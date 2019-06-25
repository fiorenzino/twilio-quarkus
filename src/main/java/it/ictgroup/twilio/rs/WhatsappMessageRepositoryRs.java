package it.ictgroup.twilio.rs;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import it.ictgroup.api.service.RsRepositoryService;
import it.ictgroup.twilio.model.WhatsappMessage;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

import static it.ictgroup.twilio.management.AppConstants.WMESSAGES_PATH;

@Path(WMESSAGES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class WhatsappMessageRepositoryRs extends RsRepositoryService<WhatsappMessage> {


    public WhatsappMessageRepositoryRs() {
    }


    @Override
    public PanacheQuery<WhatsappMessage> getSearch(UriInfo ui, String orderBy) {
        Map<String, Object> params = new HashMap<>();
        StringBuffer queryBuffer = new StringBuffer();

        return WhatsappMessage.find(queryBuffer.toString(), params);
    }

    @Override
    public Object getId(WhatsappMessage whatsappMessage) {
        return whatsappMessage.uuid;
    }


}
