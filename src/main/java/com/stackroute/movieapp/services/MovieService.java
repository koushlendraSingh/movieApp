package com.stackroute.movieapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.repositories.MovieRepository;

@Service
public class MovieService {
	
	
	private MovieRepository movieRepository;

	@Autowired
	public MovieService(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	public Movie saveMovie(Movie movie) {
		return movieRepository.save(movie);
		
	}
	
	public Iterable<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
}
