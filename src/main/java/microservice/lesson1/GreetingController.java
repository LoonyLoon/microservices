package microservice.lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import logger.LoggerExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController("GreetingController")
@RequestMapping("/greeting")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final String defaultContent = "Diasoft";
    private final AtomicLong counter = new AtomicLong();
    private static List<Greeting> greetings = new ArrayList<>();

    private Greeting getGreeting (Long id) {
        Optional<Greeting> greeting = greetings.stream()
                .filter(gr -> gr.getId().equals(id))
                .findFirst();

        if (greeting.equals(Optional.empty())) return null;

        return greeting.get();
    }

    @LoggerExecutor
    @GetMapping
    public static ResponseEntity<List<Greeting>> getAll(
    ) {
        return ResponseEntity.ok(greetings);
    }

    @LoggerExecutor
    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getByID(
            @PathVariable("id") Long id
    ) {
        Greeting greeting = getGreeting(id);
        if (null == greeting) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(greeting);
    }


    @LoggerExecutor
    @PostMapping
    public ResponseEntity<Greeting> create(
            @RequestBody Map<String, Object> params
    ) {
        String name = (String) params.get("name");
        if (null == name) name = defaultContent;

        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
        greetings.add(greeting);

        return ResponseEntity.ok(greeting);
    }

    @LoggerExecutor
    @PutMapping("/{id}")
    public ResponseEntity<Greeting> update(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> params
    ) {
        Greeting greeting = getGreeting(id);
        if (null == greeting) return ResponseEntity.notFound().build();

        String name = (String) params.get("name");
        if (null == name) name = defaultContent;

        greeting.setContent(String.format(template, name));

        return ResponseEntity.ok(greeting);
    }

    @LoggerExecutor
    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable("id") Long id
    ) {
        Greeting greeting = getGreeting(id);
        if (null == greeting) return ResponseEntity.notFound().build();

        greetings.remove(greeting);
        return ResponseEntity.noContent().build();
    }
}

