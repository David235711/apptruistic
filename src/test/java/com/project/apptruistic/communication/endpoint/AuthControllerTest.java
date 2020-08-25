package com.project.apptruistic.communication.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.apptruistic.ApptruisticApplication;
import com.project.apptruistic.logic.CreatorType;
import com.project.apptruistic.logic.OpportunityCategory;
import com.project.apptruistic.logic.time.MongoLocalTime;
import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.security.payload.request.LoginRequest;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = {ApptruisticApplication.class})
class AuthControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private LoginRequest loginRequest;

// This does not work as expected
//    @Before
//    public void setup() {
//        this.mvc = webAppContextSetup(this.webApplicationContext).build();
//    }

    @Test
    public void existentUserCanGetTokenAndAuthentication() throws Exception {
        this.mvc = webAppContextSetup(this.webApplicationContext).build();
        String email = "test@volunteer";
        String password = "00000000";
        loginRequest = new LoginRequest(email, password);
        String body = objectMapper.writeValueAsString(loginRequest);

        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isOk());
        String response = result.andReturn().getResponse().getContentAsString();
        System.out.println("Response: " + response);
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        String role = jsonParser.parseMap(response).get("roles").toString();
        String token = jsonParser.parseMap(response).get("accessToken").toString();
        System.out.println("Role: " + role);
        System.out.println("Token: " + token);
/* Server response 400, bad request
        Opportunity opportunity = new Opportunity(
                "name",
                OpportunityCategory.ANIMALS,
                CreatorType.INDIVIDUAL,
                "creatorId",
                "creatorName",
                "shortDescription",
                "detailedDescription",
                LocalDate.of(2020, 6, 22),
                MongoLocalTime.of(LocalTime.now()),
                MongoLocalTime.of(LocalTime.now()),
                1,
                "street",
                "houseNumber",
                "Vienna",
                1000);
        String opportunityBody = objectMapper.writeValueAsString(opportunity);
        mvc.perform(MockMvcRequestBuilders.post("/opportunities")
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .content(opportunityBody))
                .andExpect(status().isForbidden());*/
    }

    @Test
    void registerIndividual() {
    }

    @Test
    void registerOrganization() {
    }
}