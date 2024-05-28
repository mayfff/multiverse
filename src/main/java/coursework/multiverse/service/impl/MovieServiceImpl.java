package coursework.multiverse.service.impl;

import coursework.multiverse.dto.MovieDto;
import coursework.multiverse.entity.Movie;
import coursework.multiverse.repository.MovieRepository;
import coursework.multiverse.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Movie saveMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        return movieRepository.save(movie);
    }
}
