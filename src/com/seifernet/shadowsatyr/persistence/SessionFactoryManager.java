package com.seifernet.shadowsatyr.persistence;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.seifernet.shadowsatyr.persistence.dto.Account;
import com.seifernet.shadowsatyr.persistence.dto.AccountStatus;
import com.seifernet.shadowsatyr.persistence.dto.Article;
import com.seifernet.shadowsatyr.persistence.dto.Asset;
import com.seifernet.shadowsatyr.persistence.dto.BlogEntry;
import com.seifernet.shadowsatyr.persistence.dto.Hashtag;
import com.seifernet.shadowsatyr.persistence.dto.Permission;
import com.seifernet.shadowsatyr.persistence.dto.SystemProperty;
import com.seifernet.shadowsatyr.util.Definitions;

/**
 * Session factory manager creates and configurates
 * the session factory object
 * 
 * @author Seifer ( Cuauhtemoc Herrera Muñoz )
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public abstract class SessionFactoryManager {

	private static SessionFactory instance;
	
	/**
	 * Session factory singleton, to avoid multiple factory
	 * instantiation.
	 * 
	 * @return The session factory object
	 */
	public static SessionFactory getSessionFactoryInstance( ){
		
		if( instance == null ){
			initializeSessionFactory( );
		}
		return instance;
	}
	
	/**
	 * Session factory configuration
	 * 
	 */
	public static void initializeSessionFactory(  ){
		Configuration 		configuration 	= null;
		Properties			properties		= null;
		ServiceRegistry		builder 		= null;
		
		configuration = new Configuration( );
		properties = new Properties( );
		
		properties.setProperty( "hibernate.connection.datasource" , Definitions.SHADOW_SATYR_DEFAULT_DATASOURCE );
		properties.setProperty( "hibernate.dialect" , "org.hibernate.dialect.PostgreSQLDialect" );
//		properties.setProperty( "hibernate.hbm2ddl.auto" , "create" );
		properties.setProperty( "hibernate.current_session_context_class", "thread" );
		 
		configuration.addAnnotatedClass( Account.class );
		configuration.addAnnotatedClass( AccountStatus.class );
		configuration.addAnnotatedClass( Article.class );
		configuration.addAnnotatedClass( Asset.class );
		configuration.addAnnotatedClass( BlogEntry.class );
		configuration.addAnnotatedClass( Hashtag.class );
		configuration.addAnnotatedClass( Permission.class );
		configuration.addAnnotatedClass( SystemProperty.class );		
		
		configuration.setProperties( properties );
		builder = new StandardServiceRegistryBuilder( ).applySettings( configuration.getProperties(  ) ).build( );
		instance = configuration.buildSessionFactory( builder );
		
	}
	
}
