package coursework.multiverse.controller;

import coursework.multiverse.dto.MovieDto;
import coursework.multiverse.entity.Movie;
import coursework.multiverse.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController {
    private MovieService movieService;

    @PostMapping("/add")
    public Movie addMovie(@RequestBody MovieDto movieDto) {
        return movieService.saveMovie(movieDto);
    }
}
