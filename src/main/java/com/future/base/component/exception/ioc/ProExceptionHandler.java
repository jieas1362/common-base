package com.future.base.component.exception.ioc;

import com.future.base.component.exception.handler.ExceptionProcessor;
import com.future.base.constant.common.ResponseElement;
import com.future.base.model.common.ExceptionElement;
import com.future.base.model.common.ExceptionResponse;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.future.base.util.base.WebCommonFunctions.getAcceptLanguages;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * global exp handler bean
 *
 * @author liuyunfei
 */
@RestControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class ProExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ExceptionResponse handle(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
        ExceptionElement exceptionElement = ExceptionProcessor.handle(throwable, getAcceptLanguages(request));

        //前端无法处理非200的http响应状态,业务异常统一返回200,使用业务code码区分
//        response.setStatus(exceptionElement.getStatus());
        response.setStatus(ResponseElement.OK.status);
        return exceptionElement.getResponse();
    }

}