package com.example.demobackend.dao;

import com.example.demobackend.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id , Person person);

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id , person);
    }

    List<Person> selectAllPeople();
    Optional<Person> selectPersonById(UUID uuid);
    int deletePerson(UUID uuid);
    int updatePerson(UUID uuid , Person person);
}
