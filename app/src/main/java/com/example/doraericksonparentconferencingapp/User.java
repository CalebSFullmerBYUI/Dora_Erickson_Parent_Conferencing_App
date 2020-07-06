package com.example.cs246project;

/*****

 IMPLEMENTATION COMPLETE AS SPECIFIED IN DOCUMENTATION

 Class: user
 Author: Jared Green
 Purpose: This class will implement a user system into the project. At this
 time there is only one example user as it is not a working solution.

 *****/

public class User {
    // Class variables
    private String name;
    private String email;
    private String classroom;
    private int classId;
    private boolean isAdmin;

    // Non-default constructors
    public User(String name, boolean isAdmin) {
        setName(name);
        setEmail("johndoe123@example.com");
        setClassroom("N/A");
        setClassId(0);
        setAdmin(isAdmin);
    }
    public User(User user) {
        setName(user.getName());
        setEmail(user.getEmail());
        setClassroom(user.getClassroom());
        setClassId(user.getClassId());
        setAdmin(user.isAdmin());
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getClassroom() { return classroom; }
    public void setClassroom(String classroom) { this.classroom = classroom; }

    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
}
