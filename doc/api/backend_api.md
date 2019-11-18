# HaiPiao API 文档 

## 文档规范

### API标题 (H3)

一段对API的文字描述 （正文）

**URL**： `/example`  
**Method**: GET  
**Required headers**: `Cookie`

**Parameters**:  

| Name | Type        | Required | Description                         |
| ---- | ----------- | -------- | ----------------------------------- |
| example_para | String  | Yes      | 必要的文字描述 |

**Request body**
```json
{
  ... 
}
```

**Response body** :

**Success**

```json
{
  "status_code": "SUCCESS",
  "data": {
    ...
  }
}
```

**Fail**

```json
{
  "status_code": <String>,
  "error_message": <String>
}
```

**Possible error codes**: 

- `SOME_ERROR`: 必要的文字描述。


## API 

### 1. 上传图片

先调用阿里云的OSS服务，上传到bucket中，同时拿到返回的url。然后再用拿到的url来调用海漂的上传图片API

### 2. 获取验证码

客户端为当前用户设备请求一个验证码，请求中需包含当前设备（手机）号码。服务器端返回一个6位验证码，一分钟内有效。

### 3 . 获取验证码

客户端为当前用户设备请求一个验证码，请求中需包含当前设备（手机）号码。服务器端返回一个6位验证码，一分钟内有效。

### 4. 验证验证码并登陆/注册

客户端请求服务器验证 1. 用户登陆（”login”）或 2. 用户注册（”registration”）或 3. 用户修改手机号（”update_cell”），请求中需包含当前用户手机号码。服务器端需完成验证码验证，如果验证类型为1（用户注册）， 服务端需为请求中的手机号码创建一个新的账户， 如果账户已存在需返回相应错误码。服务器端需返回一个session token。

### 5. 上传或修改用户头像

客户端上传一个图片作为用户头像，请求中需包含session token。

### 6 .设置个人信息

客户端上传用户设置的个人基本信息：性别，生日等，请求中需包含session token。 性别，生日，隐私

### 7. 获取感兴趣的主题 

客户端向服务器端请求一个固定长度的备选“感兴趣的主题”列表。该列表应结合之前用户设置的个人基本信息生成，请求中需包含session token。建议缓存，每次启动应用的时候再fetch

**URL**: `/category?type=[hot|misc|all]`

**Method**: GET

**Required headers**: `Cookie: session-token=<token>`

**Parameters**:  

| Name | Type        | Required | Description                         |
| ---- | ----------- | -------- | ----------------------------------- |
| type | String Enum | Yes      | `hot` : 热门 `misc`:其他 `all`:所有 |

**Response body** :

**Success**

```json
{
  "status_code": "SUCCESS",
  "data": {
    "all": [
    	{
      	  "id": <Interger>, // 分类id ex: 12345
      	  "name": <String>, // 分类名称 ex: "旅游"
      	  "cover_image_url": <String>, // 阿里云OSS路径
    	}, ...
    ],
    "hot": [...], 
    "misc": [...]
  }
}
```

**Fail**

```json
{
  "status_code": <String>,
  "error_message": <String>
}
```

**Possible error codes**: 

- `INTERNAL_SERVER_ERROR`: 未知服务器端错误。



### 8. 设置感兴趣的主题 

客户端上传用户设置的5+ 个感兴趣主题，请求中需包含session token。

**URL**: `/user/category`

**Method**: POST

**Required headers**: `Cookie: session-token=<token>`

**Request body**:

```json
{
  "categories": [
    <Integer>, ... 
  ]
}
```

**Response body**:

**Success**:

```json
{
  "status_code": "SUCCESS"
}
```

**Fail**

```json
{
  "status_code": <String>,
  "error_message": <String>
}
```



### 9. 关注用户

### 10. 获取推荐文章列表

客户端向服务器端请求一个“推荐文章列表”，请求中需包含session token。服务端交由后端推荐算法生成推荐文章列表。

discover = 发现

nearby = 附近

article_related = 与当前文章相关

topic_related = 与当前话题相关

topic_related_latest = 与当前话题相关且最新 

### 11.  我的分类列表

客户端向服务器段请求当前用户所选择的“感兴趣主题”列表，请求中需包含session token。

### 12. 点赞

当前用户为一篇文章点赞，请求中需包含session token。

### 13. 取消点赞

当前用户取消之前的点赞， 请求中需包含session token。

### 14. 不感兴趣

当前用户标记一篇文章为“不感兴趣”，请求中需包含session token。

### 15. 获取关注用户的更新

检查当前用户所关注的用户是否有更新

check：检查是否有更新

pull： 获取所有更新信息

### 16. 通知是否有更新

检查当前用户是否有未读的系统通知

check：检查是否有更新

pull： 获取所有更新信息

### 17. 加载文章

包含是否本人已点赞和已收藏

### 18.  获取用户分组

获取当前用户所创建的所有“分组”

### 19. 获取默认分组

获取系统的默认分组

### 20. 删除分组

### 21. 新建分组

### 22. 获取热搜列表（前N）（16）

### 23. 自动补全 （17）

### 24. 搜索文章 

### 25. 搜索话题

### 26. 搜索用户 (21)

### 27. 举报违规文章 (21)

### 28. 设置关注用户的分组

### 29. 推荐关注的用户

### 30. 获取用户信息

### 31. 获取用户所有文章

###  32. 更改以关注用户的分组

### 33. 取消关注

### 34. 获取用户的收藏文章列表

### 35. 获取用户专辑列表

### 36. 获取用户粉丝列表

### 37. 举报用户

被举报用户ID，原因，图片

### 38. 加载评论列表

评论者昵称，评论时间，前2条回复，点赞数，是否已为评论点赞，回复总数

### 39. 加载一条评论和回复 

回复内容，回复者昵称，回复时间，回复的点赞数，被回复的人

### 40. 收藏文章到专辑

 参数：文章ID，专辑ID，

### 41. 添加专辑

### 42. 获取专辑中文章列表

### 43. 移动文章到新的专辑

### 44. 删除文章

### 45. 编辑专辑

 参数：标题，简介，可见性

### 46.  删除专辑

### 47. 获取已关注用户列表

### 48. 获取用户关注的专辑列表

### 49. 获取用户关注的话题列表

### 50. 获取推荐当前用户关注的话题列表

标签名称，参与人数

### 51. 获取话题信息

### 52. 创建新文章

### 53. APP报错

### 54. 登出

### 55. 获取用户全部信息

头像，海漂ID，性别，是否已完成实名认证，常住地，组织，生日，个性签名，鲸鱼等级

### 56. 修改用户信息

name，gender， location， birthday， signature

### 57. 获取账号相关信息

### 58. 获取用户隐私设置

### 59. 修改用户隐私设置

### 60. 获取最新用户协议

### 61. 获取N条热门文章列表

### 62. 获取达人列表

### 63. 获取赞的N条通知

### 64. 获取收藏的N条通知

### 65. 获取评论的N条通知

### 66. 获取@的N条通知

### 67. 获取新增关注的N条通知

### 68. 获取系统通知的N条通知

### 69. 获取私信的N条通知



