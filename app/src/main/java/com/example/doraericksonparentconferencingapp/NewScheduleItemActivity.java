package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h3>NewScheduleItemActivity class</h3>
 * Provides necessary tools to create a personal calender item.
 * References:
 *      https://www.youtube.com/watch?v=on_OrrX7Nw4
 *          This website discusses how to use the Android Studio Spinner view object.
 *      https://docs.microsoft.com/en-us/office/troubleshoot/excel/determine-a-leap-year
 *          This website discusses how to determine if a year is a leap year.
 * @author Caleb Fullmer
 * @version 1.0
 * @since July 9, 2020
 */
public class NewScheduleItemActivity extends AppCompatActivity {
    public static final String SCHEDULE_ITEM_NAME = "Schedule Item To View";
    private ScheduleItem newCalenderItem = null;
    private User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule_item);


        //Set spinner adapters
        //Month
        Spinner monthSpinner = ((Spinner)findViewById(R.id.spnr_Month));
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this, R.array.months_spinner_content, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        //Day
        Spinner daySpinner = ((Spinner)findViewById(R.id.spnr_Day));
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this, R.array.days_spinner_content, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        //Year
        Spinner yearSpinner = ((Spinner)findViewById(R.id.spnr_Year));
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this, R.array.years_spinner_content, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        //Hour
        Spinner hourSpinner = ((Spinner)findViewById(R.id.spnr_Hour));
        ArrayAdapter<CharSequence> hourAdapter = ArrayAdapter.createFromResource(this, R.array.hours_spinner_content, android.R.layout.simple_spinner_item);
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(hourAdapter);

        //Minute
        Spinner minuteSpinner = ((Spinner)findViewById(R.id.spnr_Minute));
        ArrayAdapter<CharSequence> minuteAdapter = ArrayAdapter.createFromResource(this, R.array.minutes_spinner_content, android.R.layout.simple_spinner_item);
        minuteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minuteSpinner.setAdapter(minuteAdapter);

        //AmPm
        Spinner amPmSpinner = ((Spinner)findViewById(R.id.spnr_AmPm));
        ArrayAdapter<CharSequence> amPmAdapter = ArrayAdapter.createFromResource(this, R.array.ampm_spinner_content, android.R.layout.simple_spinner_item);
        amPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amPmSpinner.setAdapter(amPmAdapter);



        if (getIntent().getStringArrayExtra(HomePageActivity.USER_KEY) != null) {
            currentUser = new Gson().fromJson(getIntent().getStringExtra(HomePageActivity.USER_KEY), User.class);
        } else {
            Log.e("DirectoryActivity.onCreate()", "Error: No known User passed in.");
            currentUser = null;

            Toast errorToast = Toast.makeText(getApplicationContext(), "User not recognized.", Toast.LENGTH_LONG);
            errorToast.show();
        }




        //Get message
        if (getIntent().getStringExtra(SCHEDULE_ITEM_NAME) != null) {
            try {
                int month = 1;
                int amPm = 0;

                newCalenderItem = new Gson().fromJson(getIntent().getStringExtra(SCHEDULE_ITEM_NAME), ScheduleItem.class);
                ((EditText)findViewById(R.id.txt_EventName)).setText(newCalenderItem.getSubject());
                ((EditText)findViewById(R.id.txt_MulDescription)).setText(newCalenderItem.getMessage());


                //Get month value
                switch ((new SimpleDateFormat("LLL").format(newCalenderItem.getDate())).toLowerCase()) {
                    case "jan":
                        month = 1;
                        break;
                    case "feb":
                        month = 2;
                        break;
                    case "mar":
                        month = 3;
                        break;
                    case "apr":
                        month = 4;
                        break;
                    case "may":
                        month = 5;
                        break;
                    case "jun":
                        month = 6;
                        break;
                    case "jul":
                        month = 7;
                        break;
                    case "agu":
                        month = 8;
                        break;
                    case "sep":
                        month = 9;
                        break;
                    case "oct":
                        month = 10;
                        break;
                    case "nov":
                        month = 11;
                        break;
                    case "dec":
                        month = 12;
                        break;
                    default:
                        month = 0;
                        break;
                }



                //Get am/pm value
                if ((new SimpleDateFormat("a").format(newCalenderItem.getDate())).equals("AM")) {
                    amPm = 0;
                } else {
                    amPm = 1;
                }



                ((Spinner)findViewById(R.id.spnr_Month)).setSelection(month);
                ((Spinner)findViewById(R.id.spnr_Day)).setSelection(Integer.parseInt(
                        new SimpleDateFormat("d").format(newCalenderItem.getDate())));
                ((Spinner)findViewById(R.id.spnr_Year)).setSelection(Integer.parseInt(
                        new SimpleDateFormat("yyyy").format(newCalenderItem.getDate())));
                ((Spinner)findViewById(R.id.spnr_Hour)).setSelection(Integer.parseInt(
                        new SimpleDateFormat("h").format(newCalenderItem.getDate())));
                ((Spinner)findViewById(R.id.spnr_Minute)).setSelection(Integer.parseInt(
                        new SimpleDateFormat("m").format(newCalenderItem.getDate())));
                ((Spinner)findViewById(R.id.spnr_AmPm)).setSelection(amPm);
            } catch (Exception e) {
                Log.e("NewScheduleITemActivity.onCreate()", "Invalid ScheduleItem given.");
                newCalenderItem = new ScheduleItem(new Date(), false);
            }
        } else {
            newCalenderItem = new ScheduleItem(new Date(), false);
        }



        if (currentUser != null) {
            newCalenderItem.setSender(currentUser.getName());
        } else {
            newCalenderItem.setSender("Unknown");
        }
    }


    public void save() {
        getScheduleItemData();
        final ScheduleItem constCalenderItem = newCalenderItem;

        if ((constCalenderItem.getSubject() != null) && !constCalenderItem.getSubject().equals("")) {
            Thread saveCalenderItemThread = new Thread(new CustomRun(this) {
                @Override
                public void run() {
                    final String jsonResponse = new ServerRequest().request("", ""
                            /*"?varName=" + new Gson().toJson(constCalenderItem)*/);

                    currentActivity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast statusToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    "", Toast.LENGTH_LONG);

                            if ((jsonResponse != null) && !jsonResponse.equals("")) {
                                statusToast.setText("Event Saved");
                            } else {
                                statusToast.setText("Issue saving event.");
                            }

                            statusToast.show();
                        }
                    });
                }
            });

            saveCalenderItemThread.start();
        } else {
            Toast cantSaveToast = Toast.makeText(getApplicationContext(), "Can't Save, no subject.", Toast.LENGTH_LONG);
            cantSaveToast.show();
        }
    }

    public void delete() {
        final ScheduleItem constCalenderItem = newCalenderItem;

        Thread deleteCalenderItemThread = new Thread(new CustomRun(this) {
            @Override
            public void run() {
                final String jsonResponse = new ServerRequest().request("", ""
                        /*"?varName=" + new Gson().toJson(constCalenderItem)*/);

                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast statusToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                "", Toast.LENGTH_LONG);

                        if ((jsonResponse != null) && !jsonResponse.equals("")) {
                            statusToast.setText("Event Deleted");
                        } else {
                            statusToast.setText("Issue deleting event.");
                        }

                        statusToast.show();
                    }
                });
            }
        });

        deleteCalenderItemThread.start();
    }


    /**
     * <h3>getScheduleItemData()</h3>
     * Gets the user data for the schedule item.
     */
    private void getScheduleItemData() {
        Date scheduleDate = getCorrectDate();

        //Give a toast and end the task if date is invalid.
        if (scheduleDate == null) {
            Toast errorToast = Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_LONG);
            errorToast.show();
            return;
        }

        newCalenderItem.setSubject(((TextView)findViewById(R.id.txt_Subject)).getText().toString());
        newCalenderItem.setDate(scheduleDate);
        newCalenderItem.setDueDate(scheduleDate);
    }



    /**
     * <h3>getCorrectDate()</h3>
     * Retrieves the correct date value from the user. Retuns null if invalid date was
     * entered.
     * @return date (Type: Date, the user entered date)
     */
    private Date getCorrectDate() {
        String monthString = ((Spinner)findViewById(R.id.spnr_Month)).getSelectedItem().toString();
        String dayString = ((Spinner)findViewById(R.id.spnr_Day)).getSelectedItem().toString();
        String yearString = ((Spinner)findViewById(R.id.spnr_Year)).getSelectedItem().toString();
        String hourString = ((Spinner)findViewById(R.id.spnr_Hour)).getSelectedItem().toString();
        String minuteString = ((Spinner)findViewById(R.id.spnr_Minute)).getSelectedItem().toString();
        String amPmString = ((Spinner)findViewById(R.id.spnr_AmPm)).getSelectedItem().toString();
        int monthValue = 0;
        int dayValue = 0;
        int yearValue = 0;
        int hourValue = 8;
        int minuteValue = 0;
        boolean isLeapYear = false;


        //Get monthValue
        switch (monthString) {
            case "Month":
                dayValue = 0;
                break;
            case "January":
                monthValue = 1;
                break;
            case "February":
                monthValue = 2;
                break;
            case "March":
                monthValue = 3;
                break;
            case "April":
                monthValue = 4;
                break;
            case "May":
                monthValue = 5;
                break;
            case "June":
                monthValue = 6;
                break;
            case "July":
                monthValue = 7;
                break;
            case "August":
                monthValue = 8;
                break;
            case "Spetember":
                monthValue = 9;
                break;
            case "October":
                monthValue = 10;
                break;
            case "November":
                monthValue = 11;
                break;
            case "December":
                monthValue = 12;
                break;
            default:
                monthValue = 0;
                Log.e("NewScheduleItemActivity.getCorrectDate()", "Month value not recognized.");
                break;
        }


        //Get year value
        if (yearString.equals("Year")) {
            yearValue = 0;
        } else {
            try {
                yearValue = Integer.parseInt(yearString);
            } catch (Exception e) {
                Log.e("NewScheduleActivity.getCorrectDate()", e.getMessage());
                yearValue = 0;
            }
        }

        if ((yearValue % 2) == 0) {
            if ((yearValue % 100) == 0) {
                if ((yearValue % 400) == 0) {
                    isLeapYear = true;
                } else {
                    isLeapYear = false;
                }
            } else {
                isLeapYear = true;
            }
        } else {
            isLeapYear = false;
        }




        //Get day value
        if (dayString.equals("Day")) {
            dayValue = 0;
        } else {
            try {
                dayValue = Integer.parseInt(dayString);

                //Verify that day value is valid for the given month and year.
                if ((monthValue == 2) && isLeapYear && (dayValue > 29)) {
                    dayValue = 29;
                } else if ((monthValue == 2) && !isLeapYear && (dayValue > 28)) {
                    dayValue = 28;
                } else if ((((monthValue >= 7) && ((monthValue % 2) == 0)) || ((monthValue > 7) &&
                        ((monthValue % 2) != 0))) && (dayValue > 30)) {
                    dayValue = 30;
                }
            } catch (Exception e) {
                Log.e("NewScheduleActivity.getCorrectDate()", e.getMessage());
                dayValue = 0;
            }
        }



        //Get hour value
        if (hourString.equals("Hour")) {
            hourValue = 8;
        } else {
            try {
                hourValue = Integer.parseInt(hourString);
            } catch (Exception e) {
                Log.e("NewScheduleActivity.getCorrectDate()", e.getMessage());
                hourValue = 8;
            }
        }



        //Get minute value
        if (minuteString.equals("Minute")) {
            minuteValue = 0;
        } else {
            try {
                minuteValue = Integer.parseInt(minuteString);
            } catch (Exception e) {
                Log.e("NewScheduleActivity.getCorrectDate()", e.getMessage());
                minuteValue = 0;
            }
        }



        //Format and make date
        if ((monthValue != 0) && (dayValue != 0) && (yearValue != 0)) {
            try {
                return new SimpleDateFormat("LLL d yyyy h m a").parse(monthString +
                        " " + dayValue + " " + yearValue + " " + hourValue + " " + minuteValue + " " +
                        amPmString);
            } catch (Exception e) {
                Log.e("NewScheduleItemActivity.getCorrectDate()", e.getMessage());
                return null;
            }
        } else {
            Log.e("NewScheduleItemActivity.getCorrectDate()", "Invalid Date Entered.");
            return null;
        }
    }
}