package com.stackroute.movieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.services.MovieService;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

	private MovieService movieService;
	
	@Autowired
	MovieController(MovieService movieService){
		this.movieService = movieService;
	}
	
	@PostMapping("/movie")
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
		return new ResponseEntity<Movie> (movieService.saveMovie(movie), HttpStatus.CREATED);
	}
	
	@GetMapping("/movies")
	public ResponseEntity<?> getAllMovie() {
		return new ResponseEntity<Iterable<Movie>> (movieService.getAllMovies(), HttpStatus.OK);
	}
}
