package com.springx.mypro.repository;

import com.springx.mypro.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);
    User findByUserNameAndPassword(String userName, String password);

    void deleteByUserName(String name);
}