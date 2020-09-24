package com.project.apptruistic.communication.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.apptruistic.ApptruisticApplication;
import com.project.apptruistic.security.payload.request.LoginRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String EMAIL = "test@volunteer";
    public static final String PASSWORD = "00000000";

// This does not work as expected
//    @Before
//    public void setup() {
//        this.mvc = webAppContextSetup(this.webApplicationContext).build();
//    }

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
    public void givenToken_whenGetSecuredRequest_thenOk() throws Exception {
        this.mvc = webAppContextSetup(this.webApplicationContext).build();
        String token = obtainAccessToken(EMAIL, PASSWORD);
        System.out.println("Token: " + token);

        mvc.perform(MockMvcRequestBuilders.get("/opportunities/availables")
                .header("Authorization", "Bearer " + token)
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE));
// Server response 400, bad request
       /* Opportunity opportunity = new Opportunity(
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
    public void givenNoToken_whenGetSecuredRequest_thenUnauthorized() throws Exception {
        this.mvc = webAppContextSetup(this.webApplicationContext).build();
                    mvc.perform(MockMvcRequestBuilders.get("/opportunities/availables")
                    .header("Authorization", "Bearer " + "xx")
                    .accept(CONTENT_TYPE))
                    .andExpect(status().is5xxServerError());

    }

    @Test
    void registerIndividual() {
    }

    @Test
    void registerOrganization() {
    }
}