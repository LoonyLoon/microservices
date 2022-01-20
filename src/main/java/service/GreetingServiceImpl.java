package service;

import microservice.lesson1.Greeting;
import exception.GreetingAlreadyExistsException;
import exception.GreetingNotFoundException;
import repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private GreetingRepository Repository;

    @Override
    @Transactional
    public List<Greeting> browse() {
        List<Greeting> list = new ArrayList<>();
        Repository.findAll().forEach(e -> list.add(e) );
        return list;
    }

    @Override
    @Transactional
    public Greeting findByID(long id) throws GreetingNotFoundException {
        if (Repository.existsById(id)) {
            return Repository.findById(id).get();
        } else {
            throw new GreetingNotFoundException("Greeting by ID(" + id + ") not found");
        }
    }

    @Override
    @Transactional
    public Greeting create(Greeting greeting) throws GreetingAlreadyExistsException {
        return Repository.save(greeting);
    }

    @Override
    @Transactional
    public Greeting update(Greeting greeting) throws GreetingNotFoundException {
        if (Repository.existsById(greeting.getId())) {
            return Repository.save(greeting);
        } else {
            throw new GreetingNotFoundException("Greeting by ID(" + greeting.getId() + ") not found");
        }
    }

    @Override
    @Transactional
    public void remove(long id) throws GreetingNotFoundException {
        if (Repository.existsById(id)) {
            Repository.deleteById(id);
        } else {
            throw new GreetingNotFoundException("Greeting by ID(" + id + ") not found");
        }
    }
}


