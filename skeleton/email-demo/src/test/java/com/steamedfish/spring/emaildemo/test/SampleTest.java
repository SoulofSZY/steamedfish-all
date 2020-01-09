package com.steamedfish.spring.emaildemo.test;

import lombok.ToString;
import org.junit.Test;


import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.search.*;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/30
 * @since 1.0.0
 */
public class SampleTest {


    static String protocol = "imap";
    static String host = "imap.qq.com";
    static String user = "154962931@qq.com";
    static String password = "dsbvnflvaxuvcbdf";
    static String mbox = "INBOX";
    static String url = null;
    static boolean debug = false;




   // @Test
    public void test02() {
        Pattern pattern = Pattern.compile("^RE_WJYX_\\d{8}\\.csv$");
        int matchCount = 0;

            Matcher matcher = pattern.matcher("3RE_WJYX_20191111.csv");
            if (matcher.matches()) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }

    }



   // @Test
    public  void test() {
        int optind;

        String subject = null;
        String from = null;
        boolean or = false;
        boolean today = true;
        int size = -1;



        try {

            // Get a Properties object
            Properties props = System.getProperties();

            // Get a Session object
            Session session = Session.getInstance(props, null);
            session.setDebug(debug);

            // Get a Store object
            Store store = null;
            if (url != null) {
                URLName urln = new URLName(url);
                store = session.getStore(urln);
                store.connect();
            } else {
                if (protocol != null)
                    store = session.getStore(protocol);
                else
                    store = session.getStore();

                // Connect
                if (host != null || user != null || password != null)
                    store.connect(host, user, password);
                else
                    store.connect();
            }


            // Open the Folder

            Folder folder = store.getDefaultFolder();
            if (folder == null) {
                System.out.println("Cant find default namespace");
                System.exit(1);
            }

            folder = folder.getFolder(mbox);
            if (folder == null) {
                System.out.println("Invalid folder");
                System.exit(1);
            }

            folder.open(Folder.READ_ONLY);
            SearchTerm term = null;

            if (subject != null)
                term = new SubjectTerm(subject);
            if (from != null) {
                FromStringTerm fromTerm = new FromStringTerm(from);
                if (term != null) {
                    if (or)
                        term = new OrTerm(term, fromTerm);
                    else
                        term = new AndTerm(term, fromTerm);
                }
                else
                    term = fromTerm;
            }

            if (today) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                c.set(Calendar.AM_PM, Calendar.AM);
                ReceivedDateTerm startDateTerm =
                        new ReceivedDateTerm(ComparisonTerm.GE, c.getTime());
                c.add(Calendar.DATE, 1);	// next day
                ReceivedDateTerm endDateTerm =
                        new ReceivedDateTerm(ComparisonTerm.LT, c.getTime());
                SearchTerm dateTerm = new AndTerm(startDateTerm, endDateTerm);
                if (term != null) {
                    if (or)
                        term = new OrTerm(term, dateTerm);
                    else
                        term = new AndTerm(term, dateTerm);
                }
                else
                    term = dateTerm;
            }

            if (size >= 0) {
                SizeTerm sizeTerm = new SizeTerm(ComparisonTerm.GT, size);
                if (term != null) {
                    if (or)
                        term = new OrTerm(term, sizeTerm);
                    else
                        term = new AndTerm(term, sizeTerm);
                }
                else
                    term = sizeTerm;
            }

            Message[] msgs = folder.search(term);
            System.out.println("FOUND " + msgs.length + " MESSAGES");
            if (msgs.length == 0) // no match
                System.exit(1);

            // Use a suitable FetchProfile
            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);
            folder.fetch(msgs, fp);

            for (int i = 0; i < msgs.length; i++) {
                System.out.println("--------------------------");
                System.out.println("MESSAGE #" + (i + 1) + ":");
                dumpPart(msgs[i]);
            }

            folder.close(false);
            store.close();
        } catch (Exception ex) {
            System.out.println("Oops, got exception! " + ex.getMessage());
            ex.printStackTrace();
        }

        System.exit(1);
    }

    public static void dumpPart(Part p) throws Exception {
        if (p instanceof Message) {
            Message m = (Message)p;
            Address[] a;
            // FROM
            if ((a = m.getFrom()) != null) {
                for (int j = 0; j < a.length; j++)
                    System.out.println("FROM: " + a[j].toString());
            }

            // TO
            if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
                for (int j = 0; j < a.length; j++)
                    System.out.println("TO: " + a[j].toString());
            }

            // SUBJECT
            System.out.println("SUBJECT: " + m.getSubject());

            // DATE
            Date d = m.getSentDate();
            System.out.println("SendDate: " +
                    (d != null ? d.toLocaleString() : "UNKNOWN"));

            // FLAGS:
            Flags flags = m.getFlags();
            StringBuffer sb = new StringBuffer();
            Flags.Flag[] sf = flags.getSystemFlags(); // get the system flags

            boolean first = true;
            for (int i = 0; i < sf.length; i++) {
                String s;
                Flags.Flag f = sf[i];
                if (f == Flags.Flag.ANSWERED)
                    s = "\\Answered";
                else if (f == Flags.Flag.DELETED)
                    s = "\\Deleted";
                else if (f == Flags.Flag.DRAFT)
                    s = "\\Draft";
                else if (f == Flags.Flag.FLAGGED)
                    s = "\\Flagged";
                else if (f == Flags.Flag.RECENT)
                    s = "\\Recent";
                else if (f == Flags.Flag.SEEN)
                    s = "\\Seen";
                else
                    continue;	// skip it
                if (first)
                    first = false;
                else
                    sb.append(' ');
                sb.append(s);
            }

            String[] uf = flags.getUserFlags(); // get the user flag strings
            for (int i = 0; i < uf.length; i++) {
                if (first)
                    first = false;
                else
                    sb.append(' ');
                sb.append(uf[i]);
            }
            System.out.println("FLAGS = " + sb.toString());
        }

        System.out.println("CONTENT-TYPE: " + p.getContentType());

	/* Dump input stream
	InputStream is = ((MimeMessage)m).getInputStream();
	int c;
	while ((c = is.read()) != -1)
	    System.out.write(c);
	*/

        Object o = p.getContent();
        if (o instanceof String) {
            System.out.println("This is a String");
            System.out.println((String)o);
        } else if (o instanceof Multipart) {
            System.out.println("This is a Multipart");
            Multipart mp = (Multipart)o;
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                dumpPart(mp.getBodyPart(i));
        } else if (o instanceof InputStream) {
            System.out.println("This is just an input stream");
            InputStream is = (InputStream)o;
            int c;
            while ((c = is.read()) != -1)
                System.out.write(c);
        }
    }

}