package com.example.doraericksonparentconferencingapp;

public class TeacherItem {
    private String name;
    private String className;
    private int classId;
    private String email;
    private long phoneNum;

    public TeacherItem(String name, String className, int classId){
        this.classId = classId;
        this.className = className;
        this.name = name;
    }

    public int getClassId() {
        return classId;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    public String getClassName() {
        return className;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String format(){
        return String.valueOf(phoneNum).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3");
    }

}
