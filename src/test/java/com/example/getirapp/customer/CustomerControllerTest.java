package com.example.getirapp.customer;


import com.example.getirapp.common.auth.JwtAuthenticationEntryPoint;
import com.example.getirapp.common.auth.JwtTokenUtil;
import com.example.getirapp.common.auth.JwtUserDetailsService;
import com.example.getirapp.order.OrderService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class CustomerControllerTest {

    private static final String CUSTOMER_API_BASE_URL = "/api/customer";

    @MockBean
    private CustomerService customerService;

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mock;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Test
    public void add_test() throws Exception {
        // String accessToken = obtainAccessToken("Test Account", "superSafePassword");

        this.mock.perform(post("/api/customer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.header("Authorization", "Bearer " + accessToken)
                        .content("{\"name\":\"Büyüyemeyenler\",\"surname\":\"Melis Danişmend\",\"mail\":\"125\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllOrdersByCustomerId_test() throws Exception {
        // String accessToken = obtainAccessToken("Test Account", "superSafePassword");

        this.mock.perform(get("/api/customer/getAllOrdersByCustomerId")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.header("Authorization", "Bearer " + accessToken)
                .param("id", "16673"))
                .andExpect(status().isOk());
    }

}
