package com.java.taskManager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.data.mongodb")
@Getter
@Setter
public class MongoDbProperties {

    private String database;

    private String username;

    private String host;

    private Integer port;
}
