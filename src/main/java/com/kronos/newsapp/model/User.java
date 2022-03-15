package com.kronos.newsapp.model;

import com.kronos.newsapp.tool.AutoInc;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Accessors(chain = true)//实现链式编程
public class User {
    @Id
    @AutoInc
    private long userId;
    private String userName;
    private String userProfile;
    private String userBirth;
    private String userPassword;
    private String userPhone;

    public User(String userName, String userProfile, String userBirth, String userPassword, String userPhone) {
        this.userName = userName;
        this.userProfile = userProfile;
        this.userBirth = userBirth;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
    }
}
