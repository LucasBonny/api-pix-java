package br.com.gunthercloud.api.pix.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.pix")
public record PixConfig(String clientSecret, String clientId, boolean sandbox, boolean debug, String certificate) {

}