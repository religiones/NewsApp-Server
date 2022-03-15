package com.kronos.newsapp;

import com.kronos.newsapp.model.News;
import com.kronos.newsapp.model.User;
import com.kronos.newsapp.repository.NewsReponsitory;
import com.kronos.newsapp.repository.UserReponsitory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NewsAppApplication.class)
public class MongoTest {
    @Autowired
    private NewsReponsitory newsReponsitory;

    @Autowired
    private UserReponsitory userReponsitory;

    @Test
    public void insert(){
//        newsReponsitory.save(new News("1","2022","123","12313","123123","cqu"));
//        log.info(String.valueOf(newsReponsitory.findAll()));
        userReponsitory.save(new User("kronos","11","2022","206814","136"));
        log.info(String.valueOf(userReponsitory.findAll()));
    }

    @Test
    public void select(){
        log.info(String.valueOf(newsReponsitory.findAll()));
    }
}
