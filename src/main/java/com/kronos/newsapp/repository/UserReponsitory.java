package com.kronos.newsapp.repository;

import com.kronos.newsapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserReponsitory extends MongoRepository<User,Long> {
    User findUserByUserName(String userName);
}
