package com.kronos.newsapp.repository;

import com.kronos.newsapp.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NewsReponsitory extends MongoRepository<News,String> {
    /**
     *
     * @param ctime 发布时间
     * @return 新闻List
     */
    List<News> findAllByCtime(String ctime);

}
