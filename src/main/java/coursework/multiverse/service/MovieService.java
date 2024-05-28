package coursework.multiverse.service;

import coursework.multiverse.dto.MovieDto;
import coursework.multiverse.entity.Movie;

public interface MovieService {
    Movie saveMovie(MovieDto movieDto);
}
