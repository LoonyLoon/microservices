package microservice.lesson1.service;

import microservice.lesson1.entity.Greeting;
import microservice.lesson1.exception.*;



import java.util.List;

public interface GreetingService {

    public List<Greeting> browse();

    public Greeting findByID(long id) throws GreetingNotFoundException;

    public Greeting create(Greeting greeting) throws GreetingAlreadyExistsException;

    public Greeting update(Greeting greeting) throws GreetingNotFoundException;

    public void remove(long id) throws GreetingNotFoundException;

}

