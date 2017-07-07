package com.ashan.hakka.common.web.mvc;

import javax.annotation.Resource;

import com.ashan.hakka.common.pojo.dto.RequestDTO;
import com.ashan.hakka.common.pojo.vo.ResponseVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/07/06
 */
@Aspect
@Component
public class HttpMessageConverterAspect {


    private ThreadLocal<RequestDTO> threadLocal=new ThreadLocal<>();


    @Pointcut("execution(* org.springframework.http.converter.GenericHttpMessageConverter.read(..))")
    public void readMethod(){}


    @Pointcut("execution(* org.springframework.http.converter.AbstractGenericHttpMessageConverter.writeInternal(..))")
    public void writeMethod(){}




    @AfterReturning(value="readMethod()",returning = "requestDTO")
    public void doReadAfter(RequestDTO requestDTO)
    {
        threadLocal.set(requestDTO);
    }

    @AfterReturning(value="writeMethod()")
    public void doWriteAfter()
    {
        threadLocal.remove();
        //System.out.println("");
    }


    public RequestDTO getRequestDTO()
    {
        return threadLocal.get();
    }
}
