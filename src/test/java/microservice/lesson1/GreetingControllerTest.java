package microservice.lesson1;

import microservice.lesson1.GreetingController;
import microservice.lesson1.Greeting;
import microservice.lesson1.service.GreetingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @MockBean
    private GreetingServiceImpl greetingService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindByID() throws Exception {
        given(greetingService.findByID(ArgumentMatchers.anyLong())).willReturn(new Greeting(1l, "Hello, QWERTY!"));

        mockMvc.perform(get("/greeting/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
        ;

    }

    @Test
    public void testGet() throws Exception {
        given(greetingService.browse()).willReturn(Arrays.asList(new Greeting("Hello, World!")));

        mockMvc.perform(get("/greeting")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].content", is("Hello, World!")));

    }

    @Test
    public void testPost() throws Exception {
        given(greetingService.create(ArgumentMatchers.any())).willReturn(new Greeting(1l, "Hello, World!"));

        mockMvc.perform(post("/greeting")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))//"name":"QWERTY"
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is("Hello, World!")));


    }

    @Test
    public void testPut() throws Exception {
        given(greetingService.findByID(ArgumentMatchers.anyLong())).willReturn(new Greeting(1l, "Hello, QWERTY!"));
        given(greetingService.update(ArgumentMatchers.any())).willReturn(new Greeting(1l, "Hello, QWERTY!"));

        mockMvc.perform(put("/greeting/1")
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"QWERTY\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
        ;

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/greeting/1"))
                .andExpect(status().isNoContent());

    }
}
