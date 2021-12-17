import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");//do i need put filename here?
        System.out.println("Movie size = " + sr.getMovieSize());
        System.out.println("Rater size = " + sr.getRaterSize());
        ArrayList<Rating> ratingList = sr.getAverageRatings(2);
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            System.out.printf("%-10.2f%s%n", i.getValue(), sr.getTitle(i.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");//do i need put filename here?
        ArrayList<Rating> ratingList = sr.getAverageRatings(2);
        String movieTitle = "The Godfather";
        for (Rating i : ratingList) {
            if (sr.getTitle(i.getItem()).equals(movieTitle)) {
                System.out.printf("%-10.2f%s%n", i.getValue(), sr.getTitle(i.getItem()));
            }
        }
    }
    
    public static void main(String[] args) {
        MovieRunnerAverage mra = new MovieRunnerAverage();
        System.out.println("---------------print test----------------");
        mra.printAverageRatings();
        System.out.println("---------------get average rating one movie test----------------");
        mra.getAverageRatingOneMovie();
    }
}
