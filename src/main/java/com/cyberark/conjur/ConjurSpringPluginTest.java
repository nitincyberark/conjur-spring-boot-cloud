package com.cyberark.conjur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cyberark.conjur.springboot.annotations.ConjurPropertySource;
import com.cyberark.conjur.springboot.annotations.ConjurValue;
import com.cyberark.conjur.springboot.annotations.ConjurValues;

@SpringBootApplication
@ConjurPropertySource(value={"jenkins-app/"})
public class ConjurSpringPluginTest implements CommandLineRunner{
	private static Logger logger = LoggerFactory.getLogger(ConjurSpringPluginTest.class);

	//@Value("${password}")
	//private byte[] pass;

	@Value("${dbUserName}")
	private byte[] pass1;

	@Value("${dbPassword}")
	private byte[] pass2;

	@Value("${dbUrl}")
	private byte[] pass3;

	@ConjurValue(key="jenkins-app/dbUrl")
	private byte[] customVal;

	@ConjurValues(keys={"jenkins-app/dbUserName","jenkins-app/dbPassword"})
	private byte[] keys;

	@Autowired
	ApplicationContext appContext;
	
    public static void main(String[] args) {
    	
        SpringApplication.run(ConjurSpringPluginTest.class, args);
    }

    
	public void run(String... args) throws Exception {
		//logger.info("By Using Standard Spring annotation -->  " + new String(pass) + "  " );
		logger.info("By Using Custom annotation -->"+new String(customVal));

		logger.info("By Using Standard Spring annotation -->  " + new String(pass1) + "  " );
		logger.info("By Using Standard Spring annotation -->  " + new String(pass2) + "  " );
		logger.info("By Using Standard Spring annotation -->  " + new String(pass3) + "  " );

		logger.info("By Using Custom annotation for multiple retrieval -->"+new String(keys));
		
		
		
		
	}

	
}
