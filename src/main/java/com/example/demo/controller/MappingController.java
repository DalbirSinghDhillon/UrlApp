package com.example.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.MappingService;

@Controller
public class MappingController {

	
	private MappingService mappingService;
	
	@Autowired
	public MappingController(MappingService mappingService) {
		this.mappingService = mappingService;
	}
	
	@RequestMapping("/")
	public String home() {
		return "index.jsp";
	}
	
	@PostMapping("addLongUrl")  //@ResponseBody
	public String makeShortUrlController(@RequestParam("addLongUrlinput") String longUrl,HttpSession session) {
		System.out.println("makeShortUrlController");
		
		//URI ShortUrl = URI.create(generateShortLink());	
		String ShortUrl =generateShortLink();
		String ReceivedShortUrl=mappingService.addUrlMapping(longUrl,ShortUrl);
		if(ReceivedShortUrl.equals(ShortUrl))
		{
			session.setAttribute("urlInResult",  "http://localhost:8080/"+ShortUrl);
			return "result.jsp";	
		}
		
		session.setAttribute("urlInResult",  "http://localhost:8080/"+ReceivedShortUrl);
		return "resultAlreadyPresent.jsp";
	}
	
	
	
	
	@GetMapping("/{shortUrl}")
	@ResponseBody
	public ResponseEntity<?> redirectToURL(@PathVariable("shortUrl") String shortUrl) throws URISyntaxException {
		
		System.out.println("shortUrl is : "+shortUrl);
		URI longUrl = URI.create( mappingService.getLongUrl(shortUrl)) ;
		HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setLocation(longUrl);
		System.out.println("longurl is : "+longUrl.toString());
		return new ResponseEntity<>(httpHeaders,HttpStatus.MOVED_PERMANENTLY);
		
	}
	
	
	
	private String generateShortLink() {
		String Str = "";
		String Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < 7; i++)
			Str += Chars.charAt((int) Math.floor(Math.random() * Chars.length()));
		return Str;
	}
}
