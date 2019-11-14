package com.haipiao.imageservice.handler;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.imageservice.application.ImageController;
import com.haipiao.imageservice.dto.GetSecurityTokenRequest;
import com.haipiao.imageservice.dto.GetSecurityTokenResponse;
import com.haipiao.imageservice.property.ImageUploadProperties;
import static com.haipiao.common.enums.StatusCode.INTERNAL_SERVER_ERROR;
import static com.haipiao.common.enums.StatusCode.SUCCESS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityTokenHandler extends AbstractHandler<GetSecurityTokenRequest, GetSecurityTokenResponse> {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    ImageUploadProperties imageUploadProperties;

    public SecurityTokenHandler(
            SessionService sessionService){
        super(GetSecurityTokenResponse.class, sessionService);
    }

    @Override
    public GetSecurityTokenResponse execute(GetSecurityTokenRequest req) {
        System.out.println(imageUploadProperties.toString());
        // Aliyun document: https://help.aliyun.com/document_detail/100624.html?spm=a2c4g.11186623.6.656.1a6c44fdyhPHYR
        String stsEndpoint = imageUploadProperties.getSts().getStsEndpoint();
        String accessKeyId = imageUploadProperties.getSts().getAccessKeyId();
        String accessKeySecret = imageUploadProperties.getSts().getAccessKeySecret();
        String roleArn = imageUploadProperties.getSts().getRoleArn();
        String ossEndpoint = imageUploadProperties.getSts().getOssEndpoint();

        String roleSessionName = "haipiao-session-"+req.getLoggedInUserId();
        logger.debug("role session name: {}", roleSessionName);
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                "                \"oss:*\"\n" +
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:*\" \n" +
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        try {
            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "", "Sts", stsEndpoint);
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy); // 若policy为空，则用户将获得该角色下所有权限
            request.setDurationSeconds(3600L); // 设置凭证有效时间
            AssumeRoleResponse response = client.getAcsResponse(request);
            String ossAccessKeyId = response.getCredentials().getAccessKeyId();
            String ossAccessKeySecret = response.getCredentials().getAccessKeySecret();
            String securityToken = response.getCredentials().getSecurityToken();
            String expireTime = response.getCredentials().getExpiration();

            logger.debug("access key id: {}", ossAccessKeyId);
            logger.debug("expire time: {}", response.getCredentials().getExpiration());
            logger.debug("RequestId: {}", response.getRequestId());
            GetSecurityTokenResponse resp = new GetSecurityTokenResponse(SUCCESS);
            GetSecurityTokenResponse.Data data = new GetSecurityTokenResponse.Data();
            data.setEndpoint(ossEndpoint);
            data.setAccessKeyID(ossAccessKeyId);
            data.setAccessKeySecret(ossAccessKeySecret);
            data.setSecurityToken(securityToken);
            data.setExpireTime(expireTime);
            resp.setData(data);
            return resp;
        } catch (ClientException e) {
            logger.debug("error code: {}", e.getErrCode());
            logger.debug("error message: {}", e.getErrMsg());
            logger.debug("requestId: {}", e.getRequestId());
            GetSecurityTokenResponse resp = new GetSecurityTokenResponse(INTERNAL_SERVER_ERROR);
            return resp;
        }
    }
}
