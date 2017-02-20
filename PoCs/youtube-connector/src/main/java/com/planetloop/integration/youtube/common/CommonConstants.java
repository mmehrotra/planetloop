package com.planetloop.integration.youtube.common;

public interface CommonConstants {


	String YOUTUBE_URI = "https://www.googleapis.com/youtube/v3";
	String YOUTUBE_UPLOAD_URI = "https://www.googleapis.com/upload/youtube/v3/videos";
	
	String ACCESS_TOKEN_QUERY_PARAM_NAME = "access_token";
	String PART_QUERY_PARAM_NAME = "part";
	String KEY_QUERY_PARAM_NAME = "key";
	String FORMINE_QUERY_PARAM_NAME = "forMine";
	String MAXRESULT_QUERY_PARAM_NAME = "maxResults";
	String ORDER_QUERY_PARAM_NAME = "order";
	String Q_QUERY_PARAM_NAME = "q";
	String REGION_CODE_QUERY_PARAM_NAME = "regionCode";
	String TYPE_QUERY_PARAM = "type";
	String ID_QUERY_PARAM = "id";
	String VIDEO_CATEGORY_ID_PARAM = "videoCategoryId";
	String CHART_PARAM = "chart";
	String MY_RATING_PARAM = "myRating";
	String ON_BEHALF_OF_CONTENT_OWNER= "onBehalfOfContentOwner";
	String ON_BEHALF_OF_CONTENT_OWNER_CHANNEL= "onBehalfOfContentOwnerChannel";
	String RATING_QUERY_PARAM = "rating";
	String FOR_CHANNEL_ID_QUERY_PARAM = "forChannelId";
	String PAGE_TOKEN_QUERY_PARAM = "pageToken";
	String CHANNEL_ID_QUERY_PARAM = "channelId";
	String MINE_QUERY_PARAM = "mine";
	String H1_QUERY_PARAM = "h1";
	String MY_RECENT_SUBSCRIBERS_QUERY_PARAM = "myRecentSubscribers";
	String MY_SUBSCRIBERS_QUERY_PARAM = "mySubscribers";
	String CATEGORY_ID_QUERY_PARAM = "categoryId";
	String FOR_USERNAME_QUERY_PARAM = "forUsername";
	String MANAGED_BY_ME_QUERY_PARAM = "managedByMe";
	
	String SNIPPET_OPTION = "snippet";
	
	String TYPE_VIDEO = "video";
	String TYPE_CHANNEL = "channel";
	String TYPE_PLAYLIST = "playlist";
	String TRUE = "true";
	String FALSE = "false";
	
	String VIDEO_CATEGORIES_PATH = "videoCategories";
	String SUBSCRIPTIONS_PATH = "subscriptions";
	String SEARCH_PATH = "search";
	String VIDEO_PATH = "videos";
	String GET_RATINGS_PATH = "getRating";
	String REPORT_ABUSE = "reportAbuse";
	String PLAYLIST_PATH = "playlists";
	String CHANNEL_PATH = "channels";
	
	String VIDEO_FILE_FORMAT = "video/*";
	
	enum ORDER_BY{
    	date, rating, relevance, title, videoCount, viewCount 
    }
	
	enum ORDER{
		alphabetical, relevance, unread
	}
	
	enum VIDEO_TYPE{
		channel, playlist, video
	}
	
	enum VIDEO_DURATION{
		ANY, LONG, MEDIUM, SHORT
	}
	
	enum RATINGS{
		like, dislike, none
	}
	
	enum CHART{
		mostPopular
	}
	
	enum PART_OPTIONS{
		contentDetails, id, snippet, subscriberSnippet
	}
	
	enum FILTERS{
		channelId, id, mine, myRecentSubscribers, mySubscribers
	}
	
	enum BOOL{
		TRUE, FALSE
	}
}
