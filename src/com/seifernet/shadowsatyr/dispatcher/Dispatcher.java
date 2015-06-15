package com.seifernet.shadowsatyr.dispatcher;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import com.seifernet.shadowsatyr.helper.AccountHelper;
import com.seifernet.shadowsatyr.helper.AdminHelper;
import com.seifernet.shadowsatyr.helper.BlogHelper;
import com.seifernet.shadowsatyr.helper.ErrorHelper;
import com.seifernet.shadowsatyr.helper.IndexHelper;
import com.seifernet.shadowsatyr.util.Definitions;
import com.seifernet.snwf.helper.DispatcherHelper;
import com.seifernet.snwf.util.SNWFDefinitions;

public class Dispatcher implements DispatcherHelper{

	private Logger logger = Logger.getLogger( Dispatcher.class );
	private int responseType;
	
	/**
	 * Constructor sets responseType to HTTP_RESPONSE
	 * by default
	 */
	public Dispatcher(  ){
		responseType = SNWFDefinitions.HTTP_RESPONSE;
	}
	
	@Override
	public String dispatchAction( HttpServletRequest request, HttpServletResponse response, String action ) {
		
		try{ 
			request.setCharacterEncoding( SNWFDefinitions.UTF8_CHARSET );
		} catch( UnsupportedEncodingException e ){
			logger.error( Definitions.LOGGER_ERROR_1 );
		}
		
		switch( action ){
			case Definitions.DEFAULT_ACTION: case Definitions.INDEX:
				IndexHelper.index( request, response );
				break;
			case Definitions.LOGIN:
				IndexHelper.login( request, response );
				break;
			case Definitions.HASHTAG:
				BlogHelper.hashtag( request, response );
				break;
			case Definitions.CREATE_USER:
				responseType = SNWFDefinitions.HTTP_REDIRECT;
				return IndexHelper.createUser( request, response );
			case Definitions.VALIDATE_NICK_NAME:
				responseType = SNWFDefinitions.JSON_RESPONSE;
				return IndexHelper.validateNickname( request, response );
			case Definitions.VALIDATE_MAIL:
				responseType = SNWFDefinitions.JSON_RESPONSE;
				return IndexHelper.validateMail( request, response );
			case Definitions.CREATE_BLOG_ENTRY:
				responseType = SNWFDefinitions.JSON_RESPONSE;
				return BlogHelper.createBlogEntry( request, response );
			case Definitions.LATEST_BLOG_ENTRIES:
				responseType = SNWFDefinitions.HTML_RESPONSE;
				return BlogHelper.latestBlogEntries( request, response );
			case Definitions.USER_PROFILE:
				AccountHelper.userProfile( request, response );
				break;
			case Definitions.DASHBOARD:
				AdminHelper.dashboard( request, response );
				break;
			case Definitions.ERROR_500:
				ErrorHelper.error500( request, response );
				break;
			case Definitions.ERROR_404: default:
				ErrorHelper.error404( request, response );
		}
		return SNWFDefinitions.TILES_REDIRECT_URL;
	}

	@Override
	public int getResponseType( ) {
		return responseType;
	}

}
