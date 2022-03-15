package com.kronos.newsapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class News {
    @Id
    private String id;
    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String source;
}
