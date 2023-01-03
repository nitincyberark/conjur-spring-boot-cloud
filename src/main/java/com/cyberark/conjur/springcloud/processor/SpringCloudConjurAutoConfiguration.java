package com.cyberark.conjur.springcloud.processor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression(value="${conjur.cloud.enabled}")
public class SpringCloudConjurAutoConfiguration {
	
	@Bean
	public ConjurValueProcessor conjurValueProcessor()
	{
		return new ConjurValueProcessor();
	}

}
