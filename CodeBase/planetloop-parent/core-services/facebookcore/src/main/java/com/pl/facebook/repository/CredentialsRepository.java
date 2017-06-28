package com.pl.facebook.repository;

import org.springframework.data.repository.CrudRepository;

import com.pl.facebook.entity.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, String> {

}
