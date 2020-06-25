package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

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
 * @since June 17, 2020
 * @version 1.1
 */
public class ScheduleActivity extends AppCompatActivity {
    //Uncomment this once ScheduleItem exists.
    private ArrayList<ScheduleItem> scheduledItems = new ArrayList<ScheduleItem>();
    private TreeMap<String, ArrayList<ScheduleItem>> sortedSchedule =
            new TreeMap<String, ArrayList<ScheduleItem>>();

    //Patterns for date formatting and data retreival.
    private final SimpleDateFormat keyFormat = new SimpleDateFormat("yyyyw");
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
                final ScheduleActivity newSchedule;

                if ((scheduleJson != null) && !scheduleJson.equals("")) {
                    //ScheduleJson replaced with mockResponse.
                    String mockResponse = "{'scheduledItems': [" +
                            "{'sender': 'admin1', 'subject': 'New School Policy', 'message': " +
                            "'We will be implementing a new school policy starting October 12, 2020. It requires students to be on time to class.', " +
                            "'sentDate': '1598652343', 'dueDate': 0, 'isHomework': false}," +
                            "]}";

                    newSchedule = new Gson().fromJson(mockResponse/*scheduleJson*/, ScheduleActivity.class);
                    newSchedule.sortScheduleItems();
                } else {
                    newSchedule = null;
                }

                //Update schedule list.
                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((newSchedule != null) && (newSchedule.scheduledItems != null)) {
                            scheduledItems = newSchedule.scheduledItems;
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
     */
    public void sortScheduleItems() {
        if (scheduledItems == null) {
            scheduledItems = new ArrayList<ScheduleItem>();
        }

        if (sortedSchedule == null) {
            sortedSchedule = new TreeMap<String, ArrayList<ScheduleItem>>();
        }

        //Sort items in binary tree.
        for (ScheduleItem item: scheduledItems) {
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

                if (sortedSchedule.containsKey(sortingKey)) {
                    sortedSchedule.get(sortingKey).add(item);
                } else {
                    ArrayList<ScheduleItem> newList = new ArrayList<ScheduleItem>();
                    newList.add(item);
                    sortedSchedule.put(keyFormat.format(sortingKey), newList);
                }
            }
        }

        //Ensure that the entries of the binary tree are orginized correctly by date.
        for (Map.Entry<String, ArrayList<ScheduleItem>> it: sortedSchedule.entrySet()) {
            sortScheduleListByDate(it.getValue());
        }
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
            } else if ((weekOfYear == 52) && (dayOfMonth < 15)) {
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
            currentWeek = 1;
            currentYear += 1;
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
            currentWeek = 52;
            currentYear -= 1;
        }

        displayInfo();
    }
}