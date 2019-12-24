package com.haipiao.persist.repository;

import com.haipiao.persist.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

    /**
     * 获取当前点赞数最多的6篇文章
     * @param cursor
     * @param limit
     * @return
     */
    @Query(value = "select * from article order by likes desc limit ?1, ?2", nativeQuery = true)
    List<Article> findArticlesOrderByLikes(int cursor, int limit);

    /**
     * 获取当前时间和前某小时之间点赞数最多的前6篇文章
     * @param beginTime
     * @param lastTime
     * @param cursor
     * @param limit
     * @return
     */
    @Query(value = "select * from article where create_ts between ?1 and ?2 order by likes desc limit ?3, ?4", nativeQuery = true)
    List<Article> findByCreateTsBetween(String beginTime, String lastTime, int cursor, int limit);
}
