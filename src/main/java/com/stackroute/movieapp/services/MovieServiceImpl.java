package com.stackroute.movieapp.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.repositories.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	
	private MovieRepository movieRepository;


	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
		
	}

	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException{
		if(movieRepository.existsById(movie.getId())) {throw new MovieAlreadyExistsException("existed");}
		else
		{return movieRepository.save(movie);}
		
	}
	
	public Iterable<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	public Movie updateMovie(Movie movie) {
		return movieRepository.save(movie);
	}
	public Optional<Movie> getMovieById(int id) throws MovieNotFoundException {
		if( movieRepository.existsById(id)) {return movieRepository.findById(id);}
		else {throw new MovieNotFoundException("nhi hai");}
	}
	public List<Movie> getMovieByTitle(String title) throws MovieNotFoundException {
		if((movieRepository.findByTitle(title)).equals(null))
		{throw new MovieNotFoundException("nhi hai");}
		else {return movieRepository.findByTitle(title);}
		
	}
	public boolean deleteMovie(int id) throws MovieNotFoundException{
		if(movieRepository.existsById(id)) { movieRepository.deleteById(id);return true;}
		else {throw new MovieNotFoundException("nhi hai");}
		
	}

}
