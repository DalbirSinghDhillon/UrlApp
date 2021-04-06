package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UrlMapping {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false,unique=true)
	private String Url;
	
	@Column(nullable=false,unique=true)
	private String ShortUrl;
	
	public UrlMapping() {
		
	}

	public UrlMapping(String url, String shortUrl) {
		Url = url;
		ShortUrl = shortUrl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getShortUrl() {
		return ShortUrl;
	}

	public void setShortUrl(String shortUrl) {
		ShortUrl = shortUrl;
	}

	@Override
	public String toString() {
		return "UrlMapping [id=" + id + ", Url=" + Url + ", ShortUrl=" + ShortUrl + "]";
	}
	
	
	
}
