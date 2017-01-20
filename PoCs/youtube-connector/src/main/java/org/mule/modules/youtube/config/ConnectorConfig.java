package org.mule.modules.youtube.config;

import org.mule.api.annotations.components.Configuration;
import org.mule.api.annotations.oauth.OAuth2;
import org.mule.api.annotations.oauth.OAuthScope;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.Configurable;

@Configuration(friendlyName = "Configuration")
@OAuth2( friendlyName="OAuth 2.0", authorizationUrl = "https://api.myconnector.com/uas/oauth/authorize", 
accessTokenUrl = "https://api.myconnector.com/uas/oauth/accessToken", 
accessTokenRegex = "\"access_token\":\"([^&]+?)\"",
expirationRegex = "\"expires_in\":([^&]+?),", 
refreshTokenRegex = "\"refresh_token\":\"([^&]+?)\"" )
public class ConnectorConfig {
    
	/**
     * Client Id
     */
    @Configurable
    private String clientId;

    /**
     * Client secret
     */
    @Configurable
    private String clientSecret;
    
    /**
     * Facebook permissions
     */
    @Configurable
    @Default(value = "https://www.googleapis.com/auth/youtube, https://www.googleapis.com/auth/youtube.upload")
    @OAuthScope
    private String scope;

    /**
     * Set clientId
     *
     * @param clientId the clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Get clientId
     */
    public String getClientId() {
        return this.clientId;
    }

    /**
     * Set clientSecret
     *
     * @param clientSecret the clientSecret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * Get clientSecret
     */
    public String getClientSecret() {
        return this.clientSecret;
    }
    
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}