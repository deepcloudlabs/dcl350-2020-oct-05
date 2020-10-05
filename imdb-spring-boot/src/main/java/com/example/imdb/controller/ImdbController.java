package com.example.imdb.controller;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.domain.Movie;
import com.example.imdb.service.MovieService;

// Ctrl + Shift + O : Organize import
@RestController
@RequestScope // Meta Annotation
@CrossOrigin
@RequestMapping("movies")
public class ImdbController {
	// private List<MovieService> movieServices;
	private MovieService movieService;

//	private AtomicInteger counter = new AtomicInteger(0);

	// public ImdbController(@PersistenceSource(PersistenceSourceEnum.MONGO)
	// MovieService movieService) { // Constructor Injection
	// public ImdbController(List<MovieService> movieServices) { // Constructor
	// Injection
//		this.movieServices = movieServices;
//		movieServices.stream()
//		             .map(Object::getClass)
//		             .map(Class::getName)
//		             .forEach(System.err::println);
	public ImdbController(MovieService movieService) { // Constructor Injection
		this.movieService = movieService;
	}

	// http://localhost:7100/imdb/api/v1/movies?from=1970&to=1979
	@GetMapping(params = { "from", "to" })
	public Collection<Movie> findMoviesInYearRange(@RequestParam int from, @RequestParam int to) {
		System.err.println(movieService.getClass().getName());
		// int index = counter.getAndIncrement() % movieServices.size(); // round-robin
		// return this.movieServices.get(index).findAllMoviesByYearRange(from, to);
		return this.movieService.findAllMoviesByYearRange(from, to);
	}
}
