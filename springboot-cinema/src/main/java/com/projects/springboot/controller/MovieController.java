package com.projects.springboot.controller;

import com.projects.springboot.exception.ResourceNotFoundException;
import com.projects.springboot.model.Movie;
import com.projects.springboot.repository.MovieRepository;
import com.projects.springboot.service.MovieService;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projects.springboot.model.CastDetail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;

   // @GetMapping
    @GetMapping
    public List<Movie> getAllEmployees(){
        return movieRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {

        List<CastDetail> castDetails = new ArrayList<>();
        for (CastDetail castDetail : movie.getCast()) {
            CastDetail newCastDetail = new CastDetail();
            newCastDetail.setStarName(castDetail.getStarName());
            newCastDetail.setStarImage(castDetail.getStarImage());
            newCastDetail.setStarRole(castDetail.getStarRole());
            castDetails.add(newCastDetail);
        }
        movie.setCast(castDetails);

        Movie savedMovie = movieRepository.save(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable  long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id:" + id));
        return ResponseEntity.ok(movie);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movieDetails) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);
        if (existingMovie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update movie details
        existingMovie.setTitle(movieDetails.getTitle());
        existingMovie.setReleaseDate(movieDetails.getReleaseDate());
        existingMovie.setDescription(movieDetails.getDescription());
        existingMovie.setRating(movieDetails.getRating());
        existingMovie.setImageUrl(movieDetails.getImageUrl());
        existingMovie.setIsRecommended(movieDetails.getIsRecommended());
        existingMovie.setIsTrending(movieDetails.getIsTrending());
        existingMovie.setBoc(movieDetails.getBoc());
        existingMovie.setLang(movieDetails.getLang());
        existingMovie.setRuntime(movieDetails.getRuntime());
        existingMovie.setProducer(movieDetails.getProducer());
        existingMovie.setDirector(movieDetails.getDirector());

        List<String> existingGenres = existingMovie.getGenre();
        List<String> updatedGenres = movieDetails.getGenre();
        for (String genre : updatedGenres) {
            if (!existingGenres.contains(genre)) {
                existingGenres.add(genre);
            }
        }

        // Update cast details
        List<CastDetail> castDetails = new ArrayList<>();
        for (CastDetail castDetail : movieDetails.getCast()) {
            CastDetail newCastDetail = new CastDetail();
            newCastDetail.setStarName(castDetail.getStarName());
            newCastDetail.setStarImage(castDetail.getStarImage());
            newCastDetail.setStarRole(castDetail.getStarRole());
            castDetails.add(newCastDetail);
        }
        existingMovie.setCast(castDetails);

        Movie updatedMovie = movieRepository.save(existingMovie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id: " + id));

        movieRepository.delete(movie);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/trending")
    public ResponseEntity<List<Movie>> getTrendingMovies() {
        List<Movie> trendingMovies = movieService.getTrendingMovies();
        return new ResponseEntity<>(trendingMovies, HttpStatus.OK);
    }

    @GetMapping("/recommended")
    public ResponseEntity<List<Movie>> getRecommendedMovies() {
        List<Movie> recommendedMovies = movieService.getRecommendedMovies();
        return new ResponseEntity<>(recommendedMovies, HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable("genre") String genre) {
        List<Movie> moviesByGenre = movieService.getMoviesByGenre(genre);
        return new ResponseEntity<>(moviesByGenre, HttpStatus.OK);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<String>> getAllGenres() {
        List<String> genres = movieService.getAllGenres();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }


}
