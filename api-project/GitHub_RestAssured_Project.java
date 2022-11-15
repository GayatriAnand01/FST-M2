package Project;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GitHub_RestAssured_Project {

    RequestSpecification requestSpec;
    int keyId;

    @BeforeClass
    public void setUp() {
        // Create request specification
        requestSpec = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .setBaseUri("https://api.github.com/user/keys")
                .addHeader("Authorization", "token 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")
                .build();



    }

    @Test(priority = 0)
    public void postRequest() {
        /* Map<String,String> reqBody = new HashMap<>();
        reqBody.put("title","TestAPIKey");
        reqBody.put("key", "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCEkJiI10lT/OcSqogATL3TZv01rqTMmMOXW03FHPFyyH3+MNUiSY+DePVlQp8gKh709WozEFTZzm2Guhq1OvRhae7Bx05w9RxFvikp6VmhIcYTkjB2/lk86YxBfJBFO45vsXjgJ/4VOT7kknG1PjHByvGTmQ5DEsee2IZYzQc7/RW7PdN+lb/hg1C1RCNzIKwb3IsexjXq1XRKTNQKt/M+dsW9jAsWet3QDP7+pLNeReOrlbQENKbPVbuEKsyyps+EFrJXt9Hnt3oIxQJoOYS6lqcnfeE00a/8NGarnsF2hBEgs4Bw0KLB1dSW1n32lh5Z8ib1PFX0yTcsiERsNDs5");
        */

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("title", "TestAPIKey");
        reqBody.put("key","ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC/tZNCp4p3+FZT1Q/5BZbRoSELnaH3BIVHmw35mke2384xfUb5qBi31yukxAEh0p5+C6LTcARS1EGRqZPXTDsbQ2IzTmX+W7lKW4I/egqpC/agnxpWHL2X68cyCbDm7ouBFmRWBp6NIDKA+jfcGq8booByIQUNWKsCAIkrZGFZGnnN+6ZSrz8yCXerDk1dY3zflEvSKE6UO1cvxzBEELBKAQHjcLgeyecSBb+4kKS3dW9qQTf9bMRgFSYBXHeDo4sCHHC23vzqEbDd5MU+BIQMaKGBO9vgtNaL3XnZbL3qEit02nEudqdFoGD7Fo0I1oeClz9wA8qSkKVEYX98ZnAZ");
        //Generate response
        Response response =
                given().spec(requestSpec).body(reqBody).log().all().when().post();
        System.out.println(response.getBody().asPrettyString());
        // extract key id from response
        keyId = response.then().extract().path("id");
        //Assertions
        response.then().statusCode(201);
    }

    @Test(priority = 1)
    public void getRequestTest(){
        //Generate response
        Response getResponse =  given().spec(requestSpec).pathParam("keyId",keyId).log().all().
                when().get("/{keyId}");
        System.out.println(getResponse.getBody().asPrettyString());
        Reporter.log(getResponse.getBody().asPrettyString());
        getResponse.then().statusCode(200);

    }
    @Test(priority = 2)
    public void deleteRequestTest(){
        //Generate response
        Response deleteResponse = given().spec(requestSpec).pathParam("keyId",keyId).log().all().
                when().delete("/{keyId}");
        System.out.println(deleteResponse.getBody().asPrettyString());
        Reporter.log(deleteResponse.getBody().asPrettyString());
        deleteResponse.then().statusCode(204);

    }

}
