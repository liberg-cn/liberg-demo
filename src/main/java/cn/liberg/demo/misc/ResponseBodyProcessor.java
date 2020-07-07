package cn.liberg.demo.misc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;


@ControllerAdvice
public class ResponseBodyProcessor extends FastJsonHttpMessageConverter implements ResponseBodyAdvice {
    public static final String utf8 = "utf-8";
    public static final String jsonpCallback = "callback";
    public static final Charset utf8Charset = Charset.forName(utf8);

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        String callback = request.getParameter(jsonpCallback);

        HttpServletResponse response = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
        response.setCharacterEncoding(utf8);

        String json = JSON.toJSONString(o);
        if (!StringUtils.isEmpty(callback)) {
            json = new StringBuilder(callback).append("(").append(json).append(")").toString();
        }
        try(OutputStream out = response.getOutputStream()) {
            out.write(json.getBytes(utf8Charset));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }
}
