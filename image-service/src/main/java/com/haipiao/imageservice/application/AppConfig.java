package com.haipiao.imageservice.application;

import com.haipiao.common.config.CommonConfig;
import com.haipiao.imageservice.property.ImageUploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CommonConfig.class)
@ComponentScan(basePackages = {"com.haipiao.imageservice.property", "com.haipiao.imageservice.handler"})
public class AppConfig {

    @Autowired
    public ImageUploadProperties imageUploadProperties;

}
