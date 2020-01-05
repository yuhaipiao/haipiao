package com.haipiao.persist.repository;

import com.haipiao.persist.entity.Album;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wangshun
 */
public interface AlbumRepository extends CrudRepository<Album, Integer> {

    @Query(value = "select a.* " +
            "from album a " +
            "join user_album_relation uar on a.album_id == uar.album_id " +
            "where uar.follower_id = 1 " +
            "order by uar.create_ts desc",nativeQuery = true)
    Page<Album> findArticlesByfollowerIdForPage(Integer followerId, Pageable pageable);

}
