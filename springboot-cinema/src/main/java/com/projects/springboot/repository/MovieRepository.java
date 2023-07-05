package com.projects.springboot.repository;

import com.projects.springboot.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    // Add custom query methods if needed
    List<Movie> findByIsTrendingTrue();
    List<Movie> findByIsRecommendedTrue();
    List<Movie> findByGenre(String genre);

    @Query("SELECT DISTINCT m.genre FROM Movie m")
    List<String> findAllGenres();
}
