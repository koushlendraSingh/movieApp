package com.stackroute.movieapp.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.services.MovieServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

	private MovieServiceImpl movieService;
	private Environment env;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MovieController(MovieServiceImpl movieService,Environment env){
		this.movieService = movieService;
		this.env=env;
	}
	
	@PostMapping("/movie")
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
		try {
			return new ResponseEntity<Movie> (movieService.saveMovie(movie), HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/movies")
	public ResponseEntity<?> getAllMovie() {
        logger.info("This is an info message");
		return new ResponseEntity<Iterable<Movie>> (movieService.getAllMovies(), HttpStatus.OK);
	}
	@GetMapping("/movie/{id}")
	public ResponseEntity<?> getMoviebyId(@PathVariable int id) {
		logger.debug("This is a debug message");
		try {System.out.println(env.getProperty("com.stackroute.username"));
        logger.info("This is an info message");
			return new ResponseEntity<Optional<Movie>> (movieService.getMovieById(id), HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("This is an error message");
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/movietitle/{title}")
	public ResponseEntity<?> getMoviebyTitle(@PathVariable ("title") String title) {
		 logger.debug("This is a debug message");
		try {logger.info("This is an info message");
			return new ResponseEntity<List<Movie>> (movieService.getMovieByTitle(title), HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("This is an error message");
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/movie")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie) {
		logger.info("This is an info message");
		return new ResponseEntity<Movie> (movieService.updateMovie(movie), HttpStatus.OK);
	}
	@DeleteMapping("/movie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable int id) {
		logger.debug("This is a debug message");
		try{		logger.info("This is an info message");
				movieService.deleteMovie(id);
		return new ResponseEntity<String> (HttpStatus.OK);}
		catch (MovieNotFoundException e){
			logger.error("This is an error message");
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.BAD_REQUEST);
	}}
}
