package it.ictgroup.twilio.rs.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

@Path("/")
@RegisterRestClient
public interface TwilioWhatsappSenderClient {

    /*
    curl -X POST https://api.twilio.com/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages.json \
--data-urlencode "Body=Hello there!" \
--data-urlencode "From=whatsapp:+14155238886" \
--data-urlencode "To=whatsapp:+15005550006" \
-u ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX:your_auth_token
     */
    @POST
    @Path("/2010-04-01/Accounts/{accountsid}/Messages.json")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public String send(@PathParam("accountsid") String accountsid,
                       @HeaderParam("Authorization") String authentication,
                       @HeaderParam("User-Agent") String useragent,
                       @FormParam("Body") String body,
                       @FormParam("From") String from,
                       @FormParam("To") String to,
                       @FormParam("MediaUrl") String mediaUrl
    ) throws Exception;
}
