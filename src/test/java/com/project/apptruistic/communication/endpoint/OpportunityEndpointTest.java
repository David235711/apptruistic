package com.project.apptruistic.communication.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.apptruistic.logic.CreatorType;
import com.project.apptruistic.logic.OpportunityCategory;
import com.project.apptruistic.logic.OpportunityService;
import com.project.apptruistic.logic.time.MongoLocalTime;
import com.project.apptruistic.persistence.domain.Opportunity;
import com.project.apptruistic.security.payload.request.LoginRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class OpportunityEndpointTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    OpportunityService service;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    String url = "/opportunities";

    private LoginRequest loginRequest;

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String EMAIL = "test@organization";
    public static final String PASSWORD = "00000000";

    private String obtainAccessToken(String email, String password) throws Exception {
        loginRequest = new LoginRequest(email, password);
        String body = objectMapper.writeValueAsString(loginRequest);

        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isOk());
        String response = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
//        String role = jsonParser.parseMap(response).get("roles").toString();
        return jsonParser.parseMap(response).get("accessToken").toString();
    }

    @Test
    void post() throws Exception {
        this.mvc = webAppContextSetup(this.webApplicationContext).build();
        String token = obtainAccessToken(EMAIL, PASSWORD);
        System.out.println("Token: " + token);

        Opportunity opportunity = new Opportunity(
                1,
                "help",
                OpportunityCategory.ANIMALS,
                CreatorType.BOTH,
                "id",
                "creatorName",
                "description",
                "long",
                LocalDate.now(),
                MongoLocalTime.of(LocalTime.now()),
                MongoLocalTime.of(LocalTime.now()),
                1,
                "street",
                "abc",
                "city",
                1020);

        String body = objectMapper.writeValueAsString(opportunity);

        mvc.perform(MockMvcRequestBuilders.post("/opportunities")
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .content(body)
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isCreated());
    }

    @Test
    void getOne() {
        String name = "help";

        testRestTemplate.getForObject(url + "/" + name, Opportunity.class);

        verify(service).get(name);
    }

    @Test
    void getAll() {
        testRestTemplate.getForObject(url, Opportunity[].class);

        verify(service).getAll();
    }
}
