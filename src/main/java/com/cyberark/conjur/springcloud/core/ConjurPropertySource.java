package com.cyberark.conjur.springcloud.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.stereotype.Component;

import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.api.Variables;
import com.cyberark.conjur.springcloud.env.ConjurMapProperty;




@Component
public class ConjurPropertySource extends EnumerablePropertySource<Object> {

	private Logger logger = LoggerFactory.getLogger(ConjurPropertySource.class);
	private Conjur conjur = null;
	Variables var;

	public ConjurPropertySource() {
		super("conjurPropertySource");

	}

	@Override
	public String[] getPropertyNames() { // TODO Auto-generated method stub
		return new String[0];
	}

	/**
	 * This method will be used to autowire the values to the key
	 * @param key - String
	 * @return object containing the value
	 */
	@Override
	public Object getProperty(String key) { // TODO Auto-generated method stub
		String value = null;
		try {
			conjur = ConjurConnectionManager.getInstance();
			if (null != conjur) {

				var = conjur.variables();
				// logger.info("Key >>" + key);
				key = ConjurMapProperty.getInstance().mapProperty(key);
				value = var.retrieveSecret(key.replace(".", "/"));
			}

		} catch (Exception e) {
			// logger.error("Error connecting to Conjur Vault" + e.getMessage());

		}
		
		return value;
	}

}
