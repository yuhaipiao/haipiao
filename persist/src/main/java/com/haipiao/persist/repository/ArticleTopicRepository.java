package com.haipiao.persist.repository;

import java.util.List;

import com.haipiao.persist.entity.ArticleTopic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ArticleTopicRepository extends CrudRepository<ArticleTopic, Integer> {
    List<ArticleTopic> findAllByArticleId(int articleID);
    List<ArticleTopic> findAllByTopicId(int topicID);

    /**
     * 根据话题id获取对应文章id
     * @param topicIds
     * @param beginTime
     * @param lastTime
     * @param cursor
     * @param limit
     * @return
     */
    @Query(value = "select article_id from article_topic_relation where create_ts between ?1 and ?2 and topic_id in (?3) limit ?4,?5", nativeQuery = true)
    List<Integer> findByTopicIdIn(List<Integer> topicIds, String beginTime, String lastTime, int cursor, int limit);

    /**
     * 获取特定时间段内热度高的话题下部分文章
     * @param beginTime
     * @param lastTime
     * @param cursor
     * @param limit
     * @return
     */
    @Query(value = "select article_id from article_topic_relation where create_ts BETWEEN ?1 and ?2 and topic_id in (\n" +
            "select topic_id from article_topic_relation GROUP BY topic_id ORDER BY count(article_id) desc)\n" +
            "limit ?3,?4", nativeQuery = true)
    List<Integer> getArticlesOfHotTopic(String beginTime, String lastTime, int cursor, int limit);
}
