package com.projects.springboot.service;

import com.projects.springboot.model.Movie;
import com.projects.springboot.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
        @Autowired
        private MovieRepository movieRepository;

        public List<Movie> getTrendingMovies() {
            return movieRepository.findByIsTrendingTrue();
        }

        public List<Movie> getRecommendedMovies() {
            return movieRepository.findByIsRecommendedTrue();
        }

        public List<Movie> getMoviesByGenre(String genre) {
            return movieRepository.findByGenre(genre);
        }

        public List<String> getAllGenres() {
           return movieRepository.findAllGenres();
    }
    }

