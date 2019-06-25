package it.ictgroup.twilio.rs;

import it.ictgroup.twilio.model.WhatsappMessage;
import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static it.ictgroup.twilio.management.AppConstants.W_RECEIVE_MESSAGES_PATH;

@Path(W_RECEIVE_MESSAGES_PATH)
public class TwilioWhatsappReceiver {

    Logger logger = Logger.getLogger(this.getClass());


    @GET
    @Produces(MediaType.TEXT_HTML)
    public String get(@BeanParam WhatsappMessage whatsappMessage) {
        logger.info("GET:" + whatsappMessage);
        return simpleResponse();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    public String post(@BeanParam WhatsappMessage whatsappMessage) {
        logger.info("POST:" + whatsappMessage);
        return simpleResponse();
    }

    public String simpleResponse() {
        return "<Response></Response>";
    }

}
