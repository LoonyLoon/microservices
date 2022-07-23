package microservice.lesson1.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;


import  microservice.lesson1.logger.LoggerExecutor;
import  microservice.lesson1.service.GreetingService;
import microservice.lesson1.entity.Greeting;

@Configuration
public class Listener {

    private static final String template = "Hello, %s!";
    private final GreetingService service;

    @Autowired
    public Listener(GreetingService service) {
        this.service = service;
    }

    @LoggerExecutor
    @StreamListener(Channel.DEMO)
    public void externalGreetingReceive(String name) {
        try {
            if (name == null)
                name = "World";
            Greeting greeting = new Greeting(String.format(template, name));
            service.create(greeting);
        } catch(Exception e) {
            System.out.println("Exception "+e.getMessage());
        }
    }

}
