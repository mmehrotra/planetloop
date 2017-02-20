package com.planetloop.integration.facebook.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
/*@JsonPropertyOrder({ "id", "first_name", "last_name", "display_name", "gender", "about_me", "date_of_birth",
		"member_since", "is_active", "address" })*/
@JsonAutoDetect
public class User implements Serializable{

	@JsonProperty("id")
	private String id;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("displayName")
	private String displayName;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("aboutMe")
	private String aboutMe;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("memberSince")
	private String memberSince;
	@JsonProperty("isActive")
	private String isActive;
	@JsonProperty("address")
	private List<Address> address = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("displayName")
	public String getDisplayName() {
		return displayName;
	}

	@JsonProperty("displayName")
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("aboutMe")
	public String getAboutMe() {
		return aboutMe;
	}

	@JsonProperty("aboutMe")
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	@JsonProperty("dateOfBirth")
	@JsonGetter("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("dateOfBirth")
	@JsonSetter("dateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("memberSince")
	public String getMemberSince() {
		return memberSince;
	}

	@JsonProperty("memberSince")
	public void setMemberSince(String memberSince) {
		this.memberSince = memberSince;
	}

	@JsonProperty("isActive")
	public String getIsActive() {
		return isActive;
	}

	@JsonProperty("isActive")
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@JsonProperty("address")
	public List<Address> getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(List<Address> address) {
		this.address = address;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
