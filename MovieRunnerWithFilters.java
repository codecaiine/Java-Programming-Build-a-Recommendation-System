import java.util.ArrayList;
import java.util.Collections;


public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Movie size (# of movie in list) : " + MovieDatabase.size());
        System.out.println("Rater size (# of ppl who rates) : " + tr.getRaterSize());
        ArrayList<Rating> ratingList = tr.getAverageRatings(1);
        System.out.println("Found ratings for movies : " + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            System.out.printf("%-10.2f%s%n", i.getValue(), MovieDatabase.getTitle(i.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");//do i need put filename here?
        ArrayList<Rating> ratingList = sr.getAverageRatings(1);
        String movieTitle = "The Godfather";
        boolean exist = false;
        for (Rating i : ratingList) {  //i.getItem() is it's movie id;
            if (MovieDatabase.getTitle(i.getItem()).equals(movieTitle)) {
                System.out.printf("%-10.2f%s%n", i.getValue(), movieTitle);
                exist = true;
            }
        }
        if (!exist) {
            System.out.println("MOVIE TITLE NOT FOUND!");
            
        }
    }
    
    public void printAverageRatingsByYear() {
        ThirdRatings tr3 = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Movie size (# of movie in list) : " + MovieDatabase.size());// need initialize first.
        System.out.println("Rater size (# of ppl who rates) : " + tr3.getRaterSize());
        ArrayList<Rating> ratingList = tr3.getAverageRatingsByFilter(1, new YearAfterFilter(2000));
        System.out.println("Found ratings for movies : " + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            System.out.printf("%-10.2f%-10s%-5s%n", i.getValue(), MovieDatabase.getYear(i.getItem()), MovieDatabase.getTitle(i.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings tr4 = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Movie size (# of movie in list) : " + MovieDatabase.size());
        System.out.println("Rater size (# of ppl who rates) : " + tr4.getRaterSize());
        ArrayList<Rating> ratingList = tr4.getAverageRatingsByFilter(1, new GenreFilter("Crime"));
        System.out.println("Found ratings for movies : " + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            System.out.printf("%-10.2f%-16s%-5s%n", i.getValue(), MovieDatabase.getTitle(i.getItem()), MovieDatabase.getGenres(i.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr5 = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Movie size (# of movie in list) : " + MovieDatabase.size());
        System.out.println("Rater size (# of ppl who rates) : " + tr5.getRaterSize());
        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, new MinutesFilter(110, 170));
        System.out.println("Found ratings for movies : " + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            System.out.printf("%-10.2f%-16s%-5s%n", i.getValue(), MovieDatabase.getMinutes(i.getItem()), MovieDatabase.getTitle(i.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr5 = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Movie size (# of movie in list) : " + MovieDatabase.size());
        System.out.println("Rater size (# of ppl who rates) : " + tr5.getRaterSize());
        Filter d = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, d);
        System.out.println("Found ratings for movies : " + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            System.out.printf("%-10.2f%-16s%-5s%n", i.getValue(), MovieDatabase.getTitle(i.getItem()), MovieDatabase.getDirector(i.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr5 = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Movie size (# of movie in list) : " + MovieDatabase.size());// n
        System.out.println("Rater size (# of ppl who rates) : " + tr5.getRaterSize());
        //why here must use AllFilters instead of Filter?
        AllFilters all = new AllFilters();
        all.addFilter(new GenreFilter("Romance"));
        all.addFilter(new YearAfterFilter(1980));
        
        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, all);
        System.out.println("Found ratings for movies : " + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            System.out.printf("%-10.2f%-10d%-16s%-5s%n", i.getValue(), MovieDatabase.getYear(i.getItem()), MovieDatabase.getTitle(i.getItem()), MovieDatabase.getGenres(i.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr5 = new ThirdRatings("data/ratings_short.csv");//do i need put filename here?
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Movie size (# of movie in list) : " + MovieDatabase.size());// need initialize first.
        System.out.println("Rater size (# of ppl who rates) : " + tr5.getRaterSize());
        //why here must use AllFilters instead of Filter?
        AllFilters all = new AllFilters();
        all.addFilter(new MinutesFilter(30, 170));
        all.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        
        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, all);
        System.out.println("Found ratings for movies : " + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            System.out.printf("%-10.2fTime:%-10s%-16s%-5s%n", i.getValue(), MovieDatabase.getMinutes(i.getItem()), MovieDatabase.getTitle(i.getItem()), MovieDatabase.getDirector(i.getItem()));
        }
    }
    
    public static void main(String[] args) {
        MovieRunnerWithFilters mra = new MovieRunnerWithFilters();
        System.out.println("---------------Test: printAverageRatings()----------------");
        mra.printAverageRatings();
        System.out.println("---------------Test: getAverageRatingOneMovie() ----------------");
        mra.getAverageRatingOneMovie();
        System.out.println("---------------Test: printAverageRatingsByYear() ----------------");
        mra.printAverageRatingsByYear();
        System.out.println("---------------Test: printAverageRatingsByGenre() ----------------");
        mra.printAverageRatingsByGenre();
        System.out.println("---------------Test: printAverageRatingsByMinutes() ----------------");
        mra.printAverageRatingsByMinutes();
        System.out.println("---------------Test: printAverageRatingsByDirectors() ----------------");
        mra.printAverageRatingsByDirectors();
        System.out.println("---------------Test: printAverageRatingsByYearAfterAndGenre() ----------------");
        mra.printAverageRatingsByYearAfterAndGenre();
        System.out.println("---------------Test: printAverageRatingsByDirectorsAndMinutes() ----------------");
        mra.printAverageRatingsByDirectorsAndMinutes(); 
    }
}
