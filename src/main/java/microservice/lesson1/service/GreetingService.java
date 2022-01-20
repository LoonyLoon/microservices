package microservice.lesson1.service;

import microservice.lesson1.Greeting;

import java.util.List;

public interface GreetingService {

    public List<Greeting> browse();

    public Greeting findByID(long id);

    public Greeting create(Greeting greeting);

    public Greeting update(Greeting greeting);

    public void remove(long id);
}

