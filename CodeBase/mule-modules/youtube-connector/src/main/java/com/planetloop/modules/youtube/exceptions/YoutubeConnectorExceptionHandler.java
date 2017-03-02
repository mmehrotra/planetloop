package com.planetloop.modules.youtube.exceptions;

public class YoutubeConnectorExceptionHandler extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4602376658191742897L;

	public YoutubeConnectorExceptionHandler(){
		
	}
	
	public YoutubeConnectorExceptionHandler(String message){
		super(message);
	}
	
	public YoutubeConnectorExceptionHandler(Throwable cause){
		super(cause);
	}
	
	public YoutubeConnectorExceptionHandler(String message, Throwable cause) {
		super(message, cause);
	}
	
}
