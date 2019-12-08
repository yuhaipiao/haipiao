package com.haipiao.userservice.req;

import com.haipiao.common.req.AbstractRequest;

/**
 * @author wangjipeng
 */
public class FolloweeUserRequest extends AbstractRequest {

    private int groupId;

    private int followeeId;

    public FolloweeUserRequest() {
    }

    public FolloweeUserRequest(int groupId, int followeeId) {
        this.groupId = groupId;
        this.followeeId = followeeId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getFolloweeId() {
        return followeeId;
    }

    public void setFolloweeId(int followeeId) {
        this.followeeId = followeeId;
    }
}
