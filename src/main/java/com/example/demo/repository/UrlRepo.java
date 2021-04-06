/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.UrlMapping;

@Repository
public interface UrlRepo extends JpaRepository<UrlMapping, Long> {

	@Query(value = "SELECT Url FROM url_mapping WHERE Short_Url= :variable",nativeQuery = true)
	String findByShortUrl(@Param("variable")String ShortUrl);
	
	
	@Query(value = "SELECT Short_Url FROM url_mapping WHERE Url= :variable LIMIT 1;",nativeQuery = true)
	String findByUrl(@Param("variable")String Url);
}
