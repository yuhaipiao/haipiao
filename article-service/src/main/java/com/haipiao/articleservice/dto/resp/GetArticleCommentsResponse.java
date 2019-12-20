package com.haipiao.articleservice.dto.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

import java.util.List;

/**
 * @author wangjipeng
 */
public class GetArticleCommentsResponse extends AbstractResponse<GetArticleCommentsResponse.Data> {

    public GetArticleCommentsResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data{

        @SerializedName("comments")
        private List<Comment> comments;

        @SerializedName("total_count")
        private int totalCount;

        @SerializedName("cursor")
        private String cursor;

        @SerializedName("more_to_follow")
        private boolean moreToFollow;

        public static class Comment{

            @SerializedName("id;")
            private int id;

            @SerializedName("content")
            private String content;

            @SerializedName("commented_time")
            private long commentedTime;

            @SerializedName("likes")
            private int likes;

            @SerializedName("commenter")
            private Commenter commenter;

            @SerializedName("replies_count")
            private int repliesCount;

            @SerializedName("replies")
            private List<Replie> replies;

            public Comment() {
            }

            public Comment(int id, String content, long commentedTime, int likes, Commenter commenter, int repliesCount, List<Replie> replies) {
                this.id = id;
                this.content = content;
                this.commentedTime = commentedTime;
                this.likes = likes;
                this.commenter = commenter;
                this.repliesCount = repliesCount;
                this.replies = replies;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCommentedTime() {
                return commentedTime;
            }

            public void setCommentedTime(long commentedTime) {
                this.commentedTime = commentedTime;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public Commenter getCommenter() {
                return commenter;
            }

            public void setCommenter(Commenter commenter) {
                this.commenter = commenter;
            }

            public int getRepliesCount() {
                return repliesCount;
            }

            public void setRepliesCount(int repliesCount) {
                this.repliesCount = repliesCount;
            }

            public List<Replie> getReplies() {
                return replies;
            }

            public void setReplies(List<Replie> replies) {
                this.replies = replies;
            }

            public static class Commenter{

                @SerializedName("id")
                private int id;

                @SerializedName("profile_image_url")
                private String profileImageUrl;

                @SerializedName("name")
                private String name;

                public Commenter() {
                }

                public Commenter(int id, String profileImageUrl, String name) {
                    this.id = id;
                    this.profileImageUrl = profileImageUrl;
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getProfileImageUrl() {
                    return profileImageUrl;
                }

                public void setProfileImageUrl(String profileImageUrl) {
                    this.profileImageUrl = profileImageUrl;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class Replie{

                @SerializedName("replier")
                private Replier replier;

                @SerializedName("content")
                private String content;

                public Replie() {
                }

                public Replie(Replier replier, String content) {
                    this.replier = replier;
                    this.content = content;
                }

                public Replier getReplier() {
                    return replier;
                }

                public void setReplier(Replier replier) {
                    this.replier = replier;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public static class Replier{

                    @SerializedName("id")
                    private int id;

                    @SerializedName("name")
                    private String name;

                    public Replier() {
                    }

                    public Replier(int id, String name) {
                        this.id = id;
                        this.name = name;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }
        }

        public Data() {
        }

        public Data(List<Comment> comments, int totalCount, String cursor, boolean moreToFollow) {
            this.comments = comments;
            this.totalCount = totalCount;
            this.cursor = cursor;
            this.moreToFollow = moreToFollow;
        }

        public List<Comment> getComments() {
            return comments;
        }

        public void setComments(List<Comment> comments) {
            this.comments = comments;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public String getCursor() {
            return cursor;
        }

        public void setCursor(String cursor) {
            this.cursor = cursor;
        }

        public boolean isMoreToFollow() {
            return moreToFollow;
        }

        public void setMoreToFollow(boolean moreToFollow) {
            this.moreToFollow = moreToFollow;
        }
    }
}
