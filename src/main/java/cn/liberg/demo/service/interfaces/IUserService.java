package cn.liberg.demo.service.interfaces;

import cn.liberg.core.OperatorException;
import cn.liberg.core.Response;
import cn.liberg.demo.data.entity.User;

public interface IUserService {

    public Response register(String userName, String password) throws OperatorException;
    public Response login(String userName, String password) throws OperatorException;
    public User getByName(String userName) throws OperatorException;
    public boolean nameExists(String userName) throws OperatorException;
}
