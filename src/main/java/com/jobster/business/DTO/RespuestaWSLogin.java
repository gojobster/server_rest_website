package com.jobster.business.DTO;

import com.jobster.business.types.VerificationStateType;

public class RespuestaWSLogin {
	public String apiKey;
	public String urlAvatar;
	public String username;
	public String name;
	public String surname;
	public VerificationStateType state;

	public RespuestaWSLogin(String apiKey, String urlAvatar, String username, String name, String surname) {
		this.apiKey = apiKey;
		this.urlAvatar = urlAvatar;
		this.username = username;
		this.name = name;
		this.surname = surname;
	}

	public RespuestaWSLogin(String apiKey, String urlAvatar, String username, String name, String surname, VerificationStateType state) {
		this.apiKey = apiKey;
		this.urlAvatar = urlAvatar;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.state = state;
	}
}
