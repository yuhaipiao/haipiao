package com.haipiao.articleservice.application;

import com.haipiao.common.config.CommonConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CommonConfig.class)
@ComponentScan(basePackages = {"com.haipiao.articleservice.handler"})
public class AppConfig {}
