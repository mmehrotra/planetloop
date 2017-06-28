package com.pl.facebook.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.pl.facebook.entity.AccessToken;
import com.pl.facebook.entity.Credentials;
import com.pl.facebook.entity.FacebookUser;
import com.pl.facebook.entity.FacebookVideos;
import com.pl.facebook.entity.ResponseVO;
import com.pl.facebook.properties.FacebookProperties;
import com.pl.facebook.service.CredentialsService;

@RestController
public class FacebookController {

	private static final String FAILURE = "FAILURE";

	private static final String SUCCESS = "SUCCESS";

	private static final Logger logger = LoggerFactory.getLogger(FacebookController.class);

	@Autowired
	CredentialsService credService;

	@Autowired
	private FacebookProperties fbProp;

	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
	}

	@RequestMapping(path = "/pl-facebook/authorize", method = RequestMethod.GET)
	public ModelAndView authorize(@RequestParam String emailId) {

		logger.debug("Authorization request arrived for facebook with email id [ " + emailId + " ");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fbProp.getUserAuthorizationUri())
				.queryParam("response_type", "code").queryParam("app_id", fbProp.getClientId())
				.queryParam("redirect_uri", fbProp.getCallbackurl()).queryParam("state", emailId)
				.queryParam("locale", "en_US");

		URI requestURI = builder.build().encode().toUri();
		logger.debug("Making request to OAuth for facebook with email id [ " + emailId + " and url ["
				+ requestURI.toString() + " ]");

		return new ModelAndView("redirect:" + requestURI.toString());

	}

	@RequestMapping(path = "/pl-facebook/callback", method = RequestMethod.GET)
	public ResponseVO callback(@RequestParam String code, @RequestParam String state) {

		logger.debug("Request Arrived at the callback Url for facebook with email id [ " + state
				+ " ] and authorization code [ " + code + " ]");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fbProp.getAccessTokenUri())
				.queryParam("client_id", fbProp.getClientId()).queryParam("redirect_uri", fbProp.getCallbackurl())
				.queryParam("client_secret", fbProp.getClientSecret()).queryParam("code", code);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		URI requestURI = builder.build().encode().toUri();
		logger.debug("Making request to OAuth access token for facebook with email id [ " + state + " and url ["
				+ requestURI.toString());
		HttpEntity<AccessToken> response = restTemplate.exchange(requestURI, HttpMethod.GET, entity, AccessToken.class);

		// Getting the long lived token
		UriComponentsBuilder builderForLongLiveToken = UriComponentsBuilder.fromHttpUrl(fbProp.getAccessTokenUri())
				.queryParam("client_id", fbProp.getClientId()).queryParam("client_secret", fbProp.getClientSecret())
				.queryParam("grant_type", "fb_exchange_token")
				.queryParam("fb_exchange_token", response.getBody().getAccess_token());

		URI llTokenRequestURI = builderForLongLiveToken.build().encode().toUri();
		logger.debug("Making request to OAuth access token - LONG LIVE TOKEN - for facebook with email id [ " + state
				+ " and url [" + requestURI.toString());
		HttpEntity<AccessToken> llTokenresponse = restTemplate.exchange(llTokenRequestURI, HttpMethod.GET, entity,
				AccessToken.class);

		// Saving the access token into the system
		Credentials cred = new Credentials();
		cred.setAccessToken(llTokenresponse.getBody().getAccess_token());
		cred.setEmailId(state);
		cred.setProviderName("facebook");
		long time = System.currentTimeMillis();
		java.sql.Timestamp dateTime = new java.sql.Timestamp(time);
		cred.setAccessTokenCreationTime(dateTime);
		credService.saveCredential(cred);

		ResponseVO responseVO = new ResponseVO();
		responseVO.setStaus(SUCCESS);
		responseVO.setMessage("Outh access_token for facebook with emailId [" + state + " ] has been generated");
		responseVO.setData(cred);

		return responseVO;

	}

	@RequestMapping(path = "/pl-facebook/user/access", method = RequestMethod.GET)
	public ResponseVO getUserAuthenticationDetails(@RequestParam String emailId) {

		// Getting the access token for the user
		Credentials cred = credService.getUserDetails(emailId);
		logger.debug(
				"Access Token for the user with email id [ " + emailId + " ] is  [ " + cred.getAccessToken() + " ]");

		ResponseVO responseVO = new ResponseVO();

		if (cred != null && cred.getAccessToken() != null) {
			responseVO.setStaus(SUCCESS);
			responseVO.setMessage("Access Token related data have been fetched for emailId [" + emailId + " ]");
			responseVO.setData(cred);
		} else {
			responseVO.setStaus(FAILURE);
			responseVO.setMessage(
					"Access Token related data is not available in the system for emailId [" + emailId + " ]");
			responseVO.setData(cred);
		}

		return responseVO;

	}

	@RequestMapping(path = "/pl-facebook/user/profile", method = RequestMethod.GET)
	public ResponseVO getUserDetails(@RequestParam String emailId) {

		// Getting the access token for the user
		String access_token = credService.getAccessToken(emailId);
		logger.debug("Access Token for the user with email id [ " + emailId + " ] is  [ " + access_token + " ]");

		String fields = "email,name,first_name,last_name,gender,birthday,hometown,id,relationship_status";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fbProp.getUserInfoUri())
				.queryParam("access_token", access_token).queryParam("fields", fields);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		URI requestURI = builder.build().encode().toUri();
		logger.debug("Making request to get the User details with email [ " + emailId + " and url ["
				+ requestURI.toString());
		HttpEntity<FacebookUser> response = restTemplate.exchange(requestURI, HttpMethod.GET, entity,
				FacebookUser.class);

		ResponseVO responseVO = new ResponseVO();
		responseVO.setStaus(SUCCESS);
		responseVO.setMessage("Facebook specific user details have been fetched for emailId [" + emailId + " ]");
		responseVO.setData(response.getBody());

		return responseVO;

	}

	@RequestMapping(path = "/pl-facebook/user/videos", method = RequestMethod.GET)
	public List<FacebookVideos> getUserVideos(@RequestParam String emailId) {
		// Getting the access token for the user
		String access_token = credService.getAccessToken(emailId);
		logger.debug("Access Token for the user with email id [ " + emailId + " ] is  [ " + access_token + " ]");

		String fields = "id,created_time,description,embed_html";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fbProp.getUserInfoUri() + "/videos")
				.queryParam("access_token", access_token).queryParam("fields", fields);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		URI requestURI = builder.build().encode().toUri();
		logger.debug(
				"Making request to get the User videos with email [ " + emailId + " and url [" + requestURI.toString());
		HttpEntity<List<FacebookVideos>> response = restTemplate.exchange(requestURI, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<FacebookVideos>>() {
				});

		return response.getBody();

	}

	/*
	 * public String unauthorize(){
	 * 
	 * }
	 */

}
