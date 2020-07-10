package com.example.doraericksonparentconferencingapp;

import java.util.Date;

public class ScheduleItem extends TextInformation {
    private long dueDate = 0l;
    private boolean isHomework;

    public ScheduleItem(Date dueDate, boolean isHomework){
        this.dueDate = dueDate.getTime();
        this.isHomework = isHomework;

    }
    public ScheduleItem (ScheduleItem scheduleItem){
        scheduleItem = this;
    }

    public Date getDueDate() {
        return new Date(dueDate);
    }
    public boolean getIsHomework(){
        return isHomework;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate.getTime();
    }

    public void setHomework(boolean homework) {
        isHomework = homework;
    }

}
