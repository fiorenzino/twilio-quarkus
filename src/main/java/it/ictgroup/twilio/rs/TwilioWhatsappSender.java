package it.ictgroup.twilio.rs;

import it.ictgroup.twilio.rs.client.TwilioWhatsappSenderClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Base64;

import static it.ictgroup.twilio.management.AppConstants.W_SEND_MESSAGES_PATH;

@Path(W_SEND_MESSAGES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TwilioWhatsappSender {

    Logger logger = Logger.getLogger(this.getClass());

    @Inject
    @RestClient
    TwilioWhatsappSenderClient twilioWhatsappSenderClient;

    @ConfigProperty(name = "twilio.accountsid")
    String accountsid;

    @ConfigProperty(name = "twilio.authtoken")
    String authtoken;

    @ConfigProperty(name = "twilio.from")
    String from;


    @GET
    @Path("/{to}")
    public String post(@PathParam("to") String to, @QueryParam("body") String body) throws WebApplicationException {
        logger.info("POST to:" + to + ", body: " + body);
        String resp = null;
        try {
            String useragent = "quarkus.io";
            resp = twilioWhatsappSenderClient.send(accountsid, authentication(accountsid, authtoken), useragent, body, from, to,
                    "https://www.pietraiadeipoeti.it/images/e1711.png"
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(" error: " + e.getMessage());
        }
        return resp;
    }


    private String authentication(String accountsid, String authtoken) throws Exception {
        String summa = accountsid + ":" + authtoken;
        byte[] bites = Base64.getEncoder().encode(summa.getBytes());
        return "Basic " + new String(bites);
    }


}
