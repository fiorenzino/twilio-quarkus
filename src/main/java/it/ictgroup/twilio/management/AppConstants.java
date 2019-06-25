package it.ictgroup.twilio.management;

public class AppConstants extends it.ictgroup.api.management.AppConstants {

    public static final String API_PATH = "/api/v1";

    public static final String WMESSAGES_PATH = API_PATH + "/whatsappmessages";
    public static final String W_RECEIVE_MESSAGES_PATH = API_PATH + "/fromtwilio";
    public static final String W_SEND_MESSAGES_PATH = API_PATH + "/totwilio";
    public static final String TWILIO_STATUS_PATH = API_PATH + "/twilio_status";
}
