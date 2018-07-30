# User API

## Registrition and login

### Check email

**url**: /email/check

**method**: POST

**request body**:
```json
{
  "email": "<string(required)>"
}
```
**response**:
- 204 No content
- 409 Conflict

### Check nickname

**url**: /username/check

**method**: POST

**request body**:
```json
{
  "user_name": "<string(required)>"
}
```
**response**:
- 204 No content
- 409 Conflict

### Get TC

**url**: /tc/{version}

**method**: GET

**request body**: N/A

**response**:
- 200 OK
  ```json
  {
    "tc": "<string(optional)html-format>"
  }
  ```
- 404 Not Found
  {}
  
### register
  
**url**: /user/register

**method**: POST

**request body**:
```json
{
  "email": "<string(required)>",
  "username": "<string(required)>",
  "password_digest": "<string(required)>",
  "tc_accepted": "<boolean(required)>"
}
```
**response**:
- 201 Created
  Header: Set-Cookie
- 409 Conflict

### User login

**url**: /url/login

**method**: POST

**request header**:
  User-Agent

**request body**:
```json
{
  "email": "<string(requied)>",
  "password_digest": "<string(required)>",
  "latitude": "<double(optional)>",
  "longtitude": "<double(optional)>",
  "client_hardware": "<string(optional)>"
}
```
**response**:
- 204 No Content
  Header: Set-Cookie
- 404 Not Found
- 400 Bad Request: invalid latitude and(or) longtitude

### Enter tourist mode

**url**: /user/login/temporary

**method**: POST

**request body**:
```json
{
  "latitude": "<double(optional)>",
  "longtitude": "<double(optional)>",
  "client_hardware": "<string(optional)>"
}
```
**response body**:
- 204 No Content
  Header: Set-Cookie
- 400 Bad Request: invalid latitude and(or) longtitude
