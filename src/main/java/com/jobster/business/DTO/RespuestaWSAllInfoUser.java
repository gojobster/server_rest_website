package com.jobster.business.DTO;

import com.jobster.server.model.tables.records.UsersRecord;

import java.util.List;

public class RespuestaWSAllInfoUser {

	public String urlAvatar;
	public String email;
	public String telefono;
	public String name;
	public String surname;
	public List<String> skills;
	public List<String> idioms;

	public RespuestaWSAllInfoUser(UsersRecord jobster, List<String> lstSkills, List<String> lstIdioms) {
		this.urlAvatar = jobster.getPictureUrl();
		this.email = jobster.getEmail();
		this.telefono = jobster.getPhoneNumber();
		this.name = jobster.getName();
		this.surname = jobster.getSurrname();
		this.skills = lstSkills;
		this.idioms = lstIdioms;
	}
}
