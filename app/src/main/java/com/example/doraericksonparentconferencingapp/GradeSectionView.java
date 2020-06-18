package com.example.doraericksonparentconferencingapp;


import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

/**
 * <h3>GradeSectionView</h3>
 * View object used to display teachers by grade.
 * @author Caleb Fullmer
 * @since June 18, 2020
 * @version 1.0
 */
public class GradeSectionView extends LinearLayout {
    private TextView label = null;
    private LinearLayout teachersLayout = null;
    private HashMap<String, Button> teachers = null;


    //Constructors
    /**
     * <h3>GradeSectionView(Context context)</h3>
     * Default constructor
     * @param context (Type: Context, the context of the app)
     */
    public GradeSectionView(Context context) {
        super(context);

        makeLabel();
        makeTeachersLayout();

        //Set attributes of GradeSelectionView.
    }

    /**
     * <h3>makeLabel()</h3>
     * Creates the label for the view item.
     */
    private void makeLabel() {
        label = new TextView(getContext());
        //Set label attributes.

        this.addView(label);
    }

    /**
     * <h3>makeTeachersLayout()</h3>
     * Creates the LinearLayout to store the teacher buttons in.
     */
    private void makeTeachersLayout() {
        teachersLayout = new LinearLayout(getContext());
        //Set teachersLayout attributes.
        teachersLayout.setOrientation(LinearLayout.HORIZONTAL);

        this.addView(teachersLayout);
    }

    /**
     * <h3>makeTeacherButton(TeacherItem teacher)</h3>
     * Adds a new teacher button to the view.
     * @param teacher (Type: TeacherItem, the teacher to add)
     * @param templateButton (Type: Button, the template to use. Should have an onclick event.)
     */
    private void makeTeacherButton(TeacherItem teacher, Button templateButton) {
        if ((teacher != null) && (templateButton != null)) {
            //Set templateButton attributes.
            //Include teacher.classID() as some attribute in button.

            if (teachersLayout == null) {
                makeTeachersLayout();
            }

            if (teachers == null) {
                teachers = new HashMap<>();
            }

            teachers.put(teacher.getName(), templateButton);

            teachersLayout.addView(templateButton);
        }
    }





    //Getters and setters

    /**
     * <h3>getLabel()</h3>
     * Gets the text of the label.
     * @return label (Type: String, the text of the label)
     */
    public String getLabel() {
        if (label == null) {
            makeLabel();
        }

        return label.getText().toString();
    }

    /**
     * <h3>setLabel(String label)</h3>
     * Sets the text of the label.
     * @param labelText (Type: String, the new label text)
     */
    public void setLabel(String labelText) {
        if (labelText != null) {
            if (label == null) {
                makeLabel();
            }

            label.setText(labelText);
        }
    }

    /**
     * <h3>addTeacherBtn(TeacherItem teacher, Button templateButton)</h3>
     * Adds a new teacher button to the view.
     * @param teacher (Type: TeacherItem, the teacher to add)
     * @param templateButton (Type: Button, the template button to use. Should have on click event.)
     */
    public void addTeacherBtn(TeacherItem teacher, Button templateButton) {
        if (teacher != null) {
            makeTeacherButton(teacher, templateButton);
        }
    }

    /**
     * <h3>removeTeacherBtn(TeacherItem teacher)</h3>
     * Removes the selected teacher item from the view.
     * @param teacher (Type: TeacherItem, the teacher to remove)
     */
    public void removeTeacherBtn(TeacherItem teacher) {
        if (teacher != null) {
            if (teachers.get(teacher.getName()) != null) {
                this.removeView(teachers.get(teacher.getName()));
                teachers.remove(teacher.getName());
            }
        }
    }
}
