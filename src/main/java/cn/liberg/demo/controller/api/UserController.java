/*
 * First Created by LibergCoder@1.2.0
 */
package cn.liberg.demo.controller.api;

import cn.liberg.core.OperatorException;
import cn.liberg.core.Response;
import cn.liberg.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
        init();
    }

    private void init() {
    }

    @PostMapping("/register")
    public Response register(@RequestParam("un") String userName, 
                             @RequestParam("p") String password) throws OperatorException {
        try {
            return service.register(userName, password);
        } catch (OperatorException e) {
            e.printStackTrace();
            return Response.of(e.statusCode());
        }
    }

    @PostMapping("/login")
    public Response login(@RequestParam("un") String userName, 
                          @RequestParam("p") String password) throws OperatorException {
        try {
            return service.login(userName, password);
        } catch (OperatorException e) {
            e.printStackTrace();
            return Response.of(e.statusCode());
        }
    }

    @PostMapping("/getByName")
    public Response getByName(@RequestParam("un") String userName) throws OperatorException {
        try {
            return Response.ok().putData(service.getByName(userName));
        } catch (OperatorException e) {
            e.printStackTrace();
            return Response.of(e.statusCode());
        }
    }

    @PostMapping("/nameExists")
    public Response nameExists(@RequestParam("un") String userName) throws OperatorException {
        try {
            return Response.ok().putData(service.nameExists(userName));
        } catch (OperatorException e) {
            e.printStackTrace();
            return Response.of(e.statusCode());
        }
    }

}