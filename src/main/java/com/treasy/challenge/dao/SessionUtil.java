package com.treasy.challenge.dao;
 
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
 
public class SessionUtil {
    
    private static SessionUtil instance=new SessionUtil();
    private SessionFactory sessionFactory;
    
    public static SessionUtil getInstance(){
            return instance;
    }
    
    @SuppressWarnings("deprecation")
	private SessionUtil(){
    	Map<String,String> jdbcUrlSettings = new HashMap<>();
    	String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
    	if (null != jdbcDbUrl) {
    	  jdbcUrlSettings.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
    	}
  	  	jdbcUrlSettings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
  	  	jdbcUrlSettings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
  	  	jdbcUrlSettings.put("hibernate.hbm2ddl.auto", "create");
  	  
    	ServiceRegistry registry = new StandardServiceRegistryBuilder().
    	    applySettings(jdbcUrlSettings).
    	    build();
    	
    	
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
                
        sessionFactory = configuration.buildSessionFactory();
    }
    
    public static Session getSession(){
        Session session =  getInstance().sessionFactory.openSession();
        
        return session;
    }
}