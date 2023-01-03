package com.cyberark.conjur.springcloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyberark.conjur.springcloud.domain.ConjurAuthParam;

public class DefaultPropertySourceChain extends PropertyProcessorChain {

	private Logger logger = LoggerFactory.getLogger(DefaultPropertySourceChain.class);

	private PropertyProcessorChain chain;
	private ConjurAuthParam conjurParam;

	public DefaultPropertySourceChain(ConjurAuthParam conjurParam) {

		super("defaultPropertySource", conjurParam);
		logger.info("Calling DefaultPropertysource Chain");
		this.conjurParam = conjurParam;

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
	public Object getProperty(String name) {
		// TODO Auto-generated method stub

		String value = null;

		if (value == null) {
			value = (String) this.chain.getProperty(name);

		}

		return value;
	}

}
