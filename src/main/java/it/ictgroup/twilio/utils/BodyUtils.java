package it.ictgroup.twilio.utils;

import it.ictgroup.twilio.model.WhatsappMessage;

public class BodyUtils {


    public WhatsappMessage message(String body) {
        WhatsappMessage whatsappMessage = new WhatsappMessage();
        String[] parts = body.split("&");
        for (String part : parts) {
            String[] value = part.split("=");

        }
        return whatsappMessage;
    }
}
