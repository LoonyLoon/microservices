package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class GreetingAlreadyExistsException extends RuntimeException {
    public GreetingAlreadyExistsException(String message) {
        super(message);
    }
}

