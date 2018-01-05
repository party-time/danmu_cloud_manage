package cn.partytime.controller;

import cn.partytime.util.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/1/5.
 */

@Slf4j
@RestController
public class javaController {

    @Autowired
    private Configuration configuration;

    @RequestMapping(value = "/v1001/create/careatJavaFile",method = {RequestMethod.POST,RequestMethod.GET})
    public String danmu_system(HttpServletRequest request) {
        Template template = null; // freeMarker template
        Map<String, Object> model = new HashMap<String, Object>();
        String content = null;
        try {
            template = configuration.getTemplate("java/java-log.ftl");
            String projectName = "danmu_bms_api";
            model.put("projectName",projectName);
            content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            log.info("content:{}",content);
            FileUtil.writeFile("D:/freemark/java/"+projectName+".xml",content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return content;
    }
}
