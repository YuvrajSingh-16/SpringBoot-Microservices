package com.uvsingh.moviecatalogservice.models;

public class Movie {
	private String movieId;
	private String name;
	
	
	public Movie() {
		super();
	}


	public Movie(String movieId, String name) {
		super();
		this.movieId = movieId;
		this.name = name;
	}


	public String getId() {
		return movieId;
	}


	public void setId(String movieId) {
		this.movieId = movieId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
