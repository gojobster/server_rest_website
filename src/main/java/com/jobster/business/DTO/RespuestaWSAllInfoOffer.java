package com.jobster.BLL.DTO;

import java.util.List;

public class RespuestaWSAllInfoOffer {

	public String urlAvatar;
	public String email;
	public String telefono;
	public String name;
	public String surname;
	public List<String> lstSkills;
	public List<String> lstIdioms;

	public RespuestaWSAllInfoOffer(String urlAvatar, String email, String telefono, String name, String surname, List<String> lstSkills, List<String> lstIdioms) {
		this.urlAvatar = urlAvatar;
		this.email = email;
		this.telefono = telefono;
		this.name = name;
		this.surname = surname;
		this.lstSkills = lstSkills;
		this.lstIdioms = lstIdioms;
	}
}
