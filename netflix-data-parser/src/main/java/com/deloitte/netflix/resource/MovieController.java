package com.deloitte.netflix.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.netflix.entity.Movie;
import com.deloitte.netflix.service.MovieService;

@RestController
@RequestMapping("/")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/tvshows")
	public ResponseEntity<List<?>> getMovieInfoByCount(@RequestParam(required=false, value="count")Integer count, 
			@RequestParam(required=false,value="movieType")String movieType, 
			@RequestParam(required=false,value="country")String country, 
			@RequestParam(required=false,value="startDate")String startDate, 
			@RequestParam(required=false,value="endDate")String endDate) {
		/*
		if(count==null) {
			return ResponseEntity.status(401).build();
		} */
		
		//System.out.println(count);
		List<Movie> movies = movieService.getMovies(count, movieType, country, startDate, endDate);
		return ResponseEntity.ok().body(movies);
	}

}
