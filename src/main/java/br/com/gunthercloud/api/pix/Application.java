package br.com.gunthercloud.api.pix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.gunthercloud.api.pix.config.PixConfig;

@SpringBootApplication
@EnableConfigurationProperties(PixConfig.class)	
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
