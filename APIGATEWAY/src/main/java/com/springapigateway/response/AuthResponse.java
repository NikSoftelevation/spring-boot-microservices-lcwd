package com.springapigateway.response;

import java.util.Collection;

public class AuthResponse {

	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expiredAt;
	private Collection<String> authorities;

	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String userId, String accessToken, String refreshToken, long expiredAt,
			Collection<String> authorities) {
		super();
		this.userId = userId;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expiredAt = expiredAt;
		this.authorities = authorities;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(long expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Collection<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<String> authorities) {
		this.authorities = authorities;
	}

}
