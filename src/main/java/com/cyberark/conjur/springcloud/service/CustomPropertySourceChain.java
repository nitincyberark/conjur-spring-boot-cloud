package com.cyberark.conjur.springcloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.api.Variables;
import com.cyberark.conjur.springcloud.core.ConjurConnectionManager;
import com.cyberark.conjur.springcloud.domain.ConjurAuthParam;
import com.cyberark.conjur.springcloud.env.ConjurMapProperty;

public class CustomPropertySourceChain extends PropertyProcessorChain {

	private Logger logger = LoggerFactory.getLogger(CustomPropertySourceChain.class);

	private PropertyProcessorChain chain;

	private ConjurAuthParam conjurParam;// = new ConjurAuthParam();

	private Conjur conjur = null;
	Variables var;

	public CustomPropertySourceChain(ConjurAuthParam conjurParam) {
		super("customProeprtySource", conjurParam);
		this.conjurParam = conjurParam;
		logger.info("Calling CustomPropertysource Chain");
	}

	@Override
	public void setNextChain(PropertyProcessorChain nextChain) {
		// TODO Auto-generated method stub
		this.chain = nextChain;

	}

	@Override
	public String[] getPropertyNames() {
		// TODO Auto-generated method stub
		return new String[0];
	}

	@Override
	public Object getProperty(String key) {
		String value = null;
		try {
			logger.info("Start: getProperty of CustomProperty Source");
			conjur = ConjurConnectionManager.getInstance(conjurParam);
			if (null != conjur) {

				var = conjur.variables();
				key = ConjurMapProperty.getInstance().mapProperty(key);
				value = var.retrieveSecret(key.replace(".", "/"));

			}

		} catch (Exception e) {
			// logger.error("Error connecting to Conjur Vault" + e.getMessage());

		}
		logger.info("End: getProperty of CustomPropertySource");
		return value;
	}

}
