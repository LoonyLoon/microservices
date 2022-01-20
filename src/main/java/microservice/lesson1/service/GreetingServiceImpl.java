package microservice.lesson1.service;

import microservice.lesson1.Greeting;
import microservice.lesson1.exception.*;
import microservice.lesson1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private GreetingRepository entityRepository;

    @Override
    @Transactional
    public List<Greeting> browse() {
        List<Greeting> list = new ArrayList<>();
        entityRepository.findAll().forEach(e -> list.add(e) );
        return list;
    }

    @Override
    @Transactional
    public Greeting findByID(long id) throws GreetingNotFoundException {
        if (entityRepository.existsById(id)) {
            return entityRepository.findById(id).get();
        } else {
            throw new GreetingNotFoundException("Greeting by ID(" + id + ") not found");
        }
    }

    @Override
    @Transactional
    public Greeting create(Greeting greeting) throws GreetingAlreadyExistsException {
        return entityRepository.save(greeting);
    }

    @Override
    @Transactional
    public Greeting update(Greeting greeting) throws GreetingNotFoundException {
        if (entityRepository.existsById(greeting.getId())) {
            return entityRepository.save(greeting);
        } else {
            throw new GreetingNotFoundException("Greeting by ID(" + greeting.getId() + ") not found");
        }
    }

    @Override
    @Transactional
    public void remove(long id) throws GreetingNotFoundException {
        if (entityRepository.existsById(id)) {
            entityRepository.deleteById(id);
        } else {
            throw new GreetingNotFoundException("Greeting by ID(" + id + ") not found");
        }
    }
}