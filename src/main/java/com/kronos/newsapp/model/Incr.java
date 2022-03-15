package com.kronos.newsapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: zjh
 * @Date: 2019-08-08
 * @Description: 表示每个集合记录自增的序列
 */
@Document
@Data
public class Incr {
    @Id
    private String id;
    private String collectionName;
    private Long incrId;
}
