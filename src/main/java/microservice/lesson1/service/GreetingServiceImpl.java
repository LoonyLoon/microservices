package microservice.lesson1.service;

import microservice.lesson1.entity.Greeting;
import microservice.lesson1.exception.*;
import microservice.lesson1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private GreetingRepository entityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Greeting> browse() {
        List<Greeting> list = new ArrayList<>();
        entityRepository.findAll().forEach(e -> list.add(e) );
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Greeting findByID(long id) throws GreetingNotFoundException {
        if (!entityRepository.findById(id).isPresent()) {
            throw new GreetingNotFoundException("Greeting by ID(" + id + ") not found");
        }
        return entityRepository.findById(id).get();
    }

    @Override
    public Greeting create(Greeting greeting) throws GreetingAlreadyExistsException {
        if (greeting.getId() != null && entityRepository.findById(greeting.getId()).isPresent()) {
            throw new GreetingAlreadyExistsException("Greeting (" + greeting.toString() + ") already exists");
        }
        return entityRepository.save(greeting);
    }

    @Override
    @Transactional
    public Greeting update(Greeting greeting) throws GreetingNotFoundException {
        if (entityRepository.findById(greeting.getId()).isPresent()) {
            throw new GreetingNotFoundException("Greeting by ID(" + greeting.getId() + ") not found");
        }
        return entityRepository.save(greeting);
    }

    @Override
    @Transactional
    public void remove(long id) throws GreetingNotFoundException {
        if (entityRepository.findById(id).isPresent()) {
            throw new GreetingNotFoundException("Greeting by ID(" + id + ") not found");
        }
        entityRepository.deleteById(id);
    }
}