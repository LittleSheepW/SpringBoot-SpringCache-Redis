# SpringBoot-SpringCache-Redis


本项目使用使用SpringBoot2.13+JPA+SpringCache+Redis，主要目的是在SpringBoot项目中集成SpringCache+Redis。
1、首先在Mysql中创建名称为sunshine数据库，当然也可以修改
application.properties中spring.datasource.url连接地址为你自己的。
2、保证Mysql和Redis服务处于启动状态，如果你的Redis有密码，
在application.propert文件中修改spring.redis.password=你的密码。
3、点击CloudStudyApplication启动类启动项目，数据表会自动进行创建，不过数据需要自行添加。
4、使用postman进行测试，restful风格url。

如果你有更好的建议请联系我。
