package com.wenky.app.business.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PROFILE_DETAILS")
public class UserProfileDetails {

	@Id
	@GeneratedValue
	// @GeneratedValue(generator = "identity")
	// @GenericGenerator(name = "identity",strategy="identity")
	@Column(name = "ID_USERPROFILE_DETAILS")
	private Long  idUserProfileDetails;
	@Embedded
	Address address;
	@OneToOne
	@JoinColumn(name = "FK_USER_PROFILE")
	UserProfile userProfile;

	public Long getIdUserProfileDetails() {
		return idUserProfileDetails;
	}

	public void setIdUserProfileDetails(Long idUserProfileDetails) {
		this.idUserProfileDetails = idUserProfileDetails;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
}