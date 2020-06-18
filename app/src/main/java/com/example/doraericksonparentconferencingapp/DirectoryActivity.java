package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

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
 * @author Caleb Fullmer
 * @since June 9 2020
 * @version 1.0
 */
public class DirectoryActivity extends AppCompatActivity {
    //Variables
    private ArrayList<TeacherItem> teachers = new ArrayListist<TeacherItem>();
    private TreeMap<String, ArrayList<TeacherItem>> gradeSortTeachers =
            new TreeMap<String, ArrayList<TeacherItem>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

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
                final DirectoryActivity newData;

                if ((dirJson != null) && (dirJson != "")) {
                    DirectoryActivity newDirectory = new Gson().fromJson(dirJson, DirectoryActivity.class);
                    alphabetizeTeacherVector(newDirectory.teachers);
                    newDirectory.sortTeachersByGrade();

                    newData = newDirectory;
                } else {
                    newData = null;
                }

                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newData != null) {
                            teachers = newData.teachers;
                            gradeSortTeachers = newData.gradeSortTeachers;
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
    }

    /**
     * <h3>displayDirectory()</h3>
     * Displays the teachers and admins by class type (Ex: 1st Grade,
     * counselor, etc.) Teachers are arranged alphabetically within each
     * category.
     */
    public void displayDirectory() {
        if (teachers == null) {
            teachers = new ArrayListist<TeacherItem>();
        }

        if (gradeSortTeachers == null) {
            gradeSortTeachers = new TreeMap<String, ArrayList<TeacherItem>>();
        }


        //Iterate through bst and loop through each array.
        for (Map.Entry<String, ArrayList<TeacherItem>> it: gradeSortTeachers.entrySet()) {
            //Add data to be displayed as nessisary.
            if ((it.getValue() != null) && !it.getValue().isEmpty()) {
                GradeSectionView newGradeView = new GradeSectionView(getApplicationContext());
                newGradeView.setLabel(it.getValue().get(0).getClassName());

                for (TeacherItem item: it.getValue()) {
                    Button newButton = new Button(getApplicationContext());
                    newButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goToClassroom(v);
                        }
                    });

                    newGradeView.addTeacherBtn(item, newButton);
                }

                ((LinearLayout)findViewById(R.id.linLay_Directory)).addView(newGradeView);
            }
        }
    }

    /**
     * <h3>sortTeachersByGrade()</h3>
     * Sorts the given list of teachers by grade in a TreeMap.
     */
    private void sortTeachersByGrade() {
        if (teachers == null) {
            teachers = new ArrayListist<TeacherItem>();
        }

        if (gradeSortTeachers == null) {
            new TreeMap<String, ArrayList<TeacherItem>>();
        }

        for (TeacherItem teacher: teachers) {
            if (teacher != null) {
                if (gradeSortTeachers.containsKey(teacher.getClassName())) {
                    gradeSortTeachers.get(teacher.getClassName()).add(teacher);
                } else {
                    ArrayList<TeacherItem> newList = new ArrayList<>();
                    newList.add(teacher);
                    gradeSortTeachers.put(teacher.getClassName(), newList);
                }
            }
        }

        for (Map.Entry<String, ArrayList<TeacherItem>> it: gradeSortTeachers.entrySet()) {
            if (it.getValue().size() > 1) {
                alphabetizeTeacherVector(it.getValue());
            }
        }
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
        //Intent newClassroom = new Intent(this, ClassroomsActivity.class);
        //newClassroom.putExtra("isAdmin", currentUser.getIsAdmin());
        //newClassroom.putExtra("classId", currentUser.getClassId());
        //startActivity(newClassroom);
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