package com.example.doraericksonparentconferencingapp;


/**
 * <h3>MockResponses class</h3>
 * This class is intended to be a home for our server mock responses, and to allow for
 * easier mock response removal should the app be connected to an actual server.
 * @author Caleb Fullmer
 * @since July 16, 2020
 * @version 0.1
 */
public class MockResponses {
    //Note: Use \u0027 for apostrophies ('). Otherwise the Json will break.



    private static final String mainAnnouncements = "" +
            "{'sender': 'admin1', 'subject': 'New School Policy', " +
            "'message': 'We will be implementing a new " +
            "school policy starting October 12, 2020. It requires students to " +
            "be on time to class.', " +
            "'sentDate': 1598652343, 'dueDate': 0, 'isHomework': false}, " +

            "{'sender': 'admin1', 'subject': 'Welcome Back Event', 'message': " +
            "'The school will be hosting a welcome back event for the start of " +
            "the school year on September 4th.', " +
            "'sentDate': 1597356343, 'dueDate': 1599260420, 'isHomework': true}, " +

            "{'sender': 'admin3', 'subject': 'PTO Meeting on August 24', 'message': " +
            "'We will be having a PTO meeting on August 24th to discuss the upcoming " +
            "school year.', " +
            "'sentDate': 1598652343, 'dueDate': 1598306743, 'isHomework': true}, " +

            "{'sender': 'admin1', 'subject': 'Gradebook Changes', 'message': " +
            "'Starting September 4th, there will be changes to the Gradebook app. " +
            "Check PowerSchool for more details.', " +
            "'sentDate': 1598652343, 'dueDate': 0, 'isHomework': false}, " +

            "{'sender': 'admin3', 'subject': 'Walmart and Target School Supply Sale', 'message': " +
            "'If you have not yet been able to gather school supplies for the coming semester, " +
            "both Walmart and Target will be having a 50% off sale this weekend (August 20 -  August 23).', " +
            "'sentDate': 1597936345, 'dueDate': 0, 'isHomework': false}, " +

            "{'sender': 'admin1', 'subject': 'More District 91 Lunch Policy Changes', 'message': " +
            "'District administration have determined that current styrofoam lunch trays result " +
            "in a large increase to the district\\u0027s carbon footprint and will be switching to paper " +
            "trays. See the district website for more details.', " +
            "'sentDate': 1597779325, 'dueDate': 0, 'isHomework': false}, " +

            "{'sender': 'admin1', 'subject': 'District 91 Lunch Policy Changes', 'message': " +
            "'Starting September 4th, there will be change to the nutrition requirements for " +
            "school lunches. Check PowerSchool and the District 91 website for more details.', " +
            "'sentDate': 1597174465, 'dueDate': 0, 'isHomework': false}, " +

            "{'sender': 'admin1', 'subject': 'Public School Board Meeting', 'message': " +
            "'District 91 will be hosting a public school board meeting August 7th at 5:30pm in the " +
            "[--] building to discuss how to manage the district\\u0027s budget for the coming school year', " +
            "'sentDate': 1594941865, 'dueDate': 1596843025, 'isHomework': true}, " +

            "{'sender': 'admin2', 'subject': 'PTO Meeting', 'message': " +
            "'There will be a PTO Meeting July 31 at 6pm.', " +
            "'sentDate': 1594941865, 'dueDate': 1596240025, 'isHomework': true}, " +

            "{'sender': 'admin1', 'subject': 'Community Service Project', 'message': " +
            "'At 10am on July 22, Dora Erickson Elementary will be participating in a graveyard cleanup " +
            "at Rose Hill Cemetery.', " +
            "'sentDate': 1594414825, 'dueDate': 1595433625, 'isHomework': true}, " +

            "{'sender': 'admin1', 'subject': 'Cloths Drive', 'message': " +
            "'On July 20th between 3pm and 7pm, Dora Erickson will be hosting a cloths drive for the " +
            "community. Please bring cloths to the North side of Dora Erickson Elementary.', " +
            "'sentDate': 1594941865, 'dueDate': 1595278825, 'isHomework': true}" +

            "";




    private static final String teacherAnnouncements = "" +

            "{'sender': 'teacher1', 'subject': 'Math Assignment 1.5', 'message': " +
            "'Math Assignment 1.5: Section 1.5 problems 1, 2, 6, 8, 9, 12, 14, 15, 20, and 21.', " +
            "'sentDate': 1598612343, 'dueDate': 1599058825, 'isHomework': true}," +

            "{'sender': 'teacher1', 'subject': 'Math Assignment 1.4', 'message': " +
            "'Math Assignment 1.4: Section 1.4 problems 4, 5, 11, 16, 17, and 20.', " +
            "'sentDate': 1598612343, 'dueDate': 1599058825, 'isHomework': true}," +

            "{'sender': 'teacher1', 'subject': 'English Assignment 2.1', 'message': " +
            "'English Assignment 2.1: Section 2.1 problems 1, 2, 3, 6, 19, 22, and 25.', " +
            "'sentDate': 1598612343, 'dueDate': 1599058825, 'isHomework': true}," +

            "{'sender': 'teacher1', 'subject': 'Math Assignment 1.2', 'message': " +
            "'Math Assignment 1.2: Section 1.2 problems 4, 6, 7, 8, 10, 11, 12, 24, and29.', " +
            "'sentDate': 1598612343, 'dueDate': 1598886025, 'isHomework': true}," +

            "{'sender': 'teacher1', 'subject': 'English Assignment 1.2', 'message': " +
            "'English Assignment 1.2: Review grammar rules in section 1.2 and do problems "+
            "4, 5, and 11', " +
            "'sentDate': 1598612343, 'dueDate': 1598626825, 'isHomework': true}," +

            "{'sender': 'teacher1', 'subject': 'Math Assignment 1.1', 'message': " +
            "'Math Assignment 1.1: Section 1.1 problems 4, 5, 12, 18, and 31.', " +
            "'sentDate': 1598552343, 'dueDate': 1598626825, 'isHomework': true}" +

            /*
            "{'sender': 'teacher1', 'subject': '', 'message': " +
            "'', " +
            "'sentDate': 1598612343, 'dueDate': 0, 'isHomework': true}," +
            */
            "";





    public static final String teachers = "{'teachers': [" +
            "{'name': 'Katey Adamson', 'className': '4th Grade', 'classId': 13, 'email': 'Katey@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'John Doe', 'className': '1st Grade', 'classId': 0, 'email': 'JohnDoe@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Dan Peters', 'className': '2nd Grade', 'classId': 2, 'email': 'Dan@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Katey Brown', 'className': '5th Grade', 'classId': 10, 'email': 'Katey@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Lucy Smith', 'className': '1st Grade', 'classId': 3, 'email': 'Lucy@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Milly Sanders', 'className': 'Kindergarten', 'classId': 4, 'email': 'Milly@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Peter Pan', 'className': '1st Grade', 'classId': 6, 'email': 'Peter@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Sam Smith', 'className': '4th Grade', 'classId': 8, 'email': 'Sam@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Jane Doe', 'className': 'Kindergarten', 'classId': 5, 'email': 'Jane@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Shawn Matthews', 'className': '5th Grade', 'classId': 9, 'email': 'Shawn@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Doey Jane', 'className': '2nd Grade', 'classId': 1, 'email': 'DoeyJane@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Bethany Oswald', 'className': 'Counselor', 'classId': 11, 'email': 'Bethany@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Lisa Velvet', 'className': 'Counselor', 'classId': 12, 'email': 'Lisa@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Jack Smith', 'className': '3rd Grade', 'classId': 7, 'email': 'Jack@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Jena Woodsworth', 'className': 'Office', 'classId': 14, 'email': 'Jena@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Daniel Jenkins', 'className': '4th Grade', 'classId': 15, 'email': 'Daniel@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'John Peterson', 'className': 'Administration', 'classId': 16, 'email': 'John@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Heather Oswald', 'className': 'Administration', 'classId': 17, 'email': 'Heather@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Andrew Jackson', 'className': 'Administration', 'classId': 28, 'email': 'Jackson@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Brixton Smith', 'className': '4th Grade', 'classId': 24, 'email': 'Brixton@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Bethany Patton', 'className': '4th Grade', 'classId': 20, 'email': 'Patton@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Dalton Latonson', 'className': '4th Grade', 'classId': 22, 'email': 'Dalton@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Christina Betterson', 'className': '4th Grade', 'classId': 21, 'email': 'Christina@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Benjamin Smith', 'className': '4th Grade', 'classId': 23, 'email': 'Benjamin@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Tina Oswald', 'className': '4th Grade', 'classId': 25, 'email': 'Tina@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Andrew Lessman', 'className': '4th Grade', 'classId': 18, 'email': 'Andrew@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Beth Lessman', 'className': '4th Grade', 'classId': 19, 'email': 'Lessman@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Vance Smith', 'className': '4th Grade', 'classId': 26, 'email': 'Vance@fakeEmail.com', 'phoneNum': 2085555555}," +
            "{'name': 'Barney Lee', 'className': '4th Grade', 'classId': 27, 'email': 'Barney@fakeEmail.com', 'phoneNum': 2085555555}" +
            "]}";




    private static final String messages = "{'messages': [" +

            "{'sender': 'Terry Shelly', 'recipient': 'Me', 'subject': '', 'message': " +
            "'Hi [--], I just had a small concern about my son...', " +
            "'sentDate': 1598572460, 'isRead': false, 'replies': [" +
            "]}, " +

            "{'sender': 'admin2', 'recipient': 'Me', 'subject': 'Issue With Classrooms', 'message': " +
            "'Hi [--],\n\nIs it possible for you to come in this Saturday to help me and Tina with "+
            "some overdue preparation for the school year. We need to...', " +
            "'sentDate': 1598564540, 'isRead': false, 'replies': [" +
            "]}, " +

            "{'sender': 'Me', 'recipient': 'Dalton Latonson', 'subject': 'Small Issue', 'message': " +
            "'Hi Dalton,\n\n I just had a small issue with...', " +
            "'sentDate': 1598296645, 'isRead': true, 'replies': [" +
                "{'sender': 'Dalton Latonson', 'recipient': 'Me', 'subject': 'Small Issue', 'message': " +
                "'I understand your concern...', " +
                "'sentDate': 1598311225, 'isRead': true, 'replies': []}, " +

                "{'sender': 'Me', 'recipient': 'Dalton Latonson', 'subject': 'Small Issue', 'message': " +
                "'Thanks for clarifying, though what about...', " +
                "'sentDate': 1598407345, 'isRead': true, 'replies': []}" +
            "]}, " +

            "{'sender': 'Beth Lessman', 'recipient': 'Me', 'subject': 'District Lunch Changes', 'message': " +
            "'Did you hear about the district lunch changes. I think they\\u0027re...', " +
            "'sentDate': 1597942345, 'isRead': true, 'replies': [" +
                "{'sender': 'Me', 'recipient': 'Beth Lessman', 'subject': 'District Lunch Changes', 'message': " +
                "'Yeah, it seems like...', " +
                "'sentDate': 1597944625, 'isRead': true, 'replies': []}" +
            "]}, " +

            "{'sender': 'admin1', 'recipient': 'Me', 'subject': 'Policy Changes', 'message': " +
            "'This is just a reminder to everyone about the new district policy changes regarding...', " +
            "'sentDate': 1597720340, 'isRead': true, 'replies': [" +
            "]}" +

            /*
            "{'sender': '', 'recipient': '', 'subject': '', 'message': " +
            "'', " +
            "'sentDate': 1598552343, 'isRead': true, 'replies': [" +
            "]}," +

                "{'sender': '', 'recipient': '', 'subject': '', 'message': " +
                "'', " +
                "'sentDate': 1598552343, 'isRead': true, 'replies': []}" +
            */

            "]}";


    private static final String drafts = "{'messages': [" +
            "{'sender': 'Me', 'recipient': 'Benjamin Smith', 'subject': 'After School Program', 'message': " +
            "'Hi Benjamin,\n\nI was just wondering about', " +
            "'sentDate': 1598482220, 'isRead': true, 'replies': [" +
            "]}," +

            /*
            "{'sender': '', 'recipient': '', 'subject': '', 'message': " +
            "'', " +
            "'sentDate': 1598552343, 'isRead': true, 'replies': [" +
            "]}," +
            */

            "]}";
    /**
     * <h3>UserResponse(String adminUsername)</h3>
     * Returns a User Json response. If the passed in string matches the admin username,
     * the returned Json will be for an admin user.
     * @param adminUsername (Type: String, the admin username)
     * @return jsonResponse (Type: String, the User Json response)
     */
    public static String UserResponse(String adminUsername) {
        if (adminUsername.equals("admin") || adminUsername.equals("Admin")) {
            return "{'name': 'John Doe', 'email': 'JohnDoe@fakeEmail.com', " +
                    "'classroom': '1st Grade', 'classId': 0, 'isAdmin': true, 'uniqueId': 'admin'}";
        } else {
            return "{'name': 'User1', 'email': 'User1@fakeEmail.com', " +
                    "'classroom': '', 'classId': -1, 'isAdmin': false, 'uniqueId': 'user1'}";
        }
    }


    /**
     * <h3>GetAllAnnouncements()</h3>
     * Returns a ScheduleItemVector Json response with all mock ScheduleItem responses.
     * @return jsonResponse (Type: String, the ScheduleItemVector Json response)
     */
    public static String GetAllAnnouncements() {
        return "{'announcements': [" + mainAnnouncements + ", " + teacherAnnouncements + "]}";
    }


    /**
     * <h3>GetTeacherAnnouncements</h3>
     * Returns a ScheduleItemVector Json response with teacher mock ScheduleItem responses.
     * @return jsonResponse (Type: String, the ScheduleItemVector Json response)
     */
    public static String GetTeacherAnnouncements() {
        return "{'announcements': [" + teacherAnnouncements + "]}";
    }


    /**
     * <h3>GetTeachers()</h3>
     * Returns a TeacherItemVector Json response with all mock TeacherItem responses
     * @return jsonResponse (Type: String, the TeacherItemVector Json response)
     */
    public static String GetTeachers() {
        return teachers;
    }


    /**
     * <h3>GetMessages()</h3>
     * Returns a MessageItemVector Json response with all mock MessageItem responses.
     * @return jsonResponse (Type: String, the MessageItemVector Json response)
     */
    public static String GetMessages() {
        return messages;
    }


    /**
     * <h3>GetDrafts()</h3>
     * Returns a MessageItemVector Json response with all mock MessageItem draft responses.
     * @return jsonResponse (Type: String, the MessageItemVector Json response)
     */
    public static String GetDrafts() {
        return drafts;
    }


    /**
     * <h3>GetTeacherById(int teacherClassId)</h3>
     * Returns a TeacherItem Json response with the associated teacher.
     * @param teacherClassId (Type: int, the classroom id of the teacher)
     * @return jsonResponse (Type: String, the TeacherItem Json response)
     */
    public static String GetTeacherById(int teacherClassId) {
        //Template for mock "{'name': '', 'className': '', 'classId': 0, 'email': '@fakeEmail.com', 'phoneNum': 2085555555}" +

        switch (teacherClassId) {
            case 0:
                return "{'name': 'John Doe', 'className': '1st Grade', 'classId': 0, 'email': 'JohnDoe@fakeEmail.com', 'phoneNum': 2085555555}";
            case 1:
                return "{'name': 'Doey Jane', 'className': '2nd Grade', 'classId': 1, 'email': 'DoeyJane@fakeEmail.com', 'phoneNum': 2085555555}";
            case 2:
                return "{'name': 'Dan Peters', 'className': '2nd Grade', 'classId': 2, 'email': 'Dan@fakeEmail.com', 'phoneNum': 2085555555}";
            case 3:
                return "{'name': 'Lucy Smith', 'className': '1st Grade', 'classId': 3, 'email': 'Lucy@fakeEmail.com', 'phoneNum': 2085555555}";
            case 4:
                return "{'name': 'Milly Sanders', 'className': 'Kindergarten', 'classId': 4, 'email': 'Milly@fakeEmail.com', 'phoneNum': 2085555555}";
            case 5:
                return "{'name': 'Jane Doe', 'className': 'Kindergarten', 'classId': 5, 'email': 'Jane@fakeEmail.com', 'phoneNum': 2085555555}";
            case 6:
                return "{'name': 'Peter Pan', 'className': '1st Grade', 'classId': 6, 'email': 'Peter@fakeEmail.com', 'phoneNum': 2085555555}";
            case 7:
                return "{'name': 'Jack Smith', 'className': '3rd Grade', 'classId': 7, 'email': 'Jack@fakeEmail.com', 'phoneNum': 2085555555}";
            case 8:
                return "{'name': 'Sam Smith', 'className': '4th Grade', 'classId': 8, 'email': 'Sam@fakeEmail.com', 'phoneNum': 2085555555}";
            case 9:
                return "{'name': 'Shawn Matthews', 'className': '5th Grade', 'classId': 9, 'email': 'Shawn@fakeEmail.com', 'phoneNum': 2085555555}";
            case 10:
                return "{'name': 'Katey Brown', 'className': '5th Grade', 'classId': 10, 'email': 'Katey@fakeEmail.com', 'phoneNum': 2085555555}";
            case 11:
                return "{'name': 'Bethany Oswald', 'className': 'Counselor', 'classId': 11, 'email': 'Bethany@fakeEmail.com', 'phoneNum': 2085555555}";
            case 12:
                return "{'name': 'Lisa Velvet', 'className': 'Counselor', 'classId': 12, 'email': 'Lisa@fakeEmail.com', 'phoneNum': 2085555555}";
            case 13:
                return "{'name': 'Katey Adamson', 'className': '4th Grade', 'classId': 13, 'email': 'Katey@fakeEmail.com', 'phoneNum': 2085555555}";
            case 14:
                return "{'name': 'Jena Woodsworth', 'className': 'Office', 'classId': 14, 'email': 'Jena@fakeEmail.com', 'phoneNum': 2085555555}";
            case 15:
                return "{'name': 'Daniel Jenkins', 'className': '4th Grade', 'classId': 15, 'email': 'Daniel@fakeEmail.com', 'phoneNum': 2085555555}";
            case 16:
                return "{'name': 'John Peterson', 'className': 'Administration', 'classId': 16, 'email': 'John@fakeEmail.com', 'phoneNum': 2085555555}";
            case 17:
                return "{'name': 'Heather Oswald', 'className': 'Administration', 'classId': 17, 'email': 'Heather@fakeEmail.com', 'phoneNum': 2085555555}";
            case 18:
                return "{'name': 'Andrew Lessman', 'className': '4th Grade', 'classId': 18, 'email': 'Andrew@fakeEmail.com', 'phoneNum': 2085555555}";
            case 19:
                return "{'name': 'Beth Lessman', 'className': '4th Grade', 'classId': 19, 'email': 'Lessman@fakeEmail.com', 'phoneNum': 2085555555}";
            case 20:
                return "{'name': 'Bethany Patton', 'className': '4th Grade', 'classId': 20, 'email': 'Patton@fakeEmail.com', 'phoneNum': 2085555555}";
            case 21:
                return "{'name': 'Christina Betterson', 'className': '4th Grade', 'classId': 21, 'email': 'Christina@fakeEmail.com', 'phoneNum': 2085555555}";
            case 22:
                return "{'name': 'Dalton Latonson', 'className': '4th Grade', 'classId': 22, 'email': 'Dalton@fakeEmail.com', 'phoneNum': 2085555555}";
            case 23:
                return "{'name': 'Benjamin Smith', 'className': '4th Grade', 'classId': 23, 'email': 'Benjamin@fakeEmail.com', 'phoneNum': 2085555555}";
            case 24:
                return "{'name': 'Brixton Smith', 'className': '4th Grade', 'classId': 24, 'email': 'Brixton@fakeEmail.com', 'phoneNum': 2085555555}";
            case 25:
                return "{'name': 'Tina Oswald', 'className': '4th Grade', 'classId': 25, 'email': 'Tina@fakeEmail.com', 'phoneNum': 2085555555}";
            case 26:
                return "{'name': 'Vance Smith', 'className': '4th Grade', 'classId': 26, 'email': 'Vance@fakeEmail.com', 'phoneNum': 2085555555}";
            case 27:
                return "{'name': 'Barney Lee', 'className': '4th Grade', 'classId': 27, 'email': 'Barney@fakeEmail.com', 'phoneNum': 2085555555}";
            case 28:
                return "{'name': 'Andrew Jackson', 'className': 'Administration', 'classId': 28, 'email': 'Jackson@fakeEmail.com', 'phoneNum': 2085555555},";
            default:
                return "{'name': 'Null', 'className': 'Null', 'classId': -1, 'email': 'Null', 'phoneNum': 2085555555}";
        }
    }
}
