package com.pl.facebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pl.facebook.entity.Credentials;
import com.pl.facebook.repository.CredentialsRepository;

@Service
public class CredentialsService {

	@Autowired
	CredentialsRepository credentialsRepository;

	public Credentials getUserDetails(String emailId) {

		Credentials cred = credentialsRepository.findOne(emailId);
		if (cred != null) {
			return cred;
		} else {
			return null;
		}

	}

	public void saveCredential(Credentials cred) {
		credentialsRepository.save(cred);
	}
	
	public String getAccessToken(String emailId) {

		Credentials cred = credentialsRepository.findOne(emailId);
		if (cred != null) {
			return cred.getAccessToken();
		} else {
			return null;
		}

	}


}
