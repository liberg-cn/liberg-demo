# liberg-demo

这是使用Liberg.jar和LibergCoder插件（Idea版）的演示项目。

1. 需要在Idea中先安装[LibergCoder插件](https://github.com/liberg-cn/liberg-demo/blob/master/lib/LibergCoder_1.2.0.jar)。

2. 根据需要修改application.properties中的默认数据库配置。

   ```properties
   server.port=8080
   spring.application.name=liberg-demo
   spring.datasource.url=jdbc:mysql://localhost:3306/
   spring.datasource.name=liberg_demo
   spring.datasource.username=root
   spring.datasource.password=
   ```

3. 运行项目。

   Postman中发送请求：`POST  http://localhost:8080/api/user/getByName?un=张三`

   收到响应：

   ```json
   {"code":200,"datas":[{"ct":1593921680125,"id":1,"n":"张三","p":"","r":{"id":3,"n":"超级管理员","p":"all"},"ri":3}],"message":""}
   ```

   

   

更多说明请移步Liberg项目：https://github.com/liberg-cn/Liberg

