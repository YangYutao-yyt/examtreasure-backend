package com.yyt.examtreasure.esdao;

import com.yyt.examtreasure.model.dto.question.QuestionEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 题目 ES 操作
 *
 */
//类似于数据库的dao层，我们继承ElasticsearchRepository，第一个指定关联数据的类型，第二个id的类型
public interface QuestionEsDao extends ElasticsearchRepository<QuestionEsDTO, Long> {

    //只要单纯这么命名，自动会按照userId过滤，自动映射可以看官方文档
    List<QuestionEsDTO> findByUserId(Long userId);
}