package cn.partytime.service;

import cn.partytime.util.DeployConst;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * Created by dm on 2017/6/23.
 */

@Service
public class ProjectDeployService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectDeployService.class);

    @Value("${projects}")
    private String[] projects;

    @Value("${javaSourePath}")
    private String javaSourePath;

    @Value("${executeJavaPath}")
    private String executeJavaPath;

    @Value("${executeNodePath}")
    private String executeNodePath;

    @Value("${executeJavaPullPath}")
    private String executeJavaPullPath;


    @Value("${executeNodePullPath}")
    private String executeNodePullPath;


    public void executeDeployNodeServer(HttpServletRequest request) throws Exception{
        String content = getContent(request);
        if(!StringUtils.isEmpty(content)) {
            Map<String, Object> map = (Map<String, Object>) JSON.parse(String.valueOf(content));
            Object head_commit = map.get("head_commit");
            Map<String, Object> head_commitMap = (Map<String, Object>) JSON.parse(String.valueOf(head_commit));
            String message = String.valueOf(head_commitMap.get("message"));
            logger.info("message==================" + message);

            String command = message.substring(0, message.indexOf("#"));
            if (!DeployConst.DEPLOY_ACTION.equals(command)) {
                logger.info("执行更新的命令是:{}", command);
                return;
            }

            execShell(executeNodePullPath);

            execShell(executeNodePath);
        }
    }

    public void executeDeployDanmuServer(HttpServletRequest request) throws Exception{
        String content = getContent(request);
        if(!StringUtils.isEmpty(content)){
            Map<String,Object> map = (Map<String,Object>) JSON.parse(String.valueOf(content));
            Object head_commit = map.get("head_commit");
            Map<String,Object> head_commitMap = (Map<String,Object>)JSON.parse(String.valueOf(head_commit));
            String message = String.valueOf(head_commitMap.get("message"));
            logger.info("message=================="+message);

            String command = message.substring(0,message.indexOf("#"));
            if(!DeployConst.DEPLOY_ACTION.equals(command)){
                logger.info("执行更新的命令是:{}",command);
                return;
            }
            /*execShell(executeJavaPullPath);*/
            String modifiedArray = String.valueOf(head_commitMap.get("modified"));
            logger.info("modified============>{}",modifiedArray);
            List<String> modifiedList = JSON.parseArray(modifiedArray,String.class);
            List<String> tempProjectList = getProjectList(modifiedList);
            logger.info("tempProjectList============>{}",JSON.toJSONString(tempProjectList));

            logger.info("projects============>{}",JSON.toJSONString(projects));
            Set<String> projectSet = new HashSet<>();
            if(tempProjectList!=null && tempProjectList.size()>0){
                for(String strp:tempProjectList){
                    for(String project:projects){
                        if(strp.equals(project)){
                            //获取项目名称
                            logger.info("strp:============={}",strp);
                            projectSet.add(strp);
                        }/*else{
                            String projectName = getDependProject(strp,project);
                            if(!StringUtils.isEmpty(projectName)){
                                projectSet.add(projectName);
                            }
                        }*/
                    }
                }
            }
            logger.info("projectSet============="+JSON.toJSONString(projectSet));
            /*executeProject(projectSet);*/
        }
    }

    public void executeProject(Set<String> projectSet){
        if(projectSet!=null && projectSet.size()>0){
            //执行脚本
            for(String str:projectSet){

                execShell(executeJavaPath,str);

            }
        }
    }


    public String getDependProject(String sonModel,String projectName){
        String pomPath = javaSourePath+ File.separator+projectName+File.separator+"pom.xml";
        StringBuffer result = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pomPath));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
            if(result.toString().contains(sonModel)){
                return projectName;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<String> getProjectList(List<String> modifiedList){
        List<String> projectList = new ArrayList<String>();
        if(modifiedList!=null && modifiedList.size()>0){
            for(String str:modifiedList){
                projectList.add(str.split("/")[0]);
            }
        }
        return projectList;
    }



    public String getContent(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("UTF-8");
        int size = request.getContentLength();
        System.out.println(size);

        InputStream inStream = request.getInputStream();

        //byte[] reqBodyBytes = readBytes(is, size);
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        String res = new String(in2b);

        inStream.close();
        swapStream.close();

        return res;
    }

    public String execShell(String shellString) {
        logger.info(shellString);
        Process process = null;
        StringBuffer sb = new StringBuffer();
        try {
            process = Runtime.getRuntime().exec(shellString);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                sb.append(line);
            }
            input.close();
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                logger.info("call shell failed. error code is :" + exitValue);
            } else {
                logger.info("shell exec success");
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return sb.toString();
    }

    public String execShell(String shellString,String param) {
        logger.info(shellString);
        Process process = null;
        StringBuffer sb = new StringBuffer();
        try {
            String[] cmd = {"/bin/sh","-c",shellString+" "+param};
            logger.info("execute command:{}",JSON.toJSONString(cmd));
            process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                sb.append(line);
            }
            input.close();
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                logger.info("call shell failed. error code is :" + exitValue);
            } else {
                logger.info("shell exec success");
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return sb.toString();
    }


}
