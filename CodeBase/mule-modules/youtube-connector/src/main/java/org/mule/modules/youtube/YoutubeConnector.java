package org.mule.modules.youtube;

import org.mule.api.MuleMessage;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.SubscriptionListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoGetRatingResponse;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.planetloop.modules.youtube.exceptions.YoutubeConnectorExceptionHandler;

import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;
import org.mule.modules.youtube.config.ConnectorConfig;


import static com.planetloop.integration.youtube.common.CommonConstants.*;

@Connector(name="youtube", friendlyName="Youtube")
public class YoutubeConnector {

	@Config
    ConnectorConfig YoutubeConfig;
    
    
    
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
    
    //============================================================Channel=======================================================================
    
    @Processor
    public ChannelListResponse getChannel(String part, 
    									String key,
    									String access_token,
    									@Optional String id,
    									@Optional BOOL mine, 
    									@Optional String categoryId,
    									@Optional String managedByMe,
    									@Optional String forUsername,
    									@Optional String h1,
    									@Optional String maxResults,
    									@Optional String onBehalfOfContentOwner,
    									@Optional String pageToken) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(CHANNEL_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(PART_QUERY_PARAM_NAME, part).queryParam(KEY_QUERY_PARAM_NAME, key).queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    	
    	if (maxResults != null) resource = resource.queryParam(MAXRESULT_QUERY_PARAM_NAME, maxResults);
    	if (h1 != null) resource = resource.queryParam(H1_QUERY_PARAM, h1);
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);
    	if (forUsername != null) resource = resource.queryParam(FOR_USERNAME_QUERY_PARAM, forUsername);
    	if (pageToken != null) resource = resource.queryParam(PAGE_TOKEN_QUERY_PARAM, pageToken);
    	if (categoryId != null) resource = resource.queryParam(CATEGORY_ID_QUERY_PARAM, categoryId);
    	if (id != null) resource = resource.queryParam(ID_QUERY_PARAM, id);
    	if (mine != null) resource = resource.queryParam(MINE_QUERY_PARAM, mine.toString().toLowerCase());
    	if (managedByMe != null) resource = resource.queryParam(MANAGED_BY_ME_QUERY_PARAM, managedByMe);

    	System.out.println(resource.toString());
    	return execute(resource, "GET", ChannelListResponse.class);
    }
    
    @Processor
    public Channel updateChannel(String part,
    									String access_token,
    									String key,
    									MuleMessage muleMessage,
    									@Optional String onBehalfOfContentOwner) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(CHANNEL_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(PART_QUERY_PARAM_NAME, part).queryParam(KEY_QUERY_PARAM_NAME, key).queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    	
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);

    	System.out.println(resource.toString());
    	return executePut(resource, Channel.class, (String) muleMessage.getPayload());
    }
    
    //===========================================================PlayList=======================================================================
    
    @Processor
    public PlaylistListResponse getPlaylist(String part, 
    									String key,
    									String access_token,
    									@Optional String id,
    									@Optional BOOL mine, 
    									@Optional String channelId,
    									@Optional String h1,
    									@Optional String maxResults,
    									@Optional String onBehalfOfContentOwner,
    									@Optional String onBehalfOfContentOwnerChannel,
    									@Optional String pageToken) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(PLAYLIST_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(PART_QUERY_PARAM_NAME, part).queryParam(KEY_QUERY_PARAM_NAME, key).queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    	
    	if (maxResults != null) resource = resource.queryParam(MAXRESULT_QUERY_PARAM_NAME, maxResults);
    	if (h1 != null) resource = resource.queryParam(H1_QUERY_PARAM, h1);
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);
    	if (onBehalfOfContentOwnerChannel != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER_CHANNEL, onBehalfOfContentOwnerChannel);
    	if (pageToken != null) resource = resource.queryParam(PAGE_TOKEN_QUERY_PARAM, pageToken);
    	if (channelId != null) resource = resource.queryParam(CHANNEL_ID_QUERY_PARAM, channelId);
    	if (id != null) resource = resource.queryParam(ID_QUERY_PARAM, id);
    	if (mine != null) resource = resource.queryParam(MINE_QUERY_PARAM, mine.toString().toLowerCase());

    	System.out.println(resource.toString());
    	return execute(resource, "GET", PlaylistListResponse.class);
    }

    @Processor
    public Playlist createPlaylist(String part,
    									String access_token,
    									String key,
    									MuleMessage muleMessage,
    									@Optional String onBehalfOfContentOwner,
    									@Optional String onBehalfOfContentOwnerChannel) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(PLAYLIST_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(PART_QUERY_PARAM_NAME, part).queryParam(KEY_QUERY_PARAM_NAME, key).queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    	
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);
    	if (onBehalfOfContentOwnerChannel != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER_CHANNEL, onBehalfOfContentOwnerChannel);

    	System.out.println(resource.toString());
    	return executePost(resource, Playlist.class, (String) muleMessage.getPayload());
    }
    
    @Processor
    public Playlist updatePlaylist(String part,
    									String access_token,
    									String key,
    									MuleMessage muleMessage,
    									@Optional String onBehalfOfContentOwner) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(PLAYLIST_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(PART_QUERY_PARAM_NAME, part).queryParam(KEY_QUERY_PARAM_NAME, key).queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    	
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);

    	System.out.println(resource.toString());
    	return executePut(resource, Playlist.class, (String) muleMessage.getPayload());
    }
    
    @Processor
    public Playlist deletePlaylist(String id,
    									String access_token,
    									String key,
    									@Optional String onBehalfOfContentOwner) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(PLAYLIST_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(ID_QUERY_PARAM, id).queryParam(KEY_QUERY_PARAM_NAME, key).queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    	
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);

    	System.out.println(resource.toString());
    	return executeDelete(resource, Playlist.class, null);
    }
    
    //========================================================Subscriptions=====================================================================
    
    @Processor
    public SubscriptionListResponse getSubscriptions(String part, 
    									String key,
    									String access_token,
    									@Optional String id,
    									@Optional BOOL mine, 
    									@Optional BOOL myRecentSubscribers,
    									@Optional BOOL mySubscribers,
    									@Optional String channelId,
    									@Optional String forChannelId,
    									@Optional String maxResults,
    									@Optional String onBehalfOfContentOwner,
    									@Optional String onBehalfOfContentOwnerChannel,
    									@Optional ORDER order,
    									@Optional String pageToken) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(SUBSCRIPTIONS_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(PART_QUERY_PARAM_NAME, part).queryParam(KEY_QUERY_PARAM_NAME, key).queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    	
    	if (maxResults != null) resource = resource.queryParam(MAXRESULT_QUERY_PARAM_NAME, maxResults);
    	if (forChannelId != null) resource = resource.queryParam(FOR_CHANNEL_ID_QUERY_PARAM, forChannelId);
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);
    	if (onBehalfOfContentOwnerChannel != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER_CHANNEL, onBehalfOfContentOwnerChannel);
    	if (order != null) resource = resource.queryParam(ORDER_QUERY_PARAM_NAME, order.toString());
    	if (pageToken != null) resource = resource.queryParam(PAGE_TOKEN_QUERY_PARAM, pageToken);
    	if (channelId != null) resource = resource.queryParam(CHANNEL_ID_QUERY_PARAM, channelId);
    	if (id != null) resource = resource.queryParam(ID_QUERY_PARAM, id);
    	if (mine != null) resource = resource.queryParam(MINE_QUERY_PARAM, mine.toString().toLowerCase());
    	if (myRecentSubscribers != null) resource = resource.queryParam(MY_RECENT_SUBSCRIBERS_QUERY_PARAM, myRecentSubscribers.toString().toLowerCase());
    	if (mySubscribers != null) resource = resource.queryParam(MY_SUBSCRIBERS_QUERY_PARAM, mySubscribers.toString().toLowerCase());
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", SubscriptionListResponse.class);
    }

    @Processor
    public Subscription addSubscription(String access_token,
    								String part, MuleMessage muleMessage)throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(SUBSCRIPTIONS_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(PART_QUERY_PARAM_NAME, part)
				.queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    			    	
    	
    	System.out.println(resource.toString());
    	return executePost(resource, Subscription.class, (String) muleMessage.getPayload());
    }
    
    @Processor
    public Subscription removeSubscription(String access_token,
    								String id, MuleMessage muleMessage)throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(SUBSCRIPTIONS_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(ID_QUERY_PARAM, id)
				.queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    			    	
    	System.out.println(resource.toString());
    	return executeDelete(resource, Subscription.class, (String) muleMessage.getPayload());
    }
    
    //========================================================Video Category=====================================================================
    
    /**
     * Get video details on Youtube related to video ID
     *
     * @param key Application API key
     * @param regionCode alpha-2 country code
     * @return SearchListSnippet the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public SearchResultSnippet getVideoCategories(String regionCode, String key) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(VIDEO_CATEGORIES_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(PART_QUERY_PARAM_NAME, SNIPPET_OPTION).queryParam(REGION_CODE_QUERY_PARAM_NAME, regionCode).queryParam(KEY_QUERY_PARAM_NAME, key);
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", SearchResultSnippet.class);
    }
    
    //=============================================================Video=========================================================================
    /**
     * get videos detail on from Youtube
     *
     * @param key Application API key
     * @param access_token user access token
     * @param chart the chart parameter identifies the chart that you want to retrieve.
     * @param id comma separated values of video Ids
     * @param regionCode alpha-2 country code
     * @param rating Set this parameter's value to like or dislike to instruct the API to only return videos liked or disliked by the authenticated user.
     * @param videoCategoryId identifies the video category for which the chart should be retrieved
     * @param maxResults number of results per call [0:50]
     * @return SearchListSnippet the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public VideoListResponse getVideoDetails(String key,
    								String access_token,
    								@Optional String id,
    								@Optional CHART chart,
    								@Optional RATINGS ratings,
    								@Optional String videoCategoryId,
    								@Optional String maxResults,
    								@Optional String regionCode) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(VIDEO_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(KEY_QUERY_PARAM_NAME, key).queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    	
    	if (maxResults != null) resource = resource.queryParam(MAXRESULT_QUERY_PARAM_NAME, maxResults);
    	if (id != null) resource = resource.queryParam(ID_QUERY_PARAM, id);
    	if (chart != null) resource = resource.queryParam(CHART_PARAM, chart.toString());
    	if (ratings != null) resource = resource.queryParam(MY_RATING_PARAM, ratings.toString());
    	if (videoCategoryId != null) resource = resource.queryParam(VIDEO_CATEGORY_ID_PARAM, videoCategoryId);
    	if (regionCode != null) resource = resource.queryParam(REGION_CODE_QUERY_PARAM_NAME, regionCode);
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", VideoListResponse.class);
    }
    
    @Processor
    public VideoListResponse updateVideo(String access_token,
    								String part, MuleMessage muleMessage,
    								@Optional String onBehalfOfContentOwner)throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(VIDEO_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(PART_QUERY_PARAM_NAME, SNIPPET_OPTION)
				.queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    			
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);
    	
    	
    	System.out.println(resource.toString());
    	return executePut(resource, VideoListResponse.class, (String) muleMessage.getPayload());
    }
    
    @Processor
    public String rateVideo(String access_token, String id, RATINGS rating)throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(VIDEO_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(ID_QUERY_PARAM, id)
				.queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token).queryParam(RATING_QUERY_PARAM, rating.toString());    	
    	
    	System.out.println(resource.toString());
    	return executePost(resource, String.class, (String) null);
    }
    
    @Processor
    public VideoGetRatingResponse getVideoRatings(String access_token, String id, @Optional String onBehalfOfContentOwner)throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(VIDEO_PATH).path(GET_RATINGS_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(ID_QUERY_PARAM, id)
				.queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);    	
    	
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", VideoGetRatingResponse.class);
    }
    
    @Processor
    public String reportAbuse(String access_token, @Optional String onBehalfOfContentOwner, MuleMessage message)throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(VIDEO_PATH).path(REPORT_ABUSE).build();
    	WebResource resource = client.resource(uri).queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);    	
    	
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);
    	
    	System.out.println(resource.toString());
    	return executePost(resource, String.class, (String) message.getPayload());
    }
    
    @Processor
    public String deleteVideo(String access_token, String id, @Optional String onBehalfOfContentOwner)throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path(VIDEO_PATH).build();
    	WebResource resource = client.resource(uri).queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token).queryParam(ID_QUERY_PARAM, id);    	
    	
    	if (onBehalfOfContentOwner != null) resource = resource.queryParam(ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);
    	
    	System.out.println(resource.toString());
    	return executeDelete(resource, String.class, (String) null);
    }
    
    /**
     * Uploads a video to YouTube and optionally sets the video's metadata
     * @param key Application API key
     * @param access_token user access token
     * @param id comma separated values of video Ids
     * @return VideoGetRatingResponse
     * @exception YoutubeConnectorExceptionHandler
     */
    @Processor
    public void uploadVideo(String key, String access_token, File video)throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_UPLOAD_URI).build();
    	WebResource resource = client.resource(uri).queryParam(KEY_QUERY_PARAM_NAME, key)
				.queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    			
//    	System.out.println(resource.toString());
    	FormDataMultiPart multiPart = new FormDataMultiPart();
		multiPart.bodyPart(new FileDataBodyPart("source", video, MediaType.APPLICATION_OCTET_STREAM_TYPE));
		
		resource.type(MediaType.MULTIPART_FORM_DATA).post(String.class, multiPart);
		execute(resource, "GET", Video.class);
    }
      
    /**
     * Uploads a video to YouTube and optionally sets the video's metadata
     * @param key Application API key
     * @param access_token user access token
     * @return VideoGetRatingResponse
     * @exception YoutubeConnectorExceptionHandler
     */
    @Processor
    public void uploadVideoFromStream(String key, String access_token, MuleMessage muleMessage)throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_UPLOAD_URI).build();
    	WebResource resource = client.resource(uri).queryParam(KEY_QUERY_PARAM_NAME, key)
				.queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    			
    	FormDataMultiPart multiPart = new FormDataMultiPart();
//		multiPart.bodyPart(new FileDataBodyPart("source", muleMessage.getInboundAttachment("video"), MediaType.APPLICATION_OCTET_STREAM_TYPE));
//		multiPart.bodyPart(muleMessage.getInboundAttachment("video"), MediaType.APPLICATION_OCTET_STREAM_TYPE);
		multiPart.bodyPart(muleMessage.getInboundAttachment("video"), MediaType.APPLICATION_OCTET_STREAM_TYPE);
		
		executePost(resource, Video.class, multiPart);
    }
    //=============================================================Seach=========================================================================
    /**
     * Search video on Youtube related to video ID
     *
     * @param key Application API key
     * @param access_token user access token
     * @param query search query
     * @param orderBy sort the result set
     * @param maxResults number of results per call [0:50]
     * @return SearchListSnippet the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public SearchResultSnippet searchUserVideo(String key,
    								String access_token,
    								@Optional String query,
    								@Optional ORDER_BY orderBy,
    								@Optional String maxResults) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam(PART_QUERY_PARAM_NAME, SNIPPET_OPTION)
				.queryParam(KEY_QUERY_PARAM_NAME, key)
				.queryParam(TYPE_QUERY_PARAM, TYPE_VIDEO)
				.queryParam(FORMINE_QUERY_PARAM_NAME, TRUE)
				.queryParam(ACCESS_TOKEN_QUERY_PARAM_NAME, access_token);
    	if (maxResults != null) resource = resource.queryParam("maxResults", maxResults);
    	if (query != null) resource = resource.queryParam("q", query);
    	if (orderBy != null) resource = resource.queryParam("order", orderBy.toString());
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", SearchResultSnippet.class);
    }
    
    /**
     * Search playlist on Youtube based on criterion given
     *
     * @param key Application API key
     * @param query search query
     * @param regionCode alpha-2 country code
     * @param orderBy sort the result set
     * @param maxResults number of results per call [0:50]
     * @return SearchListSnippet the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public SearchResultSnippet searchPlaylist(String key,
    								String query, 
    								@Optional String regionCode,
    								@Optional ORDER_BY orderBy,
    								@Optional String maxResults) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("type", "playlist").queryParam("key", key).queryParam("q", query);
    	
    	if (regionCode != null) resource = resource.queryParam("regionCode", regionCode);
    	if (orderBy != null) resource = resource.queryParam("order", orderBy.toString());
    	if (maxResults != null) resource = resource.queryParam("maxResults", maxResults);
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", SearchResultSnippet.class);
    }
       
    /**
     * Search channel on Youtube based on criterion given
     *
     * @param key Application API key
     * @param query search query
     * @param regionCode alpha-2 country code
     * @param order sort the result set
     * @param maxResults number of results per call [0:50]
     * @return SearchListSnippet the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public SearchResultSnippet searchChannel(String key,
    								String query, 
    								@Optional String regionCode,
    								@Optional ORDER_BY orderBy,
    								@Optional String maxResults) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("type", "channel").queryParam("key", key).queryParam("q", query);
    	
    	if (regionCode != null) resource = resource.queryParam("regionCode", regionCode);
    	if (orderBy != null) resource = resource.queryParam("order", orderBy.toString());
    	if (maxResults != null) resource = resource.queryParam("maxResults", maxResults);
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", SearchResultSnippet.class);
    }
    
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
    								String query, 
    								@Optional String regionCode,
    								@Optional ORDER_BY orderBy,
    								@Optional String maxResults,
    								@Optional VIDEO_DURATION videoDuration
    								) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("type", "video").queryParam("key", key).queryParam("q", query);
    	
    	if (regionCode != null) resource = resource.queryParam("regionCode", regionCode);
    	if (orderBy != null) resource = resource.queryParam("order", orderBy.toString());
    	if (maxResults != null) resource = resource.queryParam("maxResults", maxResults);
    	if (videoDuration != null) resource = resource.queryParam("videoDuration", videoDuration.toString().toLowerCase());
    	
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
    								String query, 
    								@Default("video") VIDEO_TYPE type,
    								@Optional String maxResults,
    								@Optional ORDER_BY orderBy,
    								@Optional VIDEO_DURATION videoDuration,
    								@Optional String regionCode) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("q", query).queryParam("type", type.toString()).queryParam("key", key).queryParam("maxResults", maxResults);
    	
    	if (regionCode != null) resource = resource.queryParam("regionCode", regionCode);
    	if (maxResults != null) resource = resource.queryParam("maxResults", maxResults);
    	if (orderBy != null) resource = resource.queryParam("order", orderBy.toString());
    	if (videoDuration != null) resource = resource.queryParam("videoDuration", videoDuration.toString().toLowerCase());
    	
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
    								String query,
    								@Optional String maxResults,
    								@Optional String regionCode,
    								@Optional VIDEO_DURATION videoDuration,
    								@Optional ORDER_BY orderBy) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("order", "date").queryParam("q", query).queryParam("type", "video").queryParam("key", key);
    	
    	if (regionCode != null) resource = resource.queryParam("regionCode", regionCode);
    	if (maxResults != null) resource = resource.queryParam("maxResults", maxResults);
    	if (orderBy != null) resource = resource.queryParam("orderBy", orderBy.toString());
    	if (videoDuration != null) resource = resource.queryParam("videoDuration", videoDuration.toString().toLowerCase());
    	
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
    								@Optional String regionCode,
    								@Optional String maxResults,
    								@Optional VIDEO_DURATION videoDuration,
    								@Optional ORDER_BY orderBy) throws YoutubeConnectorExceptionHandler{
    	System.out.println(orderBy.toString());
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("relatedToVideoId", relatedToVideoId).queryParam("type", "video").queryParam("key", key);
    	
    	if (regionCode != null) resource = resource.queryParam("regionCode", regionCode);
    	if (orderBy != null) resource = resource.queryParam("orderBy", orderBy.toString());
    	if (maxResults != null) resource = resource.queryParam("maxResults", maxResults);
    	if (videoDuration != null) resource = resource.queryParam("videoDuration", videoDuration.toString().toLowerCase());
    	
    	System.out.println(resource.toString());
    	return execute(resource, "GET", SearchResultSnippet.class);
    }
  
    /**
     * Search video on Youtube related to video ID
     *
     * @param key Application API key
     * @param pageToken Id used to retrieve Youtube next/prev page
     * @return SearchListSnippet the search result
     * @throws YoutubeConnectorExceptionHandler 
     */
    @Processor
    public SearchResultSnippet searchNextPreviousPage(String key,
    								String pageToken,
    								@Optional String maxResults) throws YoutubeConnectorExceptionHandler{
    	URI uri = UriBuilder.fromPath(YOUTUBE_URI).path("search").build();
    	WebResource resource = client.resource(uri).queryParam("part", "snippet").queryParam("key", key).queryParam("pageToken", pageToken);
    	
    	if (maxResults != null) resource = resource.queryParam("maxResults", maxResults);
    	
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
        	System.out.println(clientResponse.toString());
            throw new YoutubeConnectorExceptionHandler(
              String.format("ERROR - statusCode: %d - message: %s",
                clientResponse.getStatus(), clientResponse.getEntity(String.class)));
        }
    }
    
    /**
     * Executes the Youtube request
     *
     */
    private <T> T executePut(WebResource webResource, Class<T> returnClass, String body) throws YoutubeConnectorExceptionHandler {
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).put(ClientResponse.class, body);
        if(clientResponse.getStatus() == 200 || clientResponse.getStatus() == 204) {
        	System.out.println(clientResponse.toString());
            return clientResponse.getEntity(returnClass);
        } else {
        	System.out.println(clientResponse.toString());
            throw new YoutubeConnectorExceptionHandler(
              String.format("ERROR - statusCode: %d - message: %s",
                clientResponse.getStatus(), clientResponse.getEntity(String.class)));
        }
    }
    
    /**
     * Executes the Youtube request
     *
     */
    private <T> T executePost(WebResource webResource, Class<T> returnClass, FormDataMultiPart multiPart) throws YoutubeConnectorExceptionHandler {
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class, multiPart);
        if(clientResponse.getStatus() == 200) {
        	System.out.println(clientResponse.toString());
            return clientResponse.getEntity(returnClass);
        } else {
        	System.out.println(clientResponse.toString());
            throw new YoutubeConnectorExceptionHandler(
              String.format("ERROR - statusCode: %d - message: %s",
                clientResponse.getStatus(), clientResponse.getEntity(String.class)));
        }
    }
    
    /**
     * Executes the Youtube request
     *
     */
    private <T> T executePost(WebResource webResource, Class<T> returnClass, String body) throws YoutubeConnectorExceptionHandler {
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, body);
        if(clientResponse.getStatus() == 200 || clientResponse.getStatus() == 204) {
        	System.out.println(clientResponse.toString());
            return clientResponse.getEntity(returnClass);
        } else {
        	System.out.println(clientResponse.toString());
            throw new YoutubeConnectorExceptionHandler(
              String.format("ERROR - statusCode: %d - message: %s",
                clientResponse.getStatus(), clientResponse.getEntity(String.class)));
        }
    }
    
    /**
     * Executes the Youtube request
     *
     */
    private <T> T executeDelete(WebResource webResource, Class<T> returnClass, String body) throws YoutubeConnectorExceptionHandler {
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class, body);
        if(clientResponse.getStatus() == 200 || clientResponse.getStatus() == 204) {
        	System.out.println(clientResponse.toString());
            return clientResponse.getEntity(returnClass);
        } else {
        	System.out.println(clientResponse.toString());
            throw new YoutubeConnectorExceptionHandler(
              String.format("ERROR - statusCode: %d - message: %s",
                clientResponse.getStatus(), clientResponse.getEntity(String.class)));
        }
    }
    
    //***************************************************************Getters/Setters**************************************************************
    
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