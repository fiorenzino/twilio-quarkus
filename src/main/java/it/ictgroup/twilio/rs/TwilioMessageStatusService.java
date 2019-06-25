package it.ictgroup.twilio.rs;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static it.ictgroup.twilio.management.AppConstants.TWILIO_STATUS_PATH;

@Path(TWILIO_STATUS_PATH)
public class TwilioMessageStatusService {

    Logger logger = Logger.getLogger(this.getClass());


    @GET
    @Produces(MediaType.TEXT_HTML)
    public String get(String body) {
        logger.info("STATUS GET:" + body);
        return simpleResponse();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    public String status(String body) {
        logger.info("STATUS:" + body);
        return simpleResponse();
    }

    public String simpleResponse() {
        return "<Response></Response>";
    }

    public void body(String body) {
        logger.info("");
    }
}
