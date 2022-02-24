package com.example.getirapp.order;


import com.example.getirapp.common.auth.JwtAuthenticationEntryPoint;
import com.example.getirapp.common.auth.JwtTokenUtil;
import com.example.getirapp.common.auth.JwtUserDetailsService;
import com.example.getirapp.model.dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class OrderControllerTest {

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
    public void getById_test() throws Exception {
        // String accessToken = obtainAccessToken("Test Account", "superSafePassword");

        this.mock.perform(get("/api/order/getByOrderId")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.header("Authorization", "Bearer " + accessToken)
                        .param("id", "16673"))
                .andExpect(status().isOk());
    }

    @Test
    public void add_test() throws Exception {
        // String accessToken = obtainAccessToken("Test Account", "superSafePassword");

        this.mock.perform(post("/api/order/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.header("Authorization", "Bearer " + accessToken)
                        .content(asJsonString(new OrderDto())))
                .andExpect(status().isOk());
    }

    /*@Test
    public void getByDateInterval_test() throws Exception {
        // String accessToken = obtainAccessToken("Test Account", "superSafePassword");
        final LocalDateTime dateTime = LocalDateTime.of(2022, Month.JANUARY, 1, 1, 1, 1);
        this.mock.perform(get("/api/order/getByDateInterval")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.header("Authorization", "Bearer " + accessToken)
                        .param("startDate", dateTime.toString())
                .param("endDate", dateTime.toString()))
                .andExpect(status().isOk());
    }*/

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
