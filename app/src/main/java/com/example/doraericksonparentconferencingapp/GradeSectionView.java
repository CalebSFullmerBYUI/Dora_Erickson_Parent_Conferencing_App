package com.example.doraericksonparentconferencingapp;


import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

import java.util.HashMap;

/**
 * <h3>GradeSectionView</h3>
 * View object used to display teachers by grade.
 * References:
 *      https://stackoverflow.com/questions/45263159/constraintlayout-change-constraints-programmatically
 *      https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintSet
 *          These webpages discuss how to set ConstraintLayout constraints programmatically.
 * @author Caleb Fullmer
 * @since July 1, 2020
 * @version 1.1
 */
public class GradeSectionView extends ConstraintLayout {
    private TextView label = null;
    private HorizontalScrollView scrollView = null;
    private LinearLayout teachersLayout = null;
    private View divider = null;
    private HashMap<String, Button> teachers = null;

    private final int labelId = generateViewId();
    private final int scrollViewId = generateViewId();
    private final int teachersLayoutId = generateViewId();
    private final int dividerId = generateViewId();
    private final int gradeSectionViewId = generateViewId();


    //Constructors
    /**
     * <h3>GradeSectionView(Context context)</h3>
     * Default constructor
     * @param context (Type: Context, the context of the app)
     */
    public GradeSectionView(Context context) {
        super(context);

        makeGradeSectionView();
    }

    private void makeGradeSectionView() {
        //Set attributes of GradeSelectionView.
        setId(gradeSectionViewId);
        LayoutParams newLayout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(newLayout);

        makeLabel();
        makeTeachersLayout();
        makeDivider();

        addView(label);
        addView(scrollView);
        addView(divider);

        //Set constraints
        ConstraintSet newConstraints = new ConstraintSet();
        newConstraints.clone(this);
        //Constraints for label.
        newConstraints.connect(labelId, ConstraintSet.TOP, gradeSectionViewId, ConstraintSet.TOP);
        newConstraints.connect(labelId, ConstraintSet.BOTTOM, scrollViewId, ConstraintSet.TOP);
        newConstraints.connect(labelId, ConstraintSet.START, gradeSectionViewId, ConstraintSet.START);
        newConstraints.connect(labelId, ConstraintSet.END, gradeSectionViewId, ConstraintSet.END);

        //Constraints for scrollView
        newConstraints.connect(scrollViewId, ConstraintSet.TOP, labelId, ConstraintSet.BOTTOM);
        newConstraints.connect(scrollViewId, ConstraintSet.BOTTOM, dividerId, ConstraintSet.TOP);
        newConstraints.connect(scrollViewId, ConstraintSet.START, gradeSectionViewId, ConstraintSet.START);
        newConstraints.connect(scrollViewId, ConstraintSet.END, gradeSectionViewId, ConstraintSet.END);

        //Constrains for divider
        newConstraints.connect(dividerId, ConstraintSet.TOP, scrollViewId, ConstraintSet.BOTTOM);
        newConstraints.connect(dividerId, ConstraintSet.BOTTOM, gradeSectionViewId, ConstraintSet.BOTTOM);
        newConstraints.connect(dividerId, ConstraintSet.START, gradeSectionViewId, ConstraintSet.START);
        newConstraints.connect(dividerId, ConstraintSet.END, gradeSectionViewId, ConstraintSet.END);

        //Add constraints to layout
        newConstraints.applyTo(this);
    }

    private void makeDivider() {
        if (divider != null) {
            this.removeView(divider);
        }

        divider = new View(getContext());

        //Set divider attributes
        divider.setId(dividerId);
        divider.setBackgroundColor(Color.argb(255, 81, 81, 81));
        LayoutParams newLayout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        divider.setLayoutParams(newLayout);
    }

    /**
     * <h3>makeLabel()</h3>
     * Creates the label for the view item.
     */
    private void makeLabel() {
        if (label != null) {
            this.removeView(label);
        }

        label = new TextView(getContext());

        //Set label attributes.
        label.setId(labelId);
        label.setText("New Grade Section");
        label.setBackgroundColor(Color.argb(255, 207, 207, 207));
        label.setTextColor(Color.BLACK);
        label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

        ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        newLayout.setMargins(8, 4, 8, 4);
        label.setLayoutParams(newLayout);
    }

    /**
     * <h3>makeTeachersLayout()</h3>
     * Creates the LinearLayout to store the teacher buttons in.
     */
    private void makeTeachersLayout() {
        if (scrollView != null) {
            this.removeView(scrollView);
        }

        teachersLayout = new LinearLayout(getContext());
        scrollView = new HorizontalScrollView(getContext());

        //Set teachersLayout attributes.
        teachersLayout.setId(teachersLayoutId);
        teachersLayout.setOrientation(LinearLayout.HORIZONTAL);
        teachersLayout.setPadding(0, 25, 0, 25);
        ConstraintLayout.LayoutParams newTeachersLayout = new Constraints.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        teachersLayout.setLayoutParams(newTeachersLayout);

        //Set scrollView attributes
        scrollView.setId(scrollViewId);
        ConstraintLayout.LayoutParams newScrollLayout = new Constraints.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scrollView.setLayoutParams(newScrollLayout);


        scrollView.addView(teachersLayout);
    }

    /**
     * <h3>makeTeacherButton(TeacherItem teacher)</h3>
     * Adds a new teacher button to the view.
     * @param teacherName (Type: String, the teacher to add)
     * @param templateButton (Type: Button, the template to use. Should have an onclick event.)
     */
    private void makeTeacherButton(String teacherName, Button templateButton) {
        if ((teacherName != null) && (templateButton != null)) {
            //Set templateButton attributes.
            //Include teacher.classID() as some attribute in button.

            if (teachersLayout == null) {
                makeTeachersLayout();
            }

            if (teachers == null) {
                teachers = new HashMap<>();
            }

            templateButton.setTextColor(Color.BLACK);
            templateButton.setText(teacherName);
            ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            newLayout.setMargins(10, 0, 10, 0);
            templateButton.setLayoutParams(newLayout);

            teachers.put(teacherName, templateButton);

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
            makeGradeSectionView();
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
                makeGradeSectionView();
            }

            label.setText(labelText);
        }
    }

    /**
     * <h3>addTeacherBtn(TeacherItem teacher, Button templateButton)</h3>
     * Adds a new teacher button to the view.
     * @param teacherName (Type: Name, the teacher to add)
     * @param templateButton (Type: Button, the template button to use. Should have on click event.)
     */
    public void addTeacherBtn(String teacherName, Button templateButton) {
        if ((teacherName != null) && (templateButton != null)) {
            makeTeacherButton(teacherName, templateButton);
        }
    }

    /**
     * <h3>removeTeacherBtn(TeacherItem teacher)</h3>
     * Removes the selected teacher item from the view.
     * @param teacherName (Type: String, the teacher to remove)
     */
    public void removeTeacherBtn(String teacherName) {
        if (teacherName != null) {
            if (teachers.get(teacherName) != null) {
                this.removeView(teachers.get(teacherName));
                teachers.remove(teacherName);
            }
        }
    }
}
