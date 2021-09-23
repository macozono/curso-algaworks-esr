package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest()	
			.authenticated()
		.and()
			.cors()
		.and()
			.oauth2ResourceServer()
			//.opaqueToken()
			.jwt();
	}
	
//	@Bean
//	public JwtDecoder jwDecoder() {
//		SecretKey secretKey = new SecretKeySpec("dsaldjsaldjsalkjdsalj32u3u29dshady932hekahdndsakewoejwqdsajh".getBytes(), "HmacSHA256");
//		return NimbusJwtDecoder.withSecretKey(secretKey).build();
//	}
}
