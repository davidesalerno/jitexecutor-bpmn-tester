package org.kie.kogito.jitexecutor.tester.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kie.kogito.jitexecutor.tester.models.Applicant;
import org.kie.kogito.jitexecutor.tester.service.KafkaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("applicant")
public class ApplicantResource {

    @Inject
    KafkaService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String bascicTest(@DefaultValue("2000") @QueryParam("salary") Integer salary) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Applicant applicant = new Applicant(salary);
        return service.pushAndPoll(mapper.writeValueAsString(applicant), "applicants", "decisions");
    }
}