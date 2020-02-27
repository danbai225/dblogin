<p align="center">
<img src="http://img.p00q.cn:222/2019/10/25/b8fc388600d73.ico" alt="db" width="100">
</p>

# 淡白登陆注册

只是一个简单纯粹的登陆注册中心.只做一件事就是登陆和注册.用于几个小项目间不必重复注册的问题.

# 搭建服务端

git项目到本地 修改项目application配置 sql建表 构建项目 运行

# 客服端

## sping项目可直接添加依赖
```
<dependency>
  <groupId>cn.p00q.dblogin</groupId>
  <artifactId>client</artifactId>
  <version>1.0</version>
</dependency>
```
在application配置中添加
```
danbai:
  dblogin:
    passwordManagement: "DanBai" #没用口令可不要
    serviceURL: "http://127.0.0.1:8085"
```
在需要使用的地方注入 如:
```
    @Autowired
    DBlogin dBlogin;
```

## 其他项目 使用http接入

- /login 登陆
- /logout 退出登陆
- /tokenTime token过期时间
- /register 注册
- /username token获取用户名
- /delete 删除用户
- /user 查询用户是否存在