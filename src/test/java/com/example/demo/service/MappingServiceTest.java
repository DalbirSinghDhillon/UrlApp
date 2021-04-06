package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.Entity.UrlMapping;
import com.example.demo.repository.UrlRepo;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MappingServiceTest {

	@MockBean
	private UrlRepo urlrepo;

	@InjectMocks
	private MappingService mappingService;

	@Test
	void testAddUrlMapping() {
		UrlMapping urlMapping = new UrlMapping("https://digilocker.gov.in", "abcd");

		Mockito.when(urlrepo.findByUrl("https://digilocker.gov.in")).thenReturn(urlMapping.getShortUrl());
		assertThat(mappingService.addUrlMapping("https://digilocker.gov.in", "abcd")).isEqualTo("abcd");
	}

	@Test
	void testGetLongUrl() {
		UrlMapping urlMapping = new UrlMapping("https://digilocker.gov.in", "abcd");

		Mockito.when(urlrepo.findByShortUrl("abcd")).thenReturn(urlMapping.getUrl());
		assertThat(mappingService.getLongUrl("abcd")).isEqualTo("https://digilocker.gov.in");
	}

}
