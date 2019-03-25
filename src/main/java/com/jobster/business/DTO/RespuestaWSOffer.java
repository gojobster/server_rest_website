package com.jobster.business.DTO;

import java.sql.Timestamp;

public class RespuestaWSOffer {
	public int id_offer;
	public String nameCompany;
	public String path_image_company;
	public String position;
	public String summary;
	public String city;
	public int reward;
	public Integer salary_min;
	public Integer salary_max;
	public Timestamp date_init;
	public Timestamp date_end;

	public RespuestaWSOffer(int id_offer, String nameCompany, String path_image_company, String position, String summary, String city,
							int reward, Integer salary_min, Integer salary_max, Timestamp date_init, Timestamp date_end) {
		this.id_offer = id_offer;
		this.nameCompany = nameCompany;
		this.path_image_company = path_image_company;
		this.position = position;
		this.summary = summary;
		this.city = city;
		this.reward = reward;
		this.salary_min = salary_min;
		this.salary_max = salary_max;
		this.date_init = date_init;
		this.date_end = date_end;
	}
}
