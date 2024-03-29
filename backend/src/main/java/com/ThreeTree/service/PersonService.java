package com.ThreeTree.service;

import com.ThreeTree.config.JwtService;
import com.ThreeTree.dao.PersonRepository;
import com.ThreeTree.dto.NewPersonRequest;
import com.ThreeTree.model.Person;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PersonService {
    private PersonRepository personRepository;
    private JwtService jwtService;

    @Autowired
    public PersonService(PersonRepository personRepository, JwtService jwtService) {
        this.personRepository = personRepository;
        this.jwtService = jwtService;
    }

    public List<Person> getCustomers() {
        return personRepository.findAll();
    }

    public Person getCustomerById(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    public void saveCustomer(NewPersonRequest request) {
        Person person = new Person();
        person.setFirstName(request.FirstName());
        person.setLastName(request.LastName());
        person.setEmail(request.email());
        person.setAddress(request.address());
        person.setPhoneNumber(request.phoneNumber());
        personRepository.save(person);
    }

    public void updateCustomerById(Long id, Person person) {
        Person personToUpdate = personRepository.findById(id).orElseThrow();
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        personToUpdate.setEmail(person.getEmail());
        personRepository.save(personToUpdate);
    }

    public void deleteCustomerById(Long id) {
        personRepository.deleteById(id);
    }


    public Optional<Person> getPersonDetailsFromToken(String token) {
            String plainToken = token.substring(7);
            String userEmail = jwtService.extractClaim(plainToken, Claims::getSubject);
            Optional<Person> person = personRepository.findByEmail(userEmail); // Adjust based on your repository method
            return person;
    }

}
