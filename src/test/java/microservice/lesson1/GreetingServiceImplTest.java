package microservice.lesson1;

import microservice.lesson1.Greeting;
import microservice.lesson1.service.*;
import microservice.lesson1.repository.GreetingRepository;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Lesson1Application.class})
public class GreetingServiceImplTest {
    @TestConfiguration
    static class GreetingServiceImplTestContextConfiguration {

        @Bean
        public GreetingService greetingService() {
            return new GreetingServiceImpl();
        }
    }

    @Autowired
    private GreetingService greetingService;

    @MockBean
    private GreetingRepository greetingRepo;

    @Before
    public void before() {
        Greeting test = new Greeting("Hello, QWERTY!");

        when(greetingRepo.findById(anyLong()))
                .thenReturn(java.util.Optional.of(test));
    }

    @Test
    public void testFindByID() {
        Greeting expected = new Greeting("Hello, QWERTY!");
        Greeting found = greetingService.findByID(1L);

        assertEquals(expected.getContent(), found.getContent());
    }
}

