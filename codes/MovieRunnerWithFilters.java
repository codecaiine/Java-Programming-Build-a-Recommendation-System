import java.util.*;

public class MovieRunnerWithFilters {
    private String ratingsfile = "data/ratings.csv";
    private String moviefile = "ratedmoviesfull.csv";
    
    public void printAverageRating() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 35;
        ArrayList<Rating> avgRatingList = tr.getAverageRatings(minimalRaters);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\t" + MovieDatabase.getTitle(r.getItem()));
        }  
        System.out.println("========================================================");
    }
    
    public void printAverageRatingByYear() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter yf = new YearAfterFilter(2000);
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 20;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, yf);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\tYear: " + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("========================================================");
    }
    
    public void printAverageRatingByGenre() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter gf = new GenreFilter("Comedy");
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 20;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, gf);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println("========================================================");
    }
    
    public void printAverageRatingByMinutes() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter mf = new MinutesFilter(105, 135);
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 5;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, mf);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\t" + "Time: " + MovieDatabase.getMinutes(r.getItem()) + " Mins" + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("========================================================");
    }
    
    public void printAverageRatingByDirector() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 4;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, df);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println("========================================================");
    }
    
    public void printAverageRatingByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1990));
        f.addFilter(new GenreFilter("Drama"));
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 8;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, f);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\tYear: " + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println("========================================================");
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        AllFilters f = new AllFilters();
        f.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        f.addFilter(new MinutesFilter(90, 180));
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 3;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, f);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\tTime: " + MovieDatabase.getMinutes(r.getItem()) + " Mins\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println("========================================================");
    }

}