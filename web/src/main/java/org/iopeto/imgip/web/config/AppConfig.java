package org.iopeto.imgip.web.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;


@Configuration
@EnableWebMvc
@ComponentScan({"org.iopeto.imgip"})
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcAutoConfiguration{

    @Bean
    public TaskScheduler scheduler(){
        return new ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor());
    }

    @Bean
    public DateFormat globalDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

}