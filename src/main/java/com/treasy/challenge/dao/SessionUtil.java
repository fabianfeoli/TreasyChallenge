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

    	ServiceRegistry registry = new StandardServiceRegistryBuilder().
    	    configure("hibernate.cfg.xml").
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