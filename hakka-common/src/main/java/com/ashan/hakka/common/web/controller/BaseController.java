package com.ashan.hakka.common.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

import com.ashan.hakka.common.exception.ErrorCode;
import com.ashan.hakka.common.pojo.POJO;
import com.ashan.hakka.common.pojo.dto.RequestDTO;
import com.ashan.hakka.common.pojo.vo.ResponseVO;
import com.ashan.hakka.common.util.PojoUtils;
import com.ashan.hakka.common.web.mvc.HttpMessageConverterAspect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 李银山 yinshan.lys@alibaba-inc.com
 * @date 2017/07/06
 */
public abstract class BaseController {

    @Autowired
    private HttpMessageConverterAspect httpMessageConverterAspect;

    @ExceptionHandler
    public ResponseVO<String> exceptionHandler(HttpServletRequest request, Exception e)
    {

        RequestDTO requestDTO=httpMessageConverterAspect.getRequestDTO();

        if(requestDTO!=null)
        {
            return PojoUtils.errorResponseVO(requestDTO,e);
        }
        ResponseVO<String> responseVO=new ResponseVO<String>();
        responseVO.setErrCode(ErrorCode.UNKNOWN_ERR_CODE);
        responseVO.setErrInfo(e.getMessage());
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        PrintStream ps=new PrintStream(os);
        e.printStackTrace(ps);
        responseVO.setData(os.toString());
        return responseVO;
    }

}
