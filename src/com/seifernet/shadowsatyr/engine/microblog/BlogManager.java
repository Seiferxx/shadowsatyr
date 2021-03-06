package com.seifernet.shadowsatyr.engine.microblog;

import java.util.ArrayList;

import com.seifernet.shadowsatyr.persistence.dao.BlogEntryDAO;
import com.seifernet.shadowsatyr.persistence.dao.HashtagDAO;
import com.seifernet.shadowsatyr.persistence.dto.Account;
import com.seifernet.shadowsatyr.persistence.dto.BlogEntry;
import com.seifernet.shadowsatyr.persistence.dto.Hashtag;

public class BlogManager {
	
	public static ArrayList<BlogEntry> getLatestBlogEntries( ){
		ArrayList<BlogEntry> 	blogEntries = null;
		BlogEntryDAO			dao			= null;
		
		dao = new BlogEntryDAO( );
		blogEntries = new ArrayList<BlogEntry>( dao.readAllDsc( "date", 5 ) );
		for( BlogEntry entry : blogEntries ){
			String message = entry.getContent( );
			ArrayList<Hashtag> hashtags = new ArrayList<Hashtag>( entry.getHashtags( ) ); 
			for( Hashtag hashtag : hashtags ){
				String hs = hashtag.getHashtag( );
				message = message.replace( hs, "<a href='/shadowsatyr/hashtag?hashtag=" + hs.replace( "#" , "%23" ) + "'>" + hs + "</a>" );
			}
			entry.setContent( message );
		}
		
		return blogEntries;
	}
	
	public static void createBlogEntry( BlogEntry entry ){
		BlogEntryDAO dao = new BlogEntryDAO( );
		
		dao = new BlogEntryDAO( );
		dao.create( entry );
	}
	
	public static Hashtag getHashtag( String hashtag ){
		HashtagDAO dao = new HashtagDAO( );
		
		return dao.read( "hashtag" , hashtag );
	}
	
	public static ArrayList<BlogEntry> getBlogEntries( Account account ){
		BlogEntryDAO dao = null;
		ArrayList<BlogEntry> blogEntries = null;
		
		dao = new BlogEntryDAO( );
		blogEntries = new ArrayList<BlogEntry>( dao.readAll( account ) );
		
		for( BlogEntry entry : blogEntries ){
			String message = entry.getContent( );
			ArrayList<Hashtag> hashtags = new ArrayList<Hashtag>( entry.getHashtags( ) ); 
			for( Hashtag h : hashtags ){
				String hs = h.getHashtag( );
				message = message.replace( hs, "<a href='/shadowsatyr/hashtag?hashtag=" + hs.replace( "#" , "%23" ) + "'>" + hs + "</a>" );
			}
			entry.setContent( message );
		}
		
		return blogEntries;
	}
	
	public static ArrayList<BlogEntry> getBlogEntries( String hashtag, Integer page ){
		BlogEntryDAO dao = null;
		ArrayList<BlogEntry> blogEntries = null;
		
		dao = new BlogEntryDAO( );
		blogEntries = new ArrayList<BlogEntry>( dao.readAll( hashtag, page ) );
		
		for( BlogEntry entry : blogEntries ){
			String message = entry.getContent( );
			ArrayList<Hashtag> hashtags = new ArrayList<Hashtag>( entry.getHashtags( ) ); 
			for( Hashtag h : hashtags ){
				String hs = h.getHashtag( );
				message = message.replace( hs, "<a href='/shadowsatyr/hashtag?hashtag=" + hs.replace( "#" , "%23" ) + "'>" + hs + "</a>" );
			}
			entry.setContent( message );
		}
		
		return blogEntries;
	}
}
