package it.ictgroup.twilio.rs;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SmsReceiverTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/sms")
          .then()
             .statusCode(200)
             .body(is("<Response></Response>"));
    }

}
