package com.tenji.adminapi2.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tenji")
@Data
public class TenjiProperties {

    private String pwdEncryption;
}
