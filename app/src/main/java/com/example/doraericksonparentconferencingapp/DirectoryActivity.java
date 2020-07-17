package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * <h3>DirectoryActivity Activity</h3>
 * Allows the user to access teacher and admin info. From here
 * the user can access individual teacher and admin classrooms.
 * References:
 *      https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
 *          Official JavaDocs on TreeMaps.
 *      https://www.geeksforgeeks.org/how-to-iterate-over-a-treemap-in-java/
 *          This page discusses how to iterate over a treemap using the entrySet() method.
 *      https://www.geeksforgeeks.org/collections-sort-java-examples/
 *          This page discusses how to use the Collections.sort.
 *      https://developer.android.com/reference/android/view/View#getTag()
 *      https://stackoverflow.com/questions/2694663/android-attach-data-to-views
 *          These pages discuss the View.getTag() and .setTag() methods.
 * @author Caleb Fullmer
 * @since June 23 2020
 * @version 1.1
 */
public class DirectoryActivity extends AppCompatActivity {
    //Variables
    private ArrayList<TeacherItem> teachers = new ArrayList<TeacherItem>();
    private TreeMap<String, ArrayList<TeacherItem>> gradeSortTeachers =
            new TreeMap<String, ArrayList<TeacherItem>>();
    private User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        if (getIntent().getStringExtra(HomePageActivity.USER_KEY) != null) {
            currentUser = new Gson().fromJson(getIntent().getStringExtra(HomePageActivity.USER_KEY), User.class);
        } else {
            Log.e("DirectoryActivity.onCreate()", "Error: No known User passed in.");
            currentUser = new User("John Doe", false);

            Toast errorToast = Toast.makeText(getApplicationContext(), "User not recognized.", Toast.LENGTH_LONG);
            errorToast.show();
        }

        getDirectoryInfo();
    }


    /**
     * <h3>getDirectoryInfo()</h3>
     * Sends a request to the server to get the directory info.
     */
    public void getDirectoryInfo() {
        Thread getDirThread = new Thread(new CustomRun(this) {
            @Override
            public void run() {
                String dirJson = new ServerRequest().request("", "");
                final ArrayList<TeacherItem> newData;
                final TreeMap<String, ArrayList<TeacherItem>> newGradeSortedData;

                if ((dirJson != null) && (dirJson != "")) {
                    //driJson replaced with mockResponse for testing.
                    String mockResponse = MockResponses.GetTeachers();

                    TeacherItemVector newDirectory = new Gson().fromJson(mockResponse/*dirJson*/, TeacherItemVector.class);
                    alphabetizeTeacherVector(newDirectory.getVector());

                    newData = newDirectory.getVector();
                    newGradeSortedData = sortTeachersByGrade(newDirectory.getVector());
                } else {
                    newData = null;
                    newGradeSortedData = null;
                }

                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((newData != null) && (!newData.isEmpty()) && (newGradeSortedData != null)
                                && (!newGradeSortedData.isEmpty())) {
                            teachers = newData;
                            gradeSortTeachers = newGradeSortedData;
                            displayDirectory();
                        } else {
                            Toast errorToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    "Error loading directory.", Toast.LENGTH_LONG);
                            errorToast.show();
                        }
                    }
                });
            }
        });

        getDirThread.start();
    }

    /**
     * <h3>displayDirectory()</h3>
     * Displays the teachers and admins by class type (Ex: 1st Grade,
     * counselor, etc.) Teachers are arranged alphabetically within each
     * category.
     */
    public void displayDirectory() {
        if (teachers == null) {
            teachers = new ArrayList<TeacherItem>();
        }

        if (gradeSortTeachers == null) {
            gradeSortTeachers = new TreeMap<String, ArrayList<TeacherItem>>();
        }

        ((LinearLayout)findViewById(R.id.linLay_Directory)).removeAllViews();


        //Iterate through bst and loop through each array.
        for (Map.Entry<String, ArrayList<TeacherItem>> it: gradeSortTeachers.entrySet()) {
            //Add data to be displayed as nessisary.
            if ((it.getValue() != null) && !it.getValue().isEmpty()) {
                GradeSectionView newGradeView = new GradeSectionView(getApplicationContext());
                newGradeView.setLabel(it.getValue().get(0).getClassName());

                for (TeacherItem item: it.getValue()) {
                    if (item != null) {
                        Button newButton = new Button(getApplicationContext());
                        /* Sets the button's tag to the teacher's class id. Used to specify
                         * the correct class in the goToClassroom() method.
                         */
                        newButton.setTag(item.getClassId());
                        newButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToClassroom(v);
                            }
                        });

                        newGradeView.addTeacherBtn(item.getName(), newButton);
                    }
                }

                ((LinearLayout)findViewById(R.id.linLay_Directory)).addView(newGradeView);
            }
        }
    }

    /**
     * <h3>sortTeachersByGrade()</h3>
     * Sorts the given list of teachers by grade in a TreeMap.
     * @param listToSort (Type: ArrayList<TeacherItem>, the list to orginize)
     * @return treeList (Type: TreeMap<String, ArrayList<TeacherItem>>, the grade orginized teachers.)
     */
    private TreeMap<String, ArrayList<TeacherItem>> sortTeachersByGrade(ArrayList<TeacherItem> listToSort) {
        if (listToSort == null) {
            return new TreeMap<String, ArrayList<TeacherItem>>();
        }

        TreeMap<String, ArrayList<TeacherItem>> newSortedTeachers = new TreeMap<String, ArrayList<TeacherItem>>();

        for (TeacherItem teacher: listToSort) {
            if (teacher != null) {
                if (newSortedTeachers.containsKey(teacher.getClassName())) {
                    newSortedTeachers.get(teacher.getClassName()).add(teacher);
                } else {
                    ArrayList<TeacherItem> newList = new ArrayList<>();
                    newList.add(teacher);
                    newSortedTeachers.put(teacher.getClassName(), newList);
                }
            }
        }

        for (Map.Entry<String, ArrayList<TeacherItem>> it: newSortedTeachers.entrySet()) {
            if (it.getValue().size() > 1) {
                alphabetizeTeacherVector(it.getValue());
            }
        }

        return newSortedTeachers;
    }

    /**
     * <h3>alphabetizeTeachervector(ArrayList<TeacherItem> teacherVector)</h3>
     * Sorts the teachers in the list by first name alphabetizes.
     * @param teacherVector (Type: ArrayList<TeacherItem>, the teacher list to sort)
     */
    private void alphabetizeTeacherVector(ArrayList<TeacherItem> teacherVector) {
        Collections.sort(teacherVector, new Comparator<TeacherItem>() {
            @Override
            public int compare(TeacherItem teach1, TeacherItem teach2) {
                //Change this to be last name if we decide to have seperate last and first names.
                if ((teach1 == null) || (teach1.getName().equals(""))) {
                    return 1;
                }

                if ((teach2 == null) || (teach2.getName().equals(""))) {
                    return -1;
                }

                char teach1Char = ' ';
                char teach2Char = ' ';

                for (int index = 0; index <= 9; ++index) {
                    if (teach1.getName().length() >= (index + 1)) {
                        if (teach2.getName().length() >= (index + 1)) {
                            teach1Char = teach1.getName().charAt(index);
                            teach2Char = teach2.getName().charAt(index);

                            if (Character.isLetter(teach1Char)) {
                                Character.toLowerCase(teach1Char);
                            }

                            if (Character.isLetter(teach2Char)) {
                                Character.toLowerCase(teach2Char);
                            }

                            if (teach1Char > teach2Char) {
                                return 1;
                            } else if (teach2Char > teach1Char) {
                                return -1;
                            }
                        } else {
                            return 1;
                        }
                    } else if (teach1.getName().length() == teach2.getName().length()){
                        return 0;
                    } else {
                        return -1;
                    }
                }

                return 0;
            }
        });
    }

    /**
     * <h3>goToClassroom(view view)</h3>
     * Redirects the user to the classroom page indicated by the view
     * object.
     * @param view (Type: View, the object which triggered the method)
     */
    public void goToClassroom(View view) {
        if ((view.getTag() != null) && (view.getTag() instanceof Integer)) {
            Intent newClassroom = new Intent(this, ClassroomActivity.class);
            boolean isAdmin = false;
            int classId = (int)view.getTag();

            /* Note, somehow needs access to current user. Could either have variable passed to it,
             * or HomePageActivity user could be static.
             */
            if (currentUser.isAdmin() && (currentUser.getClassId() == classId)) {
                isAdmin = true;
            }

            //Add data to intent.
            newClassroom.putExtra(HomePageActivity.USER_KEY, new Gson().toJson(currentUser));

            startActivity(newClassroom);
        } else {
            Log.e("DirectoryActivity", "Could not get class Id");
            Toast errorToast = Toast.makeText(getApplicationContext(), "Could not access class.",Toast.LENGTH_LONG);
            errorToast.show();
        }
    }



    //This would require a boolean variable to keep track of the desired setting.
    /**
     * <h3>switchDisplay(View view)</h3>
     * Toggles between displaying teachers alphabetically or by class type.
     * @param view (Type: View, the object which triggered the method)
     */
    /*
    public void switchDisplay(View view) {

    }
    */
}