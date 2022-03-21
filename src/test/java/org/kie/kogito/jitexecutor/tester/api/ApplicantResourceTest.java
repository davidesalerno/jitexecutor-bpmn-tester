package org.kie.kogito.jitexecutor.tester.api;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.kie.kogito.jitexecutor.tester.service.KafkaService;
import org.mockito.Mockito;

import javax.inject.Inject;

import java.util.concurrent.ExecutionException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ApplicantResourceTest {

    @InjectMock
    KafkaService kafkaService;

    @Test
    void bascicTest() throws Exception {

       Mockito.when(kafkaService.pushAndPoll("{\"salary\":2000}", "applicants", "decisions")).thenReturn("{\"salary\":2000,\"decision\":\"Approved\"}");

        given()
                .when().get("/applicant?salary=2000")
                .then()
                .statusCode(200)
                .body(is("{\"salary\":2000,\"decision\":\"Approved\"}"));
    }
}