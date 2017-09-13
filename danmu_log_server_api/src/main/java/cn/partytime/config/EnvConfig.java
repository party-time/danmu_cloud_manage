package cn.partytime.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvConfig {

    @Value("${test.environment}")
    private String testEnviroment;

    @Value("${production.environment}")
    private String productionEnviroment;

    @Value("${env}")
    private Integer env;

    public String getLogPath(){
        if(env==0){
            return testEnviroment;
        }
        return productionEnviroment;
    }
}
