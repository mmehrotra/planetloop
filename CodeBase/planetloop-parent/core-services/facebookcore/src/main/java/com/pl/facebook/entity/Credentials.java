package com.pl.facebook.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Credentials {

	@Id
	String emailId;

	String providerName;

	String accessToken;

	Timestamp accessTokenCreationTime;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Timestamp getAccessTokenCreationTime() {
		return accessTokenCreationTime;
	}

	public void setAccessTokenCreationTime(Timestamp accessTokenCreationTime) {
		this.accessTokenCreationTime = accessTokenCreationTime;
	}

}
