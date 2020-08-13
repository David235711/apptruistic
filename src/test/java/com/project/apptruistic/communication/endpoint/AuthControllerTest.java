package com.project.apptruistic.communication.endpoint;

import com.project.apptruistic.ApptruisticApplication;
import com.project.apptruistic.logic.mail.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {ApptruisticApplication.class})
class AuthControllerTest {

    @MockBean
    MailServiceImpl mail;

    @Autowired
    private MockMvc mvc;

    @Test
    public void existentUserCanGetTokenAndAuthentication() throws Exception {
        String email = "test@volunteer";
        String password = "00000000";

        String body = "{\"email\":\"" + email + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/login")
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"access_token\": \"", "");
        String token = response.replace("\"}", "");

        mvc.perform(MockMvcRequestBuilders.get("/test")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void registerIndividual() {
    }

    @Test
    void registerOrganization() {
    }
}