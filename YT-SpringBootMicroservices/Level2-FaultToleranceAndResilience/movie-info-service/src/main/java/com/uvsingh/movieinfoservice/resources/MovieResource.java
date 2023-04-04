package com.uvsingh.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.uvsingh.movieinfoservice.models.Movie;
import com.uvsingh.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
 
	@GetMapping("/{movieId}")
	public Movie getMovie(@PathVariable String movieId) {
		System.out.println("[+] Here................");
		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, 
				MovieSummary.class);
		System.out.println(movieSummary);
		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
//		return new Movie("12", "Transformers", "Machines from other planet");
	}
}
