package cn.partytime.controller;

import cn.partytime.model.RestResultModel;
import cn.partytime.service.LogService;
import cn.partytime.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/3/18 0018.
 */

@RestController
@RequestMapping(value = "/log")
@Slf4j
public class LogController {

    @Autowired
    private LogService logService;

    private String exceptionUrl ="http://localhost/v1/api/javaClient/alarm";


    @RequestMapping(value = "/flash", method = {RequestMethod.GET,RequestMethod.POST})
    public RestResultModel flash(HttpServletRequest request){
        RestResultModel restResultModel = new RestResultModel();
        logService.appendLogToFile(1, request.getParameter("addressId"),request.getParameter("param"));
        return restResultModel;
    }

    @RequestMapping(value = "/java", method = {RequestMethod.GET,RequestMethod.POST})
    public RestResultModel javalog(HttpServletRequest request){
        RestResultModel restResultModel = new RestResultModel();
        String content = request.getParameter("param");
        String addressId = request.getParameter("addressId");
        //HttpUtils.sendGet("http://localhost/v1/api/javaClient/alarm/sadasd/1231232",null);
        if(content.contains("异常")){
            //发出告警
            //if("机器编号:1日志内容:执行脚本异常")
            //HttpUtils.sendGet()
            ////String s=HttpRequest.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("addressId="+addressId);
            stringBuffer.append("&");
            stringBuffer.append("number="+content.charAt(5));
            stringBuffer.append("&");
            stringBuffer.append("type="+0);
            HttpUtils.sendGet(exceptionUrl,stringBuffer.toString());
        }
        logService.appendLogToFile(0,addressId ,content);
        return restResultModel;
    }


}
