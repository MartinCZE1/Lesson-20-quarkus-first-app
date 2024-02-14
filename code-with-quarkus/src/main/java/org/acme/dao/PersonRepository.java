package org.acme.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Person;

import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
    public List<Person> listByName(String name) {
        return find("name", name).list();
    }
    public Person listById(Long id){
        return findById(id);
    }

}
