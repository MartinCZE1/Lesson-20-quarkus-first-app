package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.json.JsonObjectBuilder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dao.PersonRepository;
import org.acme.model.Person;

import java.util.List;
import java.util.Random;

@Path("/persons")
public class PersonResource {
    @Inject
    PersonRepository personRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response list() {
        List<Person> persons = personRepository.listAll();
        return Response.ok().entity(persons).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Person persons = personRepository.findById(id);
        return Response.ok().entity(persons).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        personRepository.deleteById(id);
        return Response.ok().entity("ok").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(Person person) {
        person.setId(0l);
        if (person.getName() != null && person.getAge() > 0) {
            personRepository.persist(person);
            return Response.ok().entity("ok").build();
        } else {
            return Response.status(400).entity("Person must have attributes \"name\" and \"age\".").build();
        }

    }
}
