package com.kronos.newsapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kronos.newsapp.model.User;
import com.kronos.newsapp.repository.UserReponsitory;
import com.kronos.newsapp.tool.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserReponsitory userReponsitory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/api/addUser")
    public Code addUser(@RequestBody User user){
        try {
            userReponsitory.save(user);
            return Code.OK;
        }catch (Error e){
            log.info(String.valueOf(e));
            return Code.FAILED;
        }
    }

    @RequestMapping("/api/login")
    public Code userLogin(@RequestBody User user){
        try {
            User user_db = userReponsitory.findUserByUserName(user.getUserName());
            log.info(String.valueOf(user_db));
            if(user_db == null){
                return Code.NO_USER;
            }
            if(user_db.getUserPassword().equals(user.getUserPassword())){
                return Code.OK;
            }else {
                return  Code.PASS_ERR;
            }
        }catch (Error e){
            log.info(String.valueOf(e));
            return Code.FAILED;
        }
    }

    @RequestMapping("/api/getUser")
    public User getUser(@RequestBody User user){
        try {
            String username = user.getUserName();
            User user_db = userReponsitory.findUserByUserName(username);
            user = user_db;
        }catch (Error e){
            log.info(String.valueOf(e));
        }
        return user;
    }

    @RequestMapping("/api/updateUser")
    public Code updateUser(@RequestBody JSONObject param){
        try {
            JSONObject userJson = param.getJSONObject("user");
            String userNameBefore = param.getString("userNameBefore");
            Update update = new Update();
            update.set("userName",userJson.getString("userName"));
            update.set("userBirth",userJson.getString("userBirth"));
            update.set("userPassword",userJson.getString("userPassword"));
            update.set("userPhone",userJson.getString("userPhone"));
            mongoTemplate.updateMulti(new Query(Criteria.where("userName").is(userNameBefore)),update,User.class);
            return Code.OK;
        }catch (Error e){
            log.info(String.valueOf(e));
            return Code.FAILED;
        }
    }

}
