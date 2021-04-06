package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UrlMapping;
import com.example.demo.repository.UrlRepo;

@Service
public class MappingService {

	private UrlRepo urlrepo;

	@Autowired
	public MappingService(UrlRepo urlrepo) {
		this.urlrepo = urlrepo;
	}

	public String addUrlMapping(String Url,String ShortUrl) {
     
		UrlMapping urlmapping = new UrlMapping(Url, ShortUrl);
		
		//check if long url already added
		String alreadyAdded=null;
		alreadyAdded=urlrepo.findByUrl(Url);
		
		if(alreadyAdded==null)
		{
			urlrepo.save(urlmapping);
			System.out.println("Added to database");
			return ShortUrl;
		}
		
		
		
		return alreadyAdded;
		
		
		
	}

	public String getLongUrl(String ShortUrl) {
		//System.out.println("Long url Got from database  :  "+ urlrepo.findByShortUrl(ShortUrl));
	
		
		return urlrepo.findByShortUrl(ShortUrl);
	}
}
