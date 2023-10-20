package com.metehan.movie;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document
public class UserDocument {

    @Id
    private String id;
    private String email;
    private List<Movie> favorites;

}
