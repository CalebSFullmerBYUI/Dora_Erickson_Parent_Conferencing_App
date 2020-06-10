package com.example.doraericksonparentconferencingapp;

import org.junit.Test;
import org.w3c.dom.Text;

import java.util.Date;
import java.lang.reflect.Field;

/**
 * Meant to be used for testing various objects.
 */
public class objectTest {
    @Test
    public void testTextInfo() {
        Field testSender = null;
        Field testSubject = null;
        Field testMessage = null;
        Field testSentDate = null;

        try {
            testSender = TextInformation.class.getDeclaredField("sender");
            testSender.setAccessible(true);
            testSubject = TextInformation.class.getDeclaredField("subject");
            testSubject.setAccessible(true);
            testMessage = TextInformation.class.getDeclaredField("message");
            testMessage.setAccessible(true);
            testSentDate = TextInformation.class.getDeclaredField("sentDate");
            testSentDate.setAccessible(true);

            //Test default constructor.
            TextInformation testTextInfo = new TextInformation();
            assert (testSender.get(testTextInfo).equals(""));
            assert (testSubject.get(testTextInfo).equals(""));
            assert (testMessage.get(testTextInfo).equals(""));
            assert (testSentDate.get(testTextInfo) != null);


            //Test overload constructor.
            testTextInfo = new TextInformation("TestSender", "Hi there.", new Date(8000));
            assert (testSender.get(testTextInfo).equals("TestSender"));
            assert (testSubject.get(testTextInfo).equals(""));
            assert (testMessage.get(testTextInfo).equals("Hi there."));
            assert (testSentDate.get(testTextInfo).equals(new Date(8000)));

            testTextInfo = new TextInformation(null, null, null);
            assert (testSender.get(testTextInfo).equals(""));
            assert (testSubject.get(testTextInfo).equals(""));
            assert (testMessage.get(testTextInfo).equals(""));
            assert (testSentDate.get(testTextInfo) != null);


            //Test copy constructor
            testTextInfo = new TextInformation(new TextInformation("TestSender", "Hi there.", new Date(8000)));
            assert (testSender.get(testTextInfo).equals("TestSender"));
            assert (testSubject.get(testTextInfo).equals(""));
            assert (testMessage.get(testTextInfo).equals("Hi there."));
            assert (testSentDate.get(testTextInfo).equals(new Date(8000)));



            //Test getters
            testSender.set(testTextInfo, "Hi");
            assert (testTextInfo.getSender().equals("Hi"));
            testSender.set(testTextInfo, null);
            assert (testTextInfo.getSender().equals(""));

            testSubject.set(testTextInfo, "bye");
            assert(testTextInfo.getSubject().equals("bye"));
            testSubject.set(testTextInfo, null);
            assert(testTextInfo.getSubject().equals(""));

            testMessage.set(testTextInfo, "hello");
            assert(testTextInfo.getMessage().equals("hello"));
            testMessage.set(testTextInfo, null);
            assert(testTextInfo.getMessage().equals(""));

            testSentDate.set(testTextInfo, new Date(500));
            assert(testTextInfo.getDate().equals(new Date(500)));
            testSentDate.set(testTextInfo, null);
            assert(testTextInfo.getDate().equals(new Date(0)));


            //Test setters
            testTextInfo.setSender("Dan");
            assert(testSender.get(testTextInfo).equals("Dan"));
            testTextInfo.setSender(null);
            assert(testSender.get(testTextInfo).equals(""));

            testTextInfo.setSubject("project");
            assert(testSubject.get(testTextInfo).equals("project"));
            testTextInfo.setSubject(null);
            assert(testSubject.get(testTextInfo).equals(""));

            testTextInfo.setMessage("see you later");
            assert(testMessage.get(testTextInfo).equals("see you later"));
            testTextInfo.setMessage(null);
            assert(testMessage.get(testTextInfo).equals(""));

            testTextInfo.setDate(new Date(100));
            assert(testSentDate.get(testTextInfo).equals(new Date(100)));
            testTextInfo.setDate(null);
            assert(testSentDate.get(testTextInfo).equals(new Date(0)));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            //Test has failed.
            assert(false);
        }
    }
}
