package edu.codebridge.user.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "azureblob")
@NoArgsConstructor
@Data
public class StorageConfig {
        @Value("${azureblob.defaultEndpointsProtocol}")
        private String defaultEndpointsProtocol;
        @Value("${azureblob.blobEndpoint}")
        private String blobEndpoint;
        @Value("${azureblob.queueEndpoint}")
        private String queueEndpoint;
        @Value("${azureblob.tableEndpoint}")
        private String tableEndpoint;
        @Value("${azureblob.accountName}")
        private String accountName;
        @Value("${azureblob.accountKey}")
        private String accountKey;
}