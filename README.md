### 0.工程说明
该工程使用SpringCloud OAuth2组件实现了一个简单的OAuth2认证服务，默认提供了两种token的生成方式，一种是默认方式，另一种是JWT格式的token,通过将注释/放开不同的类来得到不同的token类型。
- AuthorizationServerConfig 将该类的注释取消，则启用的是【使用简单的token模式】

- SimpleAuthorizationServerConfig 将该类的注释取消，则启用的是【使用JWT Token模式】

如果不加任何限制，token的有效期为12小时，refresh_token的有效期为7天

### 1.使用简单的token模式
- password授权模式
> http://localhost:8080/oauth/token?username=admin&password=123456&grant_type=password&scope=app&client_id=client&client_secret=secret
> 
返回结果
```
{
    "access_token": "66a0b474-e178-460b-b91f-6ee94292fe34",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "app"
}
```
- client_credentials 模式
> http://localhost:8080/oauth/token?grant_type=client_credentials&scope=app&client_id=client&client_secret=secret
>
返回结果
```
{
    "access_token": "049d59a9-f529-4333-93b7-269cb026552f",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "app"
}
```
### 2.使用JWT token
- password授权模式
> http://localhost:8080/oauth/token?username=admin&password=123456&grant_type=password&scope=app&client_id=client&client_secret=secret
> 
返回结果
```
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjQ1MzgxNTIsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiMjE2ZTIxNmMtYmViOC00MjA4LWE2NzYtYzJlYjEzYzg3MmU1IiwiY2xpZW50X2lkIjoiY2xpZW50Iiwic2NvcGUiOlsiYXBwIl19.MVB6wbOT8RGaOAR0tpO9HpqBUw_EpeNJCAl6cv6h1hV_5Mnd8DBVR_br4CDR_lNLTA2iFohOtVluVzXVU9xx7cOUdJDYlZ4_Rld1nMaXtzxxvBe0-DtUP8gWA0MwW2Hej5JJ3SpIKXGi12bA039rQ_l4ZLbB1PFteIg9SiNMb6aw3PArSb1jZnN0Suo6Cn-nxUXo-ne6qbtEyC5ywl-mrP_XO4ZXjPBSenVy6so4XA-tdRMG3xM8-Jnwf5YV3d-qGax5jfBBpxLrYDCQiJH8MdhGk7rx0eZsU1ViCAC0lyOqFS5GFTMuB8E94TqpyJuk8XiQlmDZuABg9jv1XhJy4A",
    "token_type": "bearer",
    "expires_in": 43197,
    "scope": "app",
    "user_name": "admin",
    "jti": "216e216c-beb8-4208-a676-c2eb13c872e5"
}
```
- client_credentials 模式
> http://localhost:8080/oauth/token?grant_type=client_credentials&scope=app&client_id=client&client_secret=secret
>
返回结果
```
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwiZXhwIjoxNTI0NTM4NDkxLCJqdGkiOiIyYjRkYjNhOS05MzY5LTQ1MWUtODkyZi03ODc0MDJiYjI1ZGIiLCJjbGllbnRfaWQiOiJjbGllbnQifQ.iQb3WU7RLZ28dbGvYKMOAeHzGWu8a6aS5t5DR6biG5CApGmvWr9VbaGaBuf1B-FYW89MRqPdPxlbkpW_1b02XDm54xsw-vT9tN37Bpz3Eu7LcIzOYW61g_SRI4nHBlPaNiUWY48GyZah6ZGIP9nOkAxqVz6DQeFdrqp4TSj7jfDP7kexHLCYnsNndFv4IC1iyB_oc9u1RzJ_FTLVSgte0UL-wHy0VyizkcVbvd8Q1Rda_0uU39GVkSvYZ8-NDDsmXQHWhh1sHRqumGXeMFM60SAgOkygO1TGnLCb9mAknsHgqmmGu5GhvBcq5ILrPJbvRLXQQ0bgqYfyuaYNoDPAiA",
    "token_type": "bearer",
    "expires_in": 43175,
    "scope": "app",
    "jti": "2b4db3a9-9369-451e-892f-787402bb25db"
}
```

参考资料
- [第七章 SpringCloud OAuth2认证中心-搭建认证中心 ](https://blog.csdn.net/crazycoder2010/article/details/78464883)