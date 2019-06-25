package it.ictgroup.twilio.model;

public class TwilioResponse {
//    {
//            "sid": "SM131e6206771e4a14a971c389c9cf3225",
//            "date_created": "Tue, 25 Jun 2019 10:23:46 +0000",
//            "date_updated": "Tue, 25 Jun 2019 10:23:46 +0000",
//            "date_sent": null,
//            "account_sid": "AC2999a90e54ebe0bdab34fab15f241040",
//            "to": "whatsapp:+393922274929",
//            "from": "whatsapp:+14155238886",
//            "messaging_service_sid": null,
//            "body": "Your appointment is coming up on July 21 at 3PM",
//            "status": "queued",
//            "num_segments": "1",
//            "num_media": "0",
//            "direction": "outbound-api",
//            "api_version": "2010-04-01",
//            "price": null,
//            "price_unit": null,
//            "error_code": null,
//            "error_message": null,
//            "uri": "/2010-04-01/Accounts/AC2999a90e54ebe0bdab34fab15f241040/Messages/SM131e6206771e4a14a971c389c9cf3225.json",
//            "subresource_uris":
//        {"media": "/2010-04-01/Accounts/AC2999a90e54ebe0bdab34fab15f241040/Messages/SM131e6206771e4a14a971c389c9cf3225/Media.json"}}


    public String sid;
    public String date_created;
    public String date_updated;
    public String date_sent;
    public String account_sid;
    public String to;
    public String from;
    public String messaging_service_sid;
    public String body;
    public String status;
    public String num_segments;
    public String num_media;
    public String direction;
    public String api_version;
    public String price;
    public String price_unit;
    public String error_code;
    public String error_message;
    public String uri;
    public SubResource subresource_uris;

}
