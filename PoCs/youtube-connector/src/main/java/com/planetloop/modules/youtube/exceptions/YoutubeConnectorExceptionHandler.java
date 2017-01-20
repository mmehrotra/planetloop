package com.planetloop.modules.youtube.exceptions;

public class YoutubeConnectorExceptionHandler extends Exception{
	
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
