package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Person;
import com.agh.introwertycznelosie.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Person save(Person person) { return personRepository.save(person);}

    public List<Person> get() {
        return personRepository.findAll();
    }

    public Person get(Long id) { return personRepository.getOne(id);}

    public void delete(Long id) { personRepository.deleteById(id);}

    public void delete(Person person) { personRepository.delete(person);}

    public Person findByMail(String mail) { return personRepository.findByMail(mail);}

}
