package com.uvsingh.movieinfoservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uvsingh.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {
 
	@GetMapping("/{movieId}")
	public Movie getMovie(@PathVariable String movieId) {
		return new Movie(movieId, "Transformers");
	}
}
