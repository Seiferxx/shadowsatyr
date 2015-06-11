package com.seifernet.shadowsatyr.facade;

import java.util.ArrayList;

import com.seifernet.shadowsatyr.persistance.dao.AccountDAO;
import com.seifernet.shadowsatyr.persistance.dao.BlogEntryDAO;
import com.seifernet.shadowsatyr.persistance.dto.Account;
import com.seifernet.shadowsatyr.persistance.dto.BlogEntry;
import com.seifernet.shadowsatyr.persistance.dto.Hashtag;

public class IndexFacade {
	
	public ArrayList<BlogEntry> getLatestBlogEntries( ){
		ArrayList<BlogEntry> 	blogEntries = null;
		BlogEntryDAO			dao			= null;
		
		dao = new BlogEntryDAO( );
		blogEntries = new ArrayList<BlogEntry>( dao.readAllDsc( "date", 5 ) );
		for( BlogEntry entry : blogEntries ){
			String message = entry.getMessage( );
			ArrayList<Hashtag> hashtags = new ArrayList<Hashtag>( entry.getHashtags( ) ); 
			for( Hashtag hashtag : hashtags ){
				String hs = hashtag.getHashtag( );
				message = message.replace( hs, "<a href='/shadowsatyr/hashtag?hashtag=" + hs + "'>" + hs + "</a>" );
			}
			entry.setMessage( message );
		}
		
		return blogEntries;
	}
	
	public Account getAccountByNickname( String nickname ){
		AccountDAO 	dao 	= null;
		Account		account = null;
		
		dao = new AccountDAO( );
		account = dao.read( "nickname" , nickname );
		
		return account;
	}
	
	public Account getAccountByMail( String mail ){
		AccountDAO 	dao 	= null;
		Account		account = null;
		
		dao = new AccountDAO( );
		account = dao.read( "mail" , mail );
		
		return account;
	}
	
	public void createAccount( Account account ){
		AccountDAO dao = null;
		
		dao = new AccountDAO( );
		dao.create( account );
	}
}
