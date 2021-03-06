package com.compomics.pladipus.core.control.distribution.communication.mail;

import com.compomics.pladipus.core.model.properties.NetworkProperties;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author Kenneth Verheggen
 */
public class Mailer {

    /**
     * The Recieving instance
     */
    private final String recipient;
    /**
     * The Logging instance
     */
    private final static Logger LOGGER = Logger.getLogger(Mailer.class);

    /**
     *
     * @param recipient the fully qualified e-mail address for the reciever
     */
    public Mailer(String recipient) {
        this.recipient = recipient;
    }

    private Properties mailServerProperties;
    private Session getMailSession;
    private MimeMessage generateMailMessage;

    public void generateAndSendEmail(String subject, String message, String recipient) throws AddressException, MessagingException {
        // Step0 
        String mailingAddress = NetworkProperties.getInstance().getMailingAddress();
        if (mailingAddress.equalsIgnoreCase("NO MAILS")) {
            return;
        }
        // Step1
        LOGGER.info("Sending report mail to " + recipient);
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        // Step2
        LOGGER.debug("Generating message...");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        generateMailMessage.setSubject(subject);
        String emailBody = message + "<br><br> Best regards, <br>Pladipus Admin";
        generateMailMessage.setContent(emailBody, "text/html");
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com",mailingAddress, NetworkProperties.getInstance().getMailingPassWord());
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
        LOGGER.info("Done !");
    }

}
