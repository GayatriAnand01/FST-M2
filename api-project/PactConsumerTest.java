package Project;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.http.Header;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


@ExtendWith(PactConsumerTestExt.class)
public class PactConsumerTest {
    //Headers
    Map<String, String> header = new HashMap<>();
    //Resource path
    String resourcePath = "/api/users";

    //Create the Contract
    @Pact(consumer = "UserConsumer", provider = "UserProvider")
    public RequestResponsePact consumerTest(PactDslWithProvider builder) {
        //set the headers
        header.put("Content-Type", "application/json");

        //create a request and response body
        DslPart requestResponseBody = new PactDslJsonBody()
                .numberType("id", 1098)
                .stringType("firstName", "Gayatri")
                .stringType("lastName", "Anand")
                .stringType("email", "gayatri@example.com");

        // Create rules for request and response

        return builder.given("A request to create a user")
                .uponReceiving("A request to create a user")
                .method("POST")
                .path(resourcePath)
                .headers(header)
                .body(requestResponseBody)
                .willRespondWith()
                .status(201)
                .body(requestResponseBody)
                .toPact();
    }

    @Test
    @PactTestFor(providerName = "UserProvider", port = "8282")
    public void RunTest() {
        //set a BaseURI
        String baseURI = "http://localhost:8282" + resourcePath;

        //Set the request Body
        Map<String, Object> reqbody = new HashMap<>();
        reqbody.put("id", 11098);
        reqbody.put("firstName", "Gayatri");
        reqbody.put("lastName", "Anand");
        reqbody.put("email", "gayatri@example.com");

        //Generate response and assert
        given().headers(header).body(reqbody).log().all().
                when().post(baseURI).
                then().statusCode(201).log().all();



    }
}