/*
 * First Created by LibergCoder@1.2.0
 */
package cn.liberg.demo.service;

import cn.liberg.core.OperatorException;
import cn.liberg.core.Response;
import cn.liberg.core.StatusCode;
import cn.liberg.demo.data.dao.RoleDao;
import cn.liberg.demo.data.dao.UserDao;
import cn.liberg.demo.data.entity.User;
import cn.liberg.demo.service.interfaces.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserService implements IUserService {
    public static final String PWD_SALT = "$high_performance_liberg$";

    public static String getMd5WithSalt(String source) {
        return DigestUtils.md5DigestAsHex((source+PWD_SALT).getBytes());
    }

    @Override
    public Response register(String userName, String password) throws OperatorException {
        User user = new User();
        user.name = userName;
        user.password = getMd5WithSalt(password);
        user.createTime = System.currentTimeMillis();
        UserDao.self().save(user);
        return Response.of(StatusCode.OK);
    }

    @Override
    public Response login(String userName, String password) throws OperatorException {
        final User user = UserDao.self().getByName(userName);
        if(user != null) {
            String md5Password = getMd5WithSalt(password);
            if(md5Password.equals(user.password)) {
                return Response.of(StatusCode.OK);
            }
        }
        return Response.of(999, "用户名或密码错误");
    }

    @Override
    public User getByName(String userName) throws OperatorException {
        User user = UserDao.self().getByName(userName);
        if(user!=null) {
            user.password = "";
            user.role = RoleDao.self().getById(user.roleId);
        }
        return user;
    }

    @Override
    public boolean nameExists(String userName) throws OperatorException {
        return UserDao.self().getByName(userName) == null ? true : false;
    }

}