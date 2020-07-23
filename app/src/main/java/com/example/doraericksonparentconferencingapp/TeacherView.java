package com.example.doraericksonparentconferencingapp;

import android.content.Context;
import android.graphics.Color;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;


/**
 * <h3>TeacherView class</h3>
 * Used to display teacher information on the directory page.
 * References:
 *      https://stackoverflow.com/questions/33258635/android-set-autolink-attribute-programmatically
 *          This website discusses how to set up the autoLink attribute of TextView objects.
 * @author Caleb Fullmer
 * @since July 23, 2020
 * @version 1.0
 */
public class TeacherView extends ConstraintLayout {
    public TextView className = null;
    public TextView teacherName = null;
    public TextView email = null;
    public View divider = null;
    final int classNameId = generateViewId();
    final int teacherNameId = generateViewId();
    final int emailId = generateViewId();
    final int dividerId = generateViewId();
    final int teacherViewId = generateViewId();


    /**
     * <h3>TeacherView(Context context)</h3>
     * Overload Constructor
     * @param context (Type: Context, the application context)
     */
    public TeacherView(Context context) {
        super(context);

        makeTeacherView();
    }


    /**
     * <h3>makeTeacherView()</h3>
     * Sets the attributes for the TeacherView.
     */
    private void makeTeacherView() {
        setId(teacherViewId);
        LayoutParams newLayout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(newLayout);


        makeClassName();
        makeTeacherName();
        makeEmail();
        makeDivider();

        addView(className);
        addView(teacherName);
        addView(email);
        addView(divider);



        //Set constraints
        ConstraintSet newConstraints = new ConstraintSet();
        newConstraints.clone(this);

        //Constraints for className
        newConstraints.connect(classNameId, ConstraintSet.TOP, teacherViewId, ConstraintSet.TOP);
        newConstraints.connect(classNameId, ConstraintSet.START, teacherViewId, ConstraintSet.START);
        newConstraints.connect(classNameId, ConstraintSet.END, teacherViewId, ConstraintSet.END);

        //Constraints for teacherName
        newConstraints.connect(teacherNameId, ConstraintSet.TOP, teacherViewId, ConstraintSet.TOP);
        newConstraints.connect(teacherNameId, ConstraintSet.BOTTOM, emailId, ConstraintSet.TOP);
        newConstraints.connect(teacherNameId, ConstraintSet.START, teacherViewId, ConstraintSet.START);
        newConstraints.connect(teacherNameId, ConstraintSet.END, teacherViewId, ConstraintSet.END);

        //Constraints for email
        newConstraints.connect(emailId, ConstraintSet.TOP, teacherNameId, ConstraintSet.BOTTOM);
        newConstraints.connect(emailId, ConstraintSet.BOTTOM, dividerId, ConstraintSet.TOP);
        newConstraints.connect(emailId, ConstraintSet.START, teacherViewId, ConstraintSet.START);
        newConstraints.connect(emailId, ConstraintSet.END, teacherViewId, ConstraintSet.END);

        //Constraints for divider
        newConstraints.connect(dividerId, ConstraintSet.TOP, emailId, ConstraintSet.BOTTOM);
        newConstraints.connect(dividerId, ConstraintSet.BOTTOM, teacherViewId, ConstraintSet.BOTTOM);
        newConstraints.connect(dividerId, ConstraintSet.START, teacherViewId, ConstraintSet.START);
        newConstraints.connect(dividerId, ConstraintSet.END, teacherViewId, ConstraintSet.END);

        //Add constraints to layout.
        newConstraints.applyTo(this);
    }


    /**
     * <h3>makeClassName()</h3>
     * Sets attributes for the className TextView.
     */
    private void makeClassName() {
        className = new TextView(getContext());
        className.setId(classNameId);
        className.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        className.setTextColor(Color.BLACK);
        className.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);


        ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        newLayout.setMargins(10, 5, 10, 0);
        className.setLayoutParams(newLayout);
    }


    /**
     * <h3>makeTeacherName()</h3>
     * Sets attributes for the teacherName TextView object.
     */
    private void makeTeacherName() {
        teacherName = new TextView(getContext());
        teacherName.setId(teacherNameId);
        teacherName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        teacherName.setTextColor(Color.BLACK);


        ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        newLayout.setMargins(10, 8, 10, 0);
        teacherName.setLayoutParams(newLayout);
    }


    /**
     * <h3>makeEmail()</h3>
     * Sets attributes for the email TextView object.
     */
    private void makeEmail() {
        email = new TextView(getContext());
        email.setId(emailId);
        email.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        email.setTextColor(Color.BLACK);


        ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        newLayout.setMargins(15, 0, 15, 0);
        email.setLayoutParams(newLayout);
    }


    /**
     * <h3>makeDivider()</h3>
     * Sets attributes for the divider View object.
     */
    private void makeDivider() {
        divider = new View(getContext());
        divider.setId(dividerId);
        divider.setMinimumHeight(2);
        divider.setBackgroundColor(Color.argb(255, 156, 156, 156));

        ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
        newLayout.setMargins(0, 6, 0, 0);
        divider.setLayoutParams(newLayout);
    }



    /**
     * <h3>getClassName()</h3>
     * Returns the class name (grade level) displayed by the TeacherView.
     * @return className (Type: String, the displayed class name)
     */
    public String getClassName() {
        if (className == null) {
            makeTeacherView();
        }

        return className.getText().toString();
    }


    /**
     * <h3>setClassName(String className)</h3>
     * Sets the class name (grade level) displayed by the TeacherView.
     * @param className (Type: String, the class name to display)
     */
    public void setClassName(String className) {
        if (this.className == null) {
            makeTeacherView();
        }

        if (className != null) {
            this.className.setText(className);
        } else {
            this.className.setText("");
        }
    }


    /**
     * <h3>getTeacherName()</h3>
     * Gets the teacher name displayed by the TeacherView.
     * @return teacherName (Type: String, the displayed teacher name)
     */
    public String getTeacherName() {
        if (teacherName == null) {
            makeTeacherView();
        }

        return className.getText().toString();
    }


    /**
     * <h3>setTeacherName(String teacherName)</h3>
     * Sets the teacher name displayed by the TeacherView.
     * @param teacherName (Type: String, the teacher name to display)
     */
    public void setTeacherName(String teacherName) {
        if (this.teacherName == null) {
            makeTeacherView();
        }

        if (teacherName != null) {
            this.teacherName.setText(teacherName);
        } else {
            this.teacherName.setText("");
        }
    }


    /**
     * <h3>getEmail()</h3>
     * Gets the email displayed by the TeacherView.
     * @return email (Type: String, the displayed email)
     */
    public String getEmail() {
        if (email == null) {
            makeTeacherView();
        }

        return email.getText().toString();
    }


    /**
     * <h3>setEmail(String email)</h3>
     * Sets the email displayed by the TeacherView.
     * @param email (Type: String, the email to display)
     */
    public void setEmail(String email) {
        if (this.email == null) {
            makeTeacherView();
        }

        if (email != null) {
            this.email.setText(email);
        } else {
            this.email.setText("");
        }
    }
}
