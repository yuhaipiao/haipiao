package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.application.ArticleController;
import com.haipiao.articleservice.dto.req.CreateArticleRequest;
import com.haipiao.articleservice.dto.resp.CreateArticleResponse;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.common.util.image.resize;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.entity.ArticleTopic;
import com.haipiao.persist.entity.Image;
import com.haipiao.persist.entity.Tag;
import com.haipiao.persist.entity.Topic;
import com.haipiao.persist.enums.ImageStatus;
import com.haipiao.persist.repository.ArticleRepository;
import com.haipiao.persist.repository.ArticleTopicRepository;
import com.haipiao.persist.repository.ImageRepository;
import com.haipiao.persist.repository.TagRepository;
import com.haipiao.persist.repository.TopicRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CreateArticleHandler extends AbstractHandler<CreateArticleRequest, CreateArticleResponse> {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ArticleTopicRepository articleTopicRepository;

    public CreateArticleHandler(
            SessionService sessionService,
            ArticleRepository articleRepository,
            TopicRepository topicRepository,
            ArticleTopicRepository articleTopicRepository,
            ImageRepository imageRepository,
            TagRepository tagRepository){
        super(CreateArticleResponse.class, sessionService);
        this.articleRepository = articleRepository;
        this.topicRepository = topicRepository;
        this.articleTopicRepository = articleTopicRepository;
        this.imageRepository = imageRepository;
        this.tagRepository = tagRepository;
    }

    private void validate(CreateArticleRequest req) throws AppException {
        if (StringUtils.isBlank(req.getTitle())) {
            throw new AppException(StatusCode.BAD_REQUEST, "title must not be empty");
        }
        if (StringUtils.isBlank(req.getText())) {
            throw new AppException(StatusCode.BAD_REQUEST, "text body must not be empty");
        }
        if ((req.getImages() == null || req.getImages().length == 0) && (req.getVideoUrls() == null || req.getVideoUrls().length == 0)) {
            throw new AppException(StatusCode.BAD_REQUEST, "must have images or videos");
        }
        if ((req.getImages() != null && req.getImages().length > 0) && (req.getVideoUrls() != null && req.getVideoUrls().length > 0)) {
            throw new AppException(StatusCode.BAD_REQUEST, "only one of images and videos");
        }
        if (req.getImages() != null) {
            for (CreateArticleRequest.Image img : req.getImages()) {
                if (img.getExternalUrl() == null || img.getExternalUrl() == "") {
                    throw new AppException(StatusCode.BAD_REQUEST, "external url must be set in image");
                }
                if (img.getHashDigest() == null || img.getHashDigest().length == 0) {
                    throw new AppException(StatusCode.BAD_REQUEST, "hash digest must be set in image");
                }
                if (img.getTags() != null && img.getTags().length > 0) {
                    for (com.haipiao.articleservice.dto.common.Tag tag : img.getTags()) {
                        if (tag.getText() == null || tag.getText() == "") {
                            throw new AppException(StatusCode.BAD_REQUEST, "text must be set in image tag");
                        }
                        if (tag.getX() <= 0 || tag.getY() <= 0) {
                            throw new AppException(StatusCode.BAD_REQUEST, "x and y must be greater than 0 in image tags");
                        }
                    }
                }
            }
        }
        if (req.getTopics() != null && req.getTopics().length > 0) {
            for (com.haipiao.articleservice.dto.common.Topic topic : req.getTopics()) {
                if (topic.getName() == null && topic.getName() == "") {
                    throw new AppException(StatusCode.BAD_REQUEST, "name must be set in topic");
                }
                if (topic.getId() == 0) {
                    throw new AppException(StatusCode.BAD_REQUEST, "topic ID must be set in topic");
                }
            }
        }

        // TODO: validate video related
    }

    // TODO: use transactional
    @Override
    public CreateArticleResponse execute(CreateArticleRequest req) throws AppException {
        // validate may throw AppException
        validate(req);

        Article article = new Article();
        article.setTitle(req.getTitle());
        article.setTextBody(req.getText());
        article.setLikes(0);
        article.setCollects(0);
        article.setShares(0);
        article.setAuthorId(req.getLoggedInUserId());

        if (req.getImages() != null && req.getImages().length != 0) {
            article.setType(1);
        } else if (req.getVideoUrls() != null && req.getVideoUrls().length != 0) {
            article.setType(2);
        }
        article = articleRepository.save(article);
        int articleId = article.getArticleId();

        // handle images and tags
        Integer[] imageIdArray = new Integer[req.getImages().length];
        if (req.getImages() != null && req.getImages().length != 0) {
            CreateArticleRequest.Image[] images = req.getImages();
            for (int i = 0; i < images.length; i++) {
                Image image = new Image();
                image.setArticleId(article.getArticleId());
                image.setPositionIdx(i);
                image.setExternalUrl(images[i].getExternalUrl());
                resize imageResize = new resize(images[i].getExternalUrl());
                image.setExternalUrlLarge(imageResize.resizeToLarge());
                image.setExternalUrlMedium(imageResize.resizeToMedium());
                image.setExternalUrlSmall(imageResize.resizeToSmall());
                image.setHashDigest(images[i].getHashDigest());
                // TODO: revisit how to handle image status.
                image.setStatus(ImageStatus.PUBLISHED);
                // Other fields will be set when creating the article.
                image = imageRepository.save(image);
                imageIdArray[i] = image.getImageId();
                com.haipiao.articleservice.dto.common.Tag[] tags = images[i].getTags();
                if (tags != null) {
                    for (int j = 0; j < tags.length; j++) {
                        Tag tag = new Tag();
                        tag.setImageId(image.getImageId());
                        tag.setText(tags[j].getText());
                        tag.setX(tags[j].getX());
                        tag.setY(tags[j].getY());
                        tagRepository.save(tag);
                    }
                }
            }
        } else if (req.getVideoUrls() != null && req.getVideoUrls().length != 0) {
            // TODO: create article-video relation
        }

        Iterable<Image> images = imageRepository.findAllById(Arrays.asList(imageIdArray));
        images.forEach(image -> {
            image.setStatus(ImageStatus.PUBLISHED);
            imageRepository.save(image);
        });

        com.haipiao.articleservice.dto.common.Topic[] topics = req.getTopics();
        if (topics != null) {
            for (int i = 0; i < topics.length; i++) {
                Topic topic = new Topic();
                ArticleTopic articleTopic = new ArticleTopic();
                topic.setTopicName(topics[i].getName());
                topic = topicRepository.save(topic);
                articleTopic.setArticleId(articleId);
                articleTopic.setTopicId(topic.getTopicId());
                articleTopicRepository.save(articleTopic);
            }
        }

        CreateArticleResponse resp = new CreateArticleResponse(StatusCode.SUCCESS);
        CreateArticleResponse.Data data = new CreateArticleResponse.Data();
        data.setId(article.getArticleId());
        resp.setData(data);
        return resp;
    }
}
