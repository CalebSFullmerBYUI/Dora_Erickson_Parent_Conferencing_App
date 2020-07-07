package com.example.doraericksonparentconferencingapp;

import java.util.Date;

public class ScheduleItem extends TextInformation {
    private Date dueDate;
    private boolean isHomework;

    public ScheduleItem(Date dueDate, boolean isHomework){
        dueDate = this.dueDate;
        isHomework = this.isHomework;

    }
    public ScheduleItem (ScheduleItem scheduleItem){
        scheduleItem = this;
    }

    public Date getDueDate() {
        return dueDate;
    }
    public boolean getIsHomework(){
        return isHomework;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setHomework(boolean homework) {
        isHomework = homework;
    }

}
