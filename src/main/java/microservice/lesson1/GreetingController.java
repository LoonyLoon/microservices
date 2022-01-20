package microservice.lesson1;

import java.util.List;
import java.util.Map;

import microservice.lesson1.exception.*;
import microservice.lesson1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController("GreetingController")
@RequestMapping("/greeting")
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    private static final String DEFAULT_CONTENT = "World";
    private static final String NAME_FIELD = "name";

    @Autowired
    private GreetingServiceImpl greetingService;

    @GetMapping
    public ResponseEntity<List<Greeting>> getAll(
    ) {
        return ResponseEntity.ok(greetingService.browse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getByID(
            @PathVariable("id") Long id
    ) {
        try {
            Greeting greeting = greetingService.findByID(id);
            if (null == greeting) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(greeting);
        } catch (GreetingNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Greeting> create(
            @RequestBody Map<String, Object> params
    ) {
        try {
            String name = (String) params.get(NAME_FIELD);
            if (null == name) name = DEFAULT_CONTENT;

            Greeting greeting = new Greeting(String.format(TEMPLATE, name));
            greetingService.create(greeting);

            return ResponseEntity.ok(greeting);
        } catch (GreetingAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Greeting> update(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> params
    ) {
        try {
            Greeting greeting = greetingService.findByID(id);
            if (null == greeting) return ResponseEntity.notFound().build();

            String name = (String) params.get(NAME_FIELD);
            if (null == name) name = DEFAULT_CONTENT;

            greeting.setContent(String.format(TEMPLATE, name));
            greetingService.update(greeting);

            return ResponseEntity.ok(greeting);
        } catch (GreetingNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable("id") Long id
    ) {
        try {
            greetingService.remove(id);
            return ResponseEntity.noContent().build();
        } catch (GreetingNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
