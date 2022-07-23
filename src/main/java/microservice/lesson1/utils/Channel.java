package microservice.lesson1.utils;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channel {

    String DEMO = "demo";

    @Input(DEMO)
    SubscribableChannel demo();

}


