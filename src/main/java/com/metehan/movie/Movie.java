package com.metehan.movie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
    private Integer movieId;
    private String note;
}
