package util;
/*

*/

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;
import java.io.*;

/*
  To use this program, change values for the following three constants,

    SMTP_HOST_NAME -- Has your SMTP Host Name
    SMTP_AUTH_USER -- Has your SMTP Authentication UserName
    SMTP_AUTH_PWD  -- Has your SMTP Authentication Password

  Next change values for fields

  emailMsgTxt  -- Message Text for the Email
  emailSubjectTxt  -- Subject for email
  emailFromAddress -- Email Address whose name will appears as "from" address

  Next change value for "emailList".
  This String array has List of all Email Addresses to Email Email needs to be sent to.


  Next to run the program, execute it as follows,

  SendMailUsingAuthentication authProg = new SendMailUsingAuthentication();

*/

public class SendMail
{

  public static final String SMTP_HOST_NAME = "smtp.gmail.com";
  public static final String SMTP_AUTH_USER = "stocksreporting@gmail.com";//Change the username
  public static final String SMTP_AUTH_PWD  = "stocksreportingfeb";//Change the password

  public static final String emailMsgTxt      = "Please find the link to the test reports on C:/reports/index.html";
  public static final String emailSubjectTxt  = "Please find the test results";
  public static final String emailFromAddress = "stocksreporting@gmail.com";

  // Add List of Email address to who email needs to be sent to
  public static final String[] emailList = {"sri.aayush.01@gmail.com"};//Change the mailing list
  
  public static final String fileAttachmentPath="C:\\Users\\aaysriva\\,maven_workspace\\stockmarket\\src\\main\\resources\\" ;
  public static final String fileAttachment="stockPrice.xlsx";
  
  public void postMail( String recipients[ ], String subject,
                            String message , String from) throws MessagingException
  {
    boolean debug = false;

     //Set the host smtp address
     Properties props = new Properties();
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host", SMTP_HOST_NAME);
    // props.put("mail.smtp.port", "587");
     props.put("mail.smtp.auth", "true");

    Authenticator auth = new SMTPAuthenticator();
    Session session = Session.getDefaultInstance(props, auth);

    session.setDebug(debug);

    // create a message
    Message msg = new MimeMessage(session);
             
    MimeBodyPart messageBodyPart =  new MimeBodyPart();  
     messageBodyPart.setText("Test mail one");  
     
    Multipart multipart = new MimeMultipart();  
      multipart.addBodyPart(messageBodyPart);  
 
      messageBodyPart = new MimeBodyPart();  
      DataSource source =  new FileDataSource(fileAttachmentPath+fileAttachment);  
      messageBodyPart.setDataHandler(new DataHandler(source));  
      messageBodyPart.setFileName(fileAttachment);  
      multipart.addBodyPart(messageBodyPart);  
      msg.setContent(multipart);  
   

 

    // set the from and to address
    InternetAddress addressFrom = new InternetAddress(from);
    msg.setFrom(addressFrom);

    InternetAddress[] addressTo = new InternetAddress[recipients.length];
    for (int i = 0; i < recipients.length; i++)
    {
        addressTo[i] = new InternetAddress(recipients[i]);
    }
    msg.setRecipients(Message.RecipientType.TO, addressTo);


    // Setting the Subject and Content Type
    msg.setSubject(subject);
   // msg.setContent(message, "text/plain");
    
    Transport.send(msg);
    System.out.println("Sucessfully Sent mail to All Users");
 }


/**
* SimpleAuthenticator is used to do simple authentication
* when the SMTP server requires it.
*/
private class SMTPAuthenticator extends javax.mail.Authenticator
{

    public PasswordAuthentication getPasswordAuthentication()
    {
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PWD;
        return new PasswordAuthentication(username, password);
    }
}

}


