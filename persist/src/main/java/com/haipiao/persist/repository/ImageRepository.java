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
    Image findFirstByArticleId(Integer articleId, int positionIndex);

    /**
     * 根据albumId查询文章的图片
     * @param albumId
     * @param positionIndex
     * @param size
     * @return
     */
    @Query(value = "select i.external_url " +
            "from album_article_relation aar " +
            "join article a on aar.article_id = a.article_id " +
            "join image i on a.article_id = i.article_id and i.position_index = ?2 " +
            "where aar.album_id = ?1 " +
            "order by a.create_ts desc limit ?3",nativeQuery = true)
    List<String> findExternalUrlsByAlbumId(Integer albumId, int positionIndex, int size);

}
