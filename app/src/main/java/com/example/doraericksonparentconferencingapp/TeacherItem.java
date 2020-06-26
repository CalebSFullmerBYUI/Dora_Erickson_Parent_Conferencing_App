package com.example.doraericksonparentconferencingapp;

public class Teacher {
public String name;
public String className;
public int classId;
public String email;
public long phoneNum;

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

}
