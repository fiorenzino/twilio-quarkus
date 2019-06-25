package it.ictgroup.twilio.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.FormParam;

@Entity
@Table(name = "whatsappmessages")
public class WhatsappMessage extends PanacheEntityBase {
    //GET:
    // SmsMessageSid=SMf5e486e4b105337e0b708754bc120359
    // &NumMedia=0
    // &SmsSid=SMf5e486e4b105337e0b708754bc120359
    // &SmsStatus=received
    // &Body=Jjjjj
    // &To=whatsapp%3A%2B14155238886&NumSegments=1
    // &MessageSid=SMf5e486e4b105337e0b708754bc120359
    // &AccountSid=AC2999a90e54ebe0bdab34fab15f241040
    // &From=whatsapp%3A%2B393922274929
    // &ApiVersion=2010-04-01
    @Id
    public String uuid;

    @FormParam("SmsMessageSid")
    public String smsMessageSid;
    @FormParam("NumMedia")
    public String numMedia;
    @FormParam("SmsSid")
    public String smsSid;
    @FormParam("SmsStatus")
    public String smsStatus;

    @FormParam("Body")
    @Column(name = "message_body")
    public String body;

    @FormParam("To")
    @Column(name = "message_to")
    public String to;

    @FormParam("MessageSid")
    public String messageSid;
    @FormParam("AccountSid")
    public String accountSid;

    @FormParam("From")
    @Column(name = "message_from")
    public String from;
    @FormParam("ApiVersion")
    public String apiVersion;

    public WhatsappMessage() {
    }

    @Override
    public String toString() {
        return "WhatsappMessage{" +
                "smsMessageSid='" + smsMessageSid + '\'' +
                ", numMedia='" + numMedia + '\'' +
                ", smsSid='" + smsSid + '\'' +
                ", smsStatus='" + smsStatus + '\'' +
                ", body='" + body + '\'' +
                ", to='" + to + '\'' +
                ", messageSid='" + messageSid + '\'' +
                ", accountSid='" + accountSid + '\'' +
                ", from='" + from + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                '}';
    }
}
