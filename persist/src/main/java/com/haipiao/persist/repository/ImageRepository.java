package com.haipiao.persist.repository;

import java.util.List;

import com.haipiao.persist.entity.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer> {
    List<Image> findByArticleIdOrderByPositionIdxAsc(int articleID);

    /**
     * 根据articleId与positionIndex查询
     *
     * @param articleId
     * @param positionIndex
     * @return
     */
    @Query(value = "select * from image where article_id = ?1 and position_index = ?2", nativeQuery = true)
    Image findFirstByArticleId(int articleId, int positionIndex);
}
