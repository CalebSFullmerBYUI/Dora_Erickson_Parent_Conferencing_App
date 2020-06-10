package com.example.doraericksonparentconferencingapp;

import org.junit.Test;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Meant to be used for testing various objects.
 */
public class objectTest {

    /**
     * Does testing for the TextInformation class.
     */
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


    /**
     * Preformes testing for the MessageItem class.
     */
    @Test
    public void testMessageItem() {
        Field testRecipient = null;
        Field testReplies = null;
        Field testIsRead = null;

        try {
            testRecipient = MessageItem.class.getDeclaredField("recipient");
            testRecipient.setAccessible(true);
            testReplies = MessageItem.class.getDeclaredField("replies");
            testReplies.setAccessible(true);
            testIsRead = MessageItem.class.getDeclaredField("isRead");
            testIsRead.setAccessible(true);

            //Test default constructor.
            MessageItem testTextInfo = new MessageItem();
            assert (testRecipient.get(testTextInfo).equals(""));
            assert (testReplies.get(testTextInfo).equals(new ArrayList<MessageItem>()));
            assert (testIsRead.get(testTextInfo) == false);


            //Test overload constructor.
            testTextInfo = new MessageItem("TestSender", "Hi there.", new Date(8000), "Hi");
            assert (testRecipient.get(testTextInfo).equals("Hi"));
            assert (testReplies.get(testTextInfo).equals(new ArrayList<MessageItem>()));
            assert (testIsRead.get(testTextInfo) == false);

            testTextInfo = new MessageItem(null, null, null, null);
            assert (testRecipient.get(testTextInfo).equals(""));
            assert (testReplies.get(testTextInfo).equals(new ArrayList<MessageItem>()));
            assert (testIsRead.get(testTextInfo) == false);


            //Test copy constructor
            testTextInfo = new MessageItem(new MessageItem("TestSender", "Hi there.", new Date(8000), "Bye"));
            assert (testRecipient.get(testTextInfo).equals("Bye"));
            assert (testReplies.get(testTextInfo).equals(new ArrayList<MessageItem>()));
            assert (testIsRead.get(testTextInfo) == false);



            //Test getters
            testRecipient.set(testTextInfo, "sup");
            assert (testTextInfo.getRecipient().equals("sup"));
            testRecipient.set(testTextInfo, null);
            assert (testTextInfo.getRecipient().equals(""));

            testReplies.set(testTextInfo, new ArrayList<MessageItem>(6));
            assert(testTextInfo.getReplies().equals(new ArrayList<MessageItem>(6)));
            testReplies.set(testTextInfo, null);
            assert(testTextInfo.getReplies() != null);

            testIsRead.set(testTextInfo, true);
            assert(testTextInfo.getIsRead() == true);


            //Test setters
            testTextInfo.setRecipient("Dana");
            assert(testRecipient.get(testTextInfo).equals("Dana"));
            testTextInfo.setSender(null);
            assert(testRecipient.get(testTextInfo).equals(""));

            testTextInfo.setIsRead(false);
            assert(testRecipient.get(testTextInfo).equals(false));

            MessageItem testMessageItem = new MessageItem();
            testTextInfo.addReply(testMessageItem);
            List<MessageItem> messReplies = (ArrayList<MessageItem>)testReplies.get(testTextInfo);
            assert(messReplies.get(0).equals(testMessageItem));
            testTextInfo.addReply(null);
            assert(((ArrayList<MessageItem>) testReplies.get(testTextInfo)).size() == 1);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            //Test has failed.
            assert(false);
        }
    }
}
