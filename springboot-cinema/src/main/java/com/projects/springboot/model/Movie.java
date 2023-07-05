package com.projects.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    @ElementCollection
    private List<String> genre;

    @ElementCollection
    @CollectionTable(name = "cast_details", joinColumns = @JoinColumn(name = "movie_id"))
    private List<CastDetail> cast;

    @Column(name = "releaseDate")
    private Date releaseDate;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private String rating;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "isTrending")
    private Boolean isTrending;

    @Column(name = "isRecommended")
    private Boolean isRecommended;

    @Column(name = "boc")
    private String boc;

    @Column(name = "lang")
    private String lang;

    @Column(name = "runtime")
    private String runtime;

    @Column(name = "producer")
    private String producer;

    @Column(name = "director")
    private String director;
}
