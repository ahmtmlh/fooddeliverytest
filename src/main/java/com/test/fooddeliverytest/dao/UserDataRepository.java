package com.test.fooddeliverytest.dao;

import com.test.fooddeliverytest.model.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDataRepository extends CrudRepository<UserData, Long> {
    List<UserData> findAllByNameAndSurname(String name, String surname);
    UserData findByEmail(String email);
    List<UserData> findAllByNameStartsWith(String name);
}
