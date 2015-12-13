package com.innovation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.innovation.controller.UsuarioController;

@Configuration
public class HazelcastConfiguration {

	@Bean
	public Config config() {
		return new Config().addMapConfig(
                // Set up TTL for the Map tracking received Messages IDs
                new MapConfig()
                .setName(UsuarioController.HZ_MAP_NAME)); 
	}
}
