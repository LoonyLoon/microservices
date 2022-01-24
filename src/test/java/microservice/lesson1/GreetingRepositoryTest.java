package microservice.lesson1;

import microservice.lesson1.repository.GreetingRepository;
import microservice.lesson1.Greeting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GreetingRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GreetingRepository greetingRepository;

    @Test
    public void whenFindById_thenReturnGreeting() {
        // given
        Greeting t1 = new Greeting("userT1");
        entityManager.persist(t1);
        entityManager.flush();

        // when
        Greeting found = greetingRepository.findById(t1.getId()).get();

        // then
        Assert.assertEquals(found.getContent(),t1.getContent());
    }

}

