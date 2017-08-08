package cn.partytime.controller;

import cn.partytime.service.ProjectDeployService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 * Created by task on 2016/6/21.
 */

@RestController
@RequestMapping(value = "/v0/admin/deploy")
public class DeployController {


    private static final Logger logger = LoggerFactory.getLogger(DeployController.class);


    @Autowired
    private ProjectDeployService projectDeployService;

    @RequestMapping(value = "/danmu_system",method = {RequestMethod.POST,RequestMethod.GET})
    public String danmu_system(HttpServletRequest request) {
        logger.info("客户端屏幕登录");
        try{
            /*System.out.println("request========================="+ JSON.toJSONString(request.getHeaderNames()));
            Enumeration enu=request.getHeaderNames();//取得全部头信息
            while(enu.hasMoreElements()) {//以此取出头信息
                String headerName = (String) enu.nextElement();
                String headerValue = request.getHeader(headerName);//取出头信息内容
                System.out.println(headerName+"===="+headerValue);
            }*/


            projectDeployService.executeDeployDanmuServer(request);

        }catch (Exception e){
            logger.error("",e);
               return "error";
        }
        return "12312312312321";
    }


    @RequestMapping(value = "/danmu_manager",method = {RequestMethod.POST,RequestMethod.GET})
    public String view(HttpServletRequest request) {
        logger.info("客户端屏幕登录");
        try{
            /*System.out.println("request========================="+ JSON.toJSONString(request.getHeaderNames()));
            Enumeration enu=request.getHeaderNames();//取得全部头信息
            while(enu.hasMoreElements()) {//以此取出头信息
                String headerName = (String) enu.nextElement();
                String headerValue = request.getHeader(headerName);//取出头信息内容
                System.out.println(headerName+"===="+headerValue);
            }*/


            projectDeployService.executeDeployNodeServer(request);

        }catch (Exception e){
            logger.error("",e);
            return "error";
        }
        return "12312312312321";
    }


}
