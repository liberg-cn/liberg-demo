## UserService接口文档V1.0

> **基础URL**
开发本机：
测试环境：
正式环境：

### 1. `/api/user/register`

参数：
        `un`--userName，string类型
        `p`--password，string类型
返回值：Response

### 2. `/api/user/login`

参数：
        `un`--userName，string类型
        `p`--password，string类型
返回值：Response

### 3. `/api/user/getByName`

参数：
        `un`--userName，string类型
返回值：User

### 4. `/api/user/nameExists`

参数：
        `un`--userName，string类型
返回值：boolean

