package com.cyberark.conjur.springcloud.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cyberark.conjur.api.Conjur;
import com.cyberark.conjur.springcloud.domain.ConjurAuthParam;


/**
 * The ConjurConnectionManager uses Singleton pattern to provide the Conjur
 * Connection object to the calling class using the getInstance()
 * 
 */
public class ConjurConnectionManager {

	private static Logger logger = LoggerFactory.getLogger(ConjurConnectionManager.class);

	private static Conjur conjur;

	private ConjurConnectionManager() {

	}


	private static Conjur getConnection(ConjurAuthParam conjurParam) {

		logger.info("Inside Connection Manager get Connection with AuthParam");// +conjurParam.getConjurAccount());
		logger.info("Environment variables>>>" + conjurParam.getAccount());

		System.setProperty("CONJUR_ACCOUNT", conjurParam.getAccount());
		System.setProperty("CONJUR_APPLIANCE_URL", conjurParam.getApplianceUrl());
		System.setProperty("CONJUR_AUTHN_LOGIN", conjurParam.getAuthnLogin());
		System.setProperty("CONJUR_AUTHN_API_KEY", (conjurParam.getApiKey()).trim());
		System.setProperty("CONJUR_PROPERTY_MAP", conjurParam.getPath());

		try {
			conjur = new Conjur();

			logger.info("Connection with Conjur is successful");

		} catch (Exception e) {

			// e.printStackTrace();
		}
		return conjur;
	}


	private static Conjur getConnection() {

		logger.info("Inside Connection Manager get Connection" + System.getProperty("CONJUR_ACCOUNT"));

		try {

			conjur = new Conjur();

			logger.info("Connection with Conjur is successful");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return conjur;
	}


	public static Conjur getInstance(ConjurAuthParam authParam) {
		try {
			if (conjur == null) {
				synchronized (Conjur.class) {
					if (conjur == null) {
						conjur = getConnection(authParam);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conjur;
	}

	
	public static Conjur getInstance() {
		try {
			if (conjur == null) {
				synchronized (Conjur.class) {
					if (conjur == null) {
						conjur = getConnection();
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return conjur;
	}

}
