package org.mule.modules.youtube;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;

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
    
  //********************************************************************************************************************************************

    /**
     * Channel List
     *
     * @param access_token user access_token
     * @return The channel list.
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
//    @ReconnectOn(exceptions = { Exception.class })
    public void getUserChannels(String access_token) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("channels").build();
    	WebResource resource = client.resource(uri).queryParam("part", "id").queryParam("mine", "true").queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    	System.out.println(uri);
    	ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_JSON).method("GET", ClientResponse.class);
        if(clientResponse.getStatus() == 200) {
        	System.out.println(clientResponse.toString());
        } else {
        	throw new YoutubeConnectorExceptionHandler(
        	          String.format("ERROR - statusCode: %d - message: %s",
        	            clientResponse.getStatus(), clientResponse.getEntity(String.class)));
        }
    }

    
    /**
     * Search method
     *
     * @param access_token Name user access token
//     * @return SearchListResponse the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public SearchResultSnippet getMostPopularVideos(@Default("slumdog millionaire") String query, @Default("video") String type, String key) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("q", query).queryParam("type", type).queryParam("key", key);
    	System.out.println(resource.toString());
    	ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_JSON).method("GET", ClientResponse.class);
        if(clientResponse.getStatus() == 200) {
        	System.out.println(clientResponse.toString());
//        	System.out.println(clientResponse.getEntity(String.class));
        	return clientResponse.getEntity(SearchResultSnippet.class);
        } else {
        	throw new YoutubeConnectorExceptionHandler(
        	          String.format("ERROR - statusCode: %d - message: %s",
        	            clientResponse.getStatus(), clientResponse.getEntity(String.class)));
        }
    }
  //********************************************************************************************************************************************

    
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