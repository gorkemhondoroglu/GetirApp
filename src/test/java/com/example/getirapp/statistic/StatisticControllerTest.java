package com.example.getirapp.statistic;


import com.example.getirapp.common.auth.JwtAuthenticationEntryPoint;
import com.example.getirapp.common.auth.JwtTokenUtil;
import com.example.getirapp.common.auth.JwtUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatisticController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class StatisticControllerTest {


    @MockBean
    private StatisticService orderService;

    @Autowired
    private MockMvc mock;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Test
    public void getById_test() throws Exception {
        // String accessToken = obtainAccessToken("Test Account", "superSafePassword");

        this.mock.perform(get("/api/statistics/getMonthlyStatistics")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.header("Authorization", "Bearer " + accessToken)
                        .param("customerId", "16673"))
                .andExpect(status().isOk());
    }

}
