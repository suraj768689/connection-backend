package com.projects.springboot.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Embeddable
public class CastDetail {
    private String starName;
    private String starImage;
    private String starRole;
}