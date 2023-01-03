package com.cyberark.conjur.springcloud.service;

import org.springframework.core.env.EnumerablePropertySource;
import com.cyberark.conjur.springcloud.domain.ConjurAuthParam;

public abstract class PropertyProcessorChain extends EnumerablePropertySource<Object> {

	private PropertyProcessorChain processorChain;
	private ConjurAuthParam conjurParam;

	public PropertyProcessorChain(String name, ConjurAuthParam conjurParam) {
		super("propertyProcessorChain");
		this.conjurParam = conjurParam;

	}

	public void setNextChain(PropertyProcessorChain processChain) {
		this.processorChain = new DefaultPropertySourceChain(conjurParam);

		CustomPropertySourceChain customPS = new CustomPropertySourceChain(conjurParam);
		processorChain.setNextChain(customPS);
	}

	@Override
	public Object getProperty(String name) {
		return name;

	}

}
