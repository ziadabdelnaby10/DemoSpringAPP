package com.example.demobackend.dao;

import com.example.demobackend.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static final List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.name()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID uuid) {
        return DB.stream()
                .filter(person -> person.id().equals(uuid))
                .findFirst();
    }

    @Override
    public int deletePerson(UUID uuid) {
        Optional<Person> personMaybe = selectPersonById(uuid);
        if (personMaybe.isEmpty())
            return 0;
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID uuid, Person personToUpdate) {
        return selectPersonById(uuid)
                .map(person -> {
                    int indexOfPersonToUpdate = DB.indexOf(person);
                    if (indexOfPersonToUpdate >= 0) {
                        DB.set(indexOfPersonToUpdate, new Person(uuid , person.name()));
                        return 1;
                    }
                    return -1;
                }).orElse(0);
    }
}
