package cn.partytime.service;

import cn.partytime.config.EnvConfig;
import cn.partytime.controller.LogController;
import cn.partytime.util.DateUtils;
import cn.partytime.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/18 0018.
 */

@Service
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);


    @Autowired
    private EnvConfig envConfig;

    public void appendLogToFile(int type,String addressId,String param){

        Date currentDate = DateUtils.getCurrentDate();
       String directoryName = DateUtils.dateToString(currentDate,"yyyy-MM-dd");
       String dateStr = DateUtils.dateToString(currentDate,"yyyy-MM-dd HH:mm:ss");
       int hours = currentDate.getHours();

       String typeStr = "";
       if(type==0){
            typeStr="javalog";
       }else{
           typeStr="flashlog";
        }

       String filePath = envConfig.getLogPath()+File.separator+typeStr+File.separator+directoryName+File.separator+hours;
       File file = new File(filePath);
       if(!file.exists()){
           file.mkdirs();
       }
       String fileName = filePath+File.separator+addressId;
        logger.info("======================param:"+param);
       StringBuffer stringBuffer  = new StringBuffer();
        stringBuffer.append("time:");
        stringBuffer.append(dateStr);
        stringBuffer.append("\t");
        stringBuffer.append("content:");
        stringBuffer.append(param);
        stringBuffer.append("\n");
        FileUtils.appendContentToFile(fileName,stringBuffer.toString());
    }




}
