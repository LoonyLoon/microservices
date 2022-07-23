package microservice.lesson1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import microservice.lesson1.utils.Channel;

@SpringBootApplication
@EnableBinding(Channel.class)
public class Lesson1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson1Application.class, args);
	}

}
