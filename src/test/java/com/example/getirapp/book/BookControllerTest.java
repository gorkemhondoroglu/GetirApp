package com.example.getirapp.book;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class BookControllerTest {

    private static final String BOOK_API_BASE_URL = "/api/book";

    @MockBean
    private BookServiceImpl bookService;

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

        this.mock.perform(post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.header("Authorization", "Bearer " + accessToken)
                        .content("{\"name\":\"Büyüyemeyenler\",\"author\":\"Melis Danişmend\",\"count\":\"125\",\"price\":20.00}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllOrdersByCustomerId_test() throws Exception {
        // String accessToken = obtainAccessToken("Test Account", "superSafePassword");

        this.mock.perform(post("/api/book/updateStock")
                        .contentType(MediaType.APPLICATION_JSON)
                        //.header("Authorization", "Bearer " + accessToken)
                        .content("{\"id\":\"3\",\"count\":\"125\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllBooks_test() throws Exception {
        // String accessToken = obtainAccessToken("Test Account", "superSafePassword");

        this.mock.perform(get("/api/book/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }
}
