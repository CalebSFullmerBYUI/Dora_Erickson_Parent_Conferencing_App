package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * <h3>viewProfile class</h3>
 *      References:
 *          https://stackoverflow.com/questions/29801031/how-to-add-button-tint-programmatically
 *              This website discusses how to change background tint programmatically.
 */
public class viewProfile extends AppCompatActivity {
    private ArrayList<TeacherItem> subscriptions = new ArrayList<>();
    private User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        if (getIntent().getStringExtra(HomePageActivity.USER_KEY) != null) {
            currentUser = new Gson().fromJson(getIntent().getStringExtra(HomePageActivity.USER_KEY), User.class);
            getSubscriptions();
        } else {
            Log.e("DirectoryActivity.onCreate()", "Error: No known User passed in.");
            currentUser = null;

            Toast errorToast = Toast.makeText(getApplicationContext(), "User not recognized.", Toast.LENGTH_LONG);
            errorToast.show();
        }
    }
    //private List subscriptions

    public void changeUsername(View view) {
        //startActivity(new Intent(viewProfile.this, ResetAccountActivity.class));
        }
    public void changePassword(View view) {
        //startActivity(new Intent(viewProfile.this, ResetAccountActivity.class));
    }


    public void getSubscriptions() {
        //Put const (final) copies of data for server request here.

        Thread getSubsThread = new Thread(new CustomRun(this) {
            @Override
            public void run() {
                String jsonResponse = new ServerRequest().request("", "");
                TeacherItemVector newSubsListVector = null;
                final ArrayList<TeacherItem> newSubsList;

                if ((jsonResponse != null) && !jsonResponse.equals("")) {
                    String mockResponse = MockResponses.GetSubscriptions();
                    newSubsListVector = new Gson().fromJson(mockResponse/*jsonResponse*/, TeacherItemVector.class);
                    ArrayList<TeacherItem> temp = newSubsListVector.getVector();
                    alphabetizeTeacherVector(temp);
                    newSubsList = temp;
                } else {
                    newSubsListVector = null;
                    newSubsList = null;
                }


                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newSubsList != null) {
                            subscriptions = newSubsList;
                            displaySubscriptions();
                        } else {
                            Log.e("viewProfile.getSubscriptions()", "Issue getting subscriptions from server.");
                            Toast errorToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    "Issue getting subscriptions.", Toast.LENGTH_LONG);
                            errorToast.show();
                        }
                    }
                });
            }
        });
        getSubsThread.start();
    }


    public void displaySubscriptions() {
        if (subscriptions == null) {
            subscriptions = new ArrayList<>();
        }

        //Clear current subscriptions.
        ((LinearLayout)findViewById(R.id.linLay_Subscriptions)).removeAllViews();

        for (TeacherItem item: subscriptions) {
            if (item != null) {
                //Set Subscriptions horizontal linear layout.
                ConstraintLayout newSubLayout = new ConstraintLayout(getApplicationContext());
                newSubLayout.setId(View.generateViewId());
                ConstraintLayout.LayoutParams newSubLayoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                newSubLayout.setLayoutParams(newSubLayoutParams);
                newSubLayout.setBackgroundResource(R.drawable.background_announc_view);

                //Set teacher name TextView and add to newSubLayout
                TextView newTeacherName = new TextView(getApplicationContext());
                newTeacherName.setId(View.generateViewId());
                ConstraintLayout.LayoutParams newTeacherNameParams = new ConstraintLayout.LayoutParams(Constraints.LayoutParams.MATCH_CONSTRAINT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                newTeacherNameParams.setMargins(10, 14, 0, 14);
                newTeacherName.setLayoutParams(newTeacherNameParams);
                newTeacherName.setTextColor(Color.BLACK);
                newTeacherName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                newTeacherName.setText(item.getName());
                newSubLayout.addView(newTeacherName);

                //Set remove subscription button and add to newSubLayout
                Button removeSubButton = new Button(getApplicationContext());
                removeSubButton.setId(View.generateViewId());
                ConstraintLayout.LayoutParams newButtonParams = new ConstraintLayout.LayoutParams(Constraints.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                newButtonParams.setMargins(0, 2, 5, 2);
                removeSubButton.setLayoutParams(newButtonParams);
                removeSubButton.setBackgroundTintList(ColorStateList.valueOf(Color.argb(255, 200, 200, 200)));
                removeSubButton.setText("Remove");
                removeSubButton.setTag(item.getClassId());
                removeSubButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeSubscription(v);
                    }
                });
                newSubLayout.addView(removeSubButton);



                //Set constraints
                ConstraintSet newConstraints = new ConstraintSet();
                newConstraints.clone(newSubLayout);

                //Constraints for teacherName
                newConstraints.connect(newTeacherName.getId(), ConstraintSet.TOP, newSubLayout.getId(), ConstraintSet.TOP);
                newConstraints.connect(newTeacherName.getId(), ConstraintSet.BOTTOM, newSubLayout.getId(), ConstraintSet.BOTTOM);
                newConstraints.connect(newTeacherName.getId(), ConstraintSet.START, newSubLayout.getId(), ConstraintSet.START);
                newConstraints.connect(newTeacherName.getId(), ConstraintSet.END, removeSubButton.getId(), ConstraintSet.START);

                //Constraints for removeSubButton
                newConstraints.connect(removeSubButton.getId(), ConstraintSet.TOP, newSubLayout.getId(), ConstraintSet.TOP);
                newConstraints.connect(removeSubButton.getId(), ConstraintSet.BOTTOM, newSubLayout.getId(), ConstraintSet.BOTTOM);
                newConstraints.connect(removeSubButton.getId(), ConstraintSet.START, newTeacherName.getId(), ConstraintSet.END);
                newConstraints.connect(removeSubButton.getId(), ConstraintSet.END, newSubLayout.getId(), ConstraintSet.END);

                //Add constraints to layout.
                newConstraints.applyTo(newSubLayout);

                //Add new ConstraintLayout to linLay_Subscriptions
                ((LinearLayout)findViewById(R.id.linLay_Subscriptions)).addView(newSubLayout);
            }
        }
    }




    private void removeSubscription(View view) {
        if ((view.getTag() != null) && (view.getTag() instanceof Integer) && (currentUser != null)) {
            final int classId = (Integer)view.getTag();
            Thread removeSubThread = new Thread(new CustomRun(this) {
                @Override
                public void run() {
                    final String jsonResponse = new ServerRequest().request("", /*"?classId=" + classId*/"");

                    currentActivity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if ((jsonResponse != null) && !jsonResponse.equals("")) {
                                Toast subRemovedToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                        "Subscription Removed", Toast.LENGTH_LONG);
                                subRemovedToast.show();
                                getSubscriptions();
                            } else {
                                Log.e("viewProfile.removeSubscription()", "Issue removing subscription from server.");
                                Toast errorToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                        "Issue removing subscription", Toast.LENGTH_LONG);
                                errorToast.show();
                            }
                        }
                    });
                }
            });

            removeSubThread.start();
        } else {
            Log.e("viewProfile.removeSubscription()", "Illegal call to removeSubscription.");
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
}