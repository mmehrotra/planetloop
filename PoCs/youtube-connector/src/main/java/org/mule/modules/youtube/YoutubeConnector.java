package org.mule.modules.youtube;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;


import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.google.api.services.youtube.model.SearchResultSnippet;
import com.planetloop.modules.youtube.exceptions.YoutubeConnectorExceptionHandler;

import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
//import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.impl.MultiPartWriter;
//import com.google.api.services.youtube.model.SearchResult;
//import com.google.api.services.youtube.model.SearchListResponse;

import org.mule.modules.youtube.config.ConnectorConfig;

@Connector(name="youtube", friendlyName="Youtube")
public class YoutubeConnector {

	@Config
    ConnectorConfig YoutubeConfig;
    
    
    private static String YOUTUBE_URI = "https://www.googleapis.com/youtube/v3";
    private static String ACCESS_TOKEN_QUERY_PARAM_NAME = "access_token";
    
    
    /**
     * Jersey client
     */
    private Client client;

    /**
     * Constructor
     */
    public YoutubeConnector()
    {
    	ClientConfig clientConfig = new DefaultClientConfig();
    	clientConfig.getClasses().add(MultiPartWriter.class);
        client = Client.create(clientConfig);
    }
    
  //***********************************************************Methods***********************************************************************

//    /**
//     * Channel List
//     *
//     * @param access_token user access_token
//     * @return The channel list.
//     * @throws YoutubeConnectorExceptionHandler 
//     */
//    @Processor
////    @ReconnectOn(exceptions = { Exception.class })
//    public void getUserChannels(String access_token) throws YoutubeConnectorExceptionHandler{
//    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("channels").build();
//    	WebResource resource = client.resource(uri).queryParam("part", "id").queryParam("mine", "true").queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
//    	System.out.println(uri);
//    	ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_JSON).method("GET", ClientResponse.class);
//        if(clientResponse.getStatus() == 200) {
//        	System.out.println(clientResponse.toString());
//        } else {
//        	throw new YoutubeConnectorExceptionHandler(
//        	          String.format("ERROR - statusCode: %d - message: %s",
//        	            clientResponse.getStatus(), clientResponse.getEntity(String.class)));
//        }
//    }
    
    /**
     * Search most popular video on Youtube based on criterion given
     *
     * @param key Application API key
     * @param query search query
     * @param regionCode alpha-2 country code
     * @param order sort the result set
     * @param maxResults number of results per call [0:50]
     * @param videoDuration length of video. short, medium, long
     * @return SearchListSnippet the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public SearchResultSnippet searchVideos(String key,
    								@Default("slumdog millionaire") String query, 
    								@Optional String regionCode,
    								@Optional String order,
    								@Optional  @Default("10") String maxResults,
    								@Optional String videoDuration
    								) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("type", "video").queryParam("key", key).queryParam("q", query);
    	
    	if (regionCode != null) resource.queryParam("regionCode", regionCode);
    	if (order != null) resource.queryParam("order", order);
    	if (maxResults != null) resource.queryParam("maxResults", maxResults);
    	if (videoDuration != null) resource.queryParam("videoDuration", videoDuration);
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", SearchResultSnippet.class);
    }
    
    /**
     * Search most popular video/channel/playlist on Youtube based on criterion given
     *
     * @param key Application API key
     * @param query search query
     * @param regionCode alpha-2 country code
     * @return SearchListSnippet the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public SearchResultSnippet searchMostPopular(String key,
    								@Default("slumdog millionaire") String query, 
    								@Default("video") String type, 			 
    								@Optional String regionCode) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("q", query).queryParam("type", type).queryParam("key", key);
    	
    	if (regionCode != null) resource.queryParam("regionCode", regionCode);
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", SearchResultSnippet.class);
    }
    
    /**
     * Search most recent video on Youtube based on criterion given
     *
     * @param key Application API key
     * @param query search query
     * @param regionCode alpha-2 country code
     * @return SearchListSnippet the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public SearchResultSnippet searchMostRecentVideos(String key,
    								@Default("slumdog millionaire") String query, 
    								@Optional String regionCode) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("order", "date").queryParam("q", query).queryParam("type", "video").queryParam("key", key);
    	
    	if (regionCode != null) resource.queryParam("regionCode", regionCode);
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", SearchResultSnippet.class);
    }
    
    /**
     * Search video on Youtube related to video ID
     *
     * @param key Application API key
     * @param relatedToVideoId Id Youtube Video Id
     * @return SearchListSnippet the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public SearchResultSnippet searchVideosRelatedToVideoId(String key,
    								String relatedToVideoId, 
    								@Optional String regionCode) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("relatedToVideoId", relatedToVideoId).queryParam("type", "video").queryParam("key", key);
    	
    	if (regionCode != null) resource.queryParam("regionCode", regionCode);
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", SearchResultSnippet.class);
    }
  //*****************************************************************Helpers********************************************************************

	/**
     * Executes the Youtube request
     *
     */
    private <T> T execute(WebResource webResource, String method, Class<T> returnClass) throws YoutubeConnectorExceptionHandler {
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).method(method, ClientResponse.class);
        if(clientResponse.getStatus() == 200) {
        	System.out.println(clientResponse.toString());
            return clientResponse.getEntity(returnClass);
        } else {
            throw new YoutubeConnectorExceptionHandler(
              String.format("ERROR - statusCode: %d - message: %s",
                clientResponse.getStatus(), clientResponse.getEntity(String.class)));
        }
    }
    
  //***************************************************************Getters/Setters***************************************************************
    
    public ConnectorConfig getYoutubeConfig() {
        return YoutubeConfig;
    }

    public void setYoutubeConfig(ConnectorConfig YoutubeConfig) {
        this.YoutubeConfig = YoutubeConfig;
    }
    
    public Client getClient()
    {
        return client;
    }
    
    public void setClient(Client client)
    {
        this.client = client;
    }

}