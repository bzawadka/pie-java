package pl.bzawadka.pie.graphs;

import java.util.List;
import java.util.Set;

public class RecommendedMovies {

    public static class Movie {
        float rank;
        List<Movie> relatedMovies;
    }

    public static Set<Movie> findNHighestRankedRelatedMovies(Movie movie, int N) {
        return null;
    }
}
