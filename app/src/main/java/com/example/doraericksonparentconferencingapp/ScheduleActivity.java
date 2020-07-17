package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import java.text.SimpleDateFormat;

/**
 * <h3>ScheduleActivity Activity</h3>
 * Displays the user's schedule in a weekly schedule format.
 * @author Caleb Fullmer
 * @since July 13, 2020
 * @version 1.2
 */
public class ScheduleActivity extends AppCompatActivity {
    //Uncomment this once ScheduleItem exists.
    private ArrayList<ScheduleItem> scheduledItems = new ArrayList<ScheduleItem>();
    private TreeMap<String, ArrayList<ScheduleItem>> sortedSchedule =
            new TreeMap<String, ArrayList<ScheduleItem>>();
    private User currentUser = null;

    //Patterns for date formatting and data retreival.
    private final SimpleDateFormat keyFormat = new SimpleDateFormat("yyyy w");
    private final SimpleDateFormat dateYear = new SimpleDateFormat("yyyy");
    private final SimpleDateFormat weekInYear = new SimpleDateFormat("w");
    private final SimpleDateFormat dayInWeek = new SimpleDateFormat("u");
    private final SimpleDateFormat dayInMonth = new SimpleDateFormat("d");

    //Used to determine the current week to show.
    private int currentWeek = 0;
    private int currentYear = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //Set current week
        Date currentDate = new Date();
        currentWeek = Integer.parseInt(weekInYear.format(currentDate));
        currentYear = Integer.parseInt(dateYear.format(currentDate));


        //Get user
        if (getIntent().getStringExtra(HomePageActivity.USER_KEY) != null) {
            currentUser = new Gson().fromJson(getIntent().getStringExtra(HomePageActivity.USER_KEY), User.class);
        } else {
            Log.e("ScheduleActivity.onCreate()", "Error: No known User passed in.");
            currentUser = null;

            Toast errorToast = Toast.makeText(getApplicationContext(), "User not recognized.", Toast.LENGTH_LONG);
            errorToast.show();
        }


        //Set up schedule.
        getScheduleInfo();
    }


    /**
     * <h3>getScheduleInfo()</h3>
     * Sends a request to the server to get the user's schedule.
     */
    public void getScheduleInfo() {
        Thread scheduleThread = new Thread(new CustomRun(this) {
            @Override
            public void run() {
                //Get data from server.
                final String scheduleJson = new ServerRequest().request("", "");
                final ArrayList<ScheduleItem> newSchedule;
                final TreeMap<String, ArrayList<ScheduleItem>> newSortedSchedule;

                if ((scheduleJson != null) && !scheduleJson.equals("")) {
                    //ScheduleJson replaced with mockResponse.
                    String mockResponse = MockResponses.GetAllAnnouncements();

                    newSchedule = new Gson().fromJson(mockResponse/*scheduleJson*/, ScheduleItemVector.class).getVector();
                    newSortedSchedule = sortScheduleItems(newSchedule);
                } else {
                    newSchedule = null;
                    newSortedSchedule = null;
                }

                //Update schedule list.
                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((newSchedule != null) && (!newSchedule.isEmpty()) && (newSortedSchedule != null)
                                && (!newSortedSchedule.isEmpty())) {
                            scheduledItems = newSchedule;
                            sortedSchedule = newSortedSchedule;
                            displayInfo();
                        } else {
                            Toast errorToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    "Error loading schedule.", Toast.LENGTH_LONG);
                            errorToast.show();
                        }
                    }
                });
            }
        });

        scheduleThread.start();
    }

    /**
     * <h3>displayInfo()</h3>
     * Displays the user's schedule info.
     */
    public void displayInfo() {
        if (scheduledItems == null) {
            scheduledItems = new ArrayList<ScheduleItem>();
        }

        if (sortedSchedule == null) {
            sortedSchedule = new TreeMap<String, ArrayList<ScheduleItem>>();
        }



        //Update the date label TextViews
        String label = "";
        Date dateLabel = null;
        TextView txtV_DateLabel = ((TextView)findViewById(R.id.txtV_DateLabel));
        final SimpleDateFormat parsingFormat = new SimpleDateFormat("yyyy w u");
        final SimpleDateFormat formattingFormat = new SimpleDateFormat("MMM/d/yy");

        try {
            //Sunday
            dateLabel = parsingFormat.parse( Integer.toString(currentYear) + " " + Integer.toString(currentWeek) + " 7");
            ((TextView)findViewById(R.id.txtV_Sunday)).setText("Sunday " + formattingFormat.format(dateLabel));
            label += (formattingFormat.format(dateLabel) + " - ");

            //Monday
            dateLabel = parsingFormat.parse(Integer.toString(currentYear) + " " + Integer.toString(currentWeek) + " 1");
            ((TextView)findViewById(R.id.txtV_Monday)).setText("Monday " + formattingFormat.format(dateLabel));

            //Tuesday
            dateLabel = parsingFormat.parse(Integer.toString(currentYear) + " " + Integer.toString(currentWeek) + " 2");
            ((TextView)findViewById(R.id.txtV_Tuesday)).setText("Tuesday " + formattingFormat.format(dateLabel));

            //Wednesday
            dateLabel = parsingFormat.parse(Integer.toString(currentYear) + " " + Integer.toString(currentWeek) + " 3");
            ((TextView)findViewById(R.id.txtV_Wednesday)).setText("Wednesday " + formattingFormat.format(dateLabel));

            //Thursday
            dateLabel = parsingFormat.parse(Integer.toString(currentYear) + " " + Integer.toString(currentWeek) + " 4");
            ((TextView)findViewById(R.id.txtV_Thursday)).setText("Thursday " + formattingFormat.format(dateLabel));

            //Friday
            dateLabel = parsingFormat.parse(Integer.toString(currentYear) + " " + Integer.toString(currentWeek) + " 5");
            ((TextView)findViewById(R.id.txtV_Friday)).setText("Friday " + formattingFormat.format(dateLabel));

            //Saturday
            dateLabel = parsingFormat.parse(Integer.toString(currentYear) + " " + Integer.toString(currentWeek) + " 6");
            ((TextView)findViewById(R.id.txtV_Saturday)).setText("Saturday " + formattingFormat.format(dateLabel));
            label += formattingFormat.format(dateLabel);

            txtV_DateLabel.setText(label);
        } catch (ParseException e) {
            Log.e("ScheduleActivity.displayInfo()", "Issue parsing date: " + e.getMessage());
            txtV_DateLabel.setText("--");
        } catch (Exception e) {
            Log.e("ScheduleActivity.displayInfo()", e.getMessage());
            txtV_DateLabel.setText("--");
        }



        //Clear schedule views.
        ((LinearLayout)findViewById(R.id.linLay_SundaySchedule)).removeAllViews();
        ((LinearLayout)findViewById(R.id.linLay_MondaySchedule)).removeAllViews();
        ((LinearLayout)findViewById(R.id.linLay_TuesdaySchedule)).removeAllViews();
        ((LinearLayout)findViewById(R.id.linLay_WednesdaySchedule)).removeAllViews();
        ((LinearLayout)findViewById(R.id.linLay_ThursdaySchedule)).removeAllViews();
        ((LinearLayout)findViewById(R.id.linLay_FridaySchedule)).removeAllViews();
        ((LinearLayout)findViewById(R.id.linLay_SaturdaySchedule)).removeAllViews();

        //Load schedule views.
        if (sortedSchedule.containsKey(Integer.toString(currentYear) + Integer.toString(currentWeek))) {
            ArrayList<ScheduleItem> tempDisplay = sortedSchedule.get(Integer.toString(currentYear)
                    + Integer.toString(currentWeek));

            if (tempDisplay != null) {
                for (ScheduleItem item : tempDisplay) {
                    if (item != null) {
                        Date sortDate = item.getDate();
                        if (item.getIsHomework()) {
                            sortDate = item.getDueDate();
                        }

                        //Set up new AnnouncementView
                        AnnouncementView newView = new AnnouncementView(getApplicationContext(), false);
                        newView.setSender(item.getSender());
                        newView.setSubject(item.getSubject());
                        newView.setSentDate(sortDate);
                        newView.setMessage(item.getMessage());
                        newView.setTag(new Gson().toJson(item));
                        newView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addNewCalanderItem(v);
                            }
                        });

                        //Determine the day of the week.
                        switch (Integer.parseInt(dayInWeek.format(sortDate))) {
                            case 7:
                                //Sunday
                                ((LinearLayout)findViewById(R.id.linLay_SundaySchedule)).addView(newView);
                                break;
                            case 1:
                                ((LinearLayout)findViewById(R.id.linLay_MondaySchedule)).addView(newView);
                                //Monday
                                break;
                            case 2:
                                //Tuesday
                                ((LinearLayout)findViewById(R.id.linLay_TuesdaySchedule)).addView(newView);
                                break;
                            case 3:
                                //Wednesday
                                ((LinearLayout)findViewById(R.id.linLay_WednesdaySchedule)).addView(newView);
                                break;
                            case 4:
                                //Thursday
                                ((LinearLayout)findViewById(R.id.linLay_ThursdaySchedule)).addView(newView);
                                break;
                            case 5:
                                ((LinearLayout)findViewById(R.id.linLay_FridaySchedule)).addView(newView);
                                //Friday
                                break;
                            case 6:
                                ((LinearLayout)findViewById(R.id.linLay_SaturdaySchedule)).addView(newView);
                                //Saturday
                                break;
                            default:
                                ((LinearLayout)findViewById(R.id.linLay_SundaySchedule)).addView(newView);
                                Log.e("ScheduleActivity.Display()",
                                        "Day of week value not recognized.");
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * <h3>sortScheduleItems()</h3>
     * Sorts the schedule items by week into a bst.
     * @param listToSort (Type: ArrayList<ScheduleItem>, the list to sort)
     * @return sortedSchedule (Type: TreeMap<String, ArrayList<ScheduleItem>>, the sorted schedule)
     */
    public TreeMap<String, ArrayList<ScheduleItem>> sortScheduleItems(ArrayList<ScheduleItem> listToSort) {
        if (listToSort == null) {
            return new TreeMap<String, ArrayList<ScheduleItem>>();
        }


        TreeMap<String, ArrayList<ScheduleItem>> newSortedSchedule = new TreeMap<String, ArrayList<ScheduleItem>>();

        //Sort items in binary tree.
        for (ScheduleItem item: listToSort) {
            if (item != null) {
                Date sortingDate = null;
                String sortingKey = "";

                if (item.getIsHomework()) {
                    sortingDate = item.getDueDate();
                } else {
                    sortingDate = item.getDate();
                }

                //Get correct key.
                sortingKey = getScheduleKey(sortingDate);

                if (newSortedSchedule.containsKey(sortingKey)) {
                    newSortedSchedule.get(sortingKey).add(item);
                } else {
                    ArrayList<ScheduleItem> newList = new ArrayList<ScheduleItem>();
                    newList.add(item);
                    newSortedSchedule.put(sortingKey, newList);
                }
            }
        }

        //Ensure that the entries of the binary tree are orginized correctly by date.
        for (Map.Entry<String, ArrayList<ScheduleItem>> it: newSortedSchedule.entrySet()) {
            sortScheduleListByDate(it.getValue());
        }

        return newSortedSchedule;
    }

    /**
     * <h3>getScheduleKey(Date dateToKey)</h3>
     * Takes a date and returns the appropreate week key.
     * Returns " " if an invalid date was given.
     * @param dateToKey (Type: Date, the date to key)
     * @return key (Type: String, the key to associate with the date)
     */
    private String getScheduleKey(Date dateToKey) {
        if (dateToKey != null) {
            String sortingKey = "";
            int weekOfYear = Integer.parseInt(weekInYear.format(dateToKey));
            int dayOfMonth = Integer.parseInt(dayInMonth.format(dateToKey));
            int year = Integer.parseInt(dateYear.format(dateToKey));

            /* There are cases where the last few days of December can be listed as
             * the first week of the year. Similarly, the first few days of January
             * can be listed as the last week of the year. This "if" statement ensures
             * the year value is correct for the given week. All week 1 values will have
             * the same key regardless of year. All week 52 values will also have the same
             * key value regardless of year.
             */
            if ((weekOfYear == 1) && (dayOfMonth > 15)) {
                year++;
            } else if ((weekOfYear >= 52) && (dayOfMonth < 8)) {
                year--;
            }

            return Integer.toString(year) + Integer.toString(weekOfYear);
        } else {
            return " ";
        }
    }

    /**
     * <h3>sortScheduleListByDate()</h3>
     * Sorts the items in a scheduleItem list by the date.
     * @param list (Type: ArrayList<ScheduleItem>, the list to sort)
     */
    public void sortScheduleListByDate(ArrayList<ScheduleItem> list) {
        Collections.sort(list, new Comparator<ScheduleItem>() {
            @Override
            public int compare(ScheduleItem item1, ScheduleItem item2) {
                Date item1Date = item1.getDate();
                Date item2Date = item2.getDate();

                //Determine if the date to use is getDate() or getDueDate()
                // for each input.
                if (item1.getIsHomework()) {
                    item1Date = item1.getDueDate();
                }

                if (item2.getIsHomework()) {
                    item2Date = item2.getDueDate();
                }

                if (item1Date.before(item2Date)) {
                    return -1;
                } else if (item1Date.after(item2Date)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    /**
     * <h3>refreshInfo(View view)</h3>
     * Allows user to update schedule info.
     * @param view (Type: View, the object which triggered the method)
     */
    public void refreshInfo(View view) {
        getScheduleInfo();
    }

    /**
     * <h3>loadNextWeek(View view)</h3>
     * Loads the next week of the user's schedule.
     * @param view (Type: View, the object which triggered the method)
     */
    public void loadNextWeek(View view) {
        currentWeek += 1;

        if (currentWeek > 52) {
            Date newDate = null;

            try {
                newDate = new SimpleDateFormat("yyyy w").parse(currentYear + " 53");

                if ((Integer.parseInt(dayInMonth.format(newDate)) > 25) ||
                        (currentWeek > 54)) {
                    currentWeek = 1;
                    currentYear += 1;
                }
            } catch (Exception e) {
                Log.e("ScheduleActivity.loadNextWeek", "Parse Error:" + e.getMessage());
                currentWeek = 1;
                currentYear += 1;
            }
        }

        displayInfo();
    }

    /**
     * <h3>loadPrevWeek(View view)</h3>
     * Loads the previous week of the user's schedule.
     * @param view (Type: View, the object which triggered the method)
     */
    public void loadPrevWeek(View view) {
        currentWeek -= 1;

        if (currentWeek < 1) {
            Date newDate = null;

            try {
                newDate = new SimpleDateFormat("yyyy w").parse(currentYear + " 1");

                if (Integer.parseInt(dayInMonth.format(newDate)) == 1) {
                    currentWeek = 53;
                } else {
                    currentWeek = 52;
                }
            } catch (Exception e) {
                Log.e("ScheduleActivity.loadNextWeek", "Parse Error:" + e.getMessage());
                currentWeek = 52;
            }

            currentYear -= 1;
        }

        displayInfo();
    }



    public void addNewCalanderItem(View view) {
        Intent newCalenderItemIntent = new Intent(this, NewScheduleItemActivity.class);
        if (currentUser != null) {
            newCalenderItemIntent.putExtra(HomePageActivity.USER_KEY, new Gson().toJson(currentUser));
        }

        if ((view.getTag() != null) && (view.getTag() instanceof String) && !view.getTag().equals("")) {
            newCalenderItemIntent.putExtra(NewScheduleItemActivity.SCHEDULE_ITEM_NAME, (String)view.getTag());
        }

        startActivity(newCalenderItemIntent);
    }
}