
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
         // process every record from csv file
         FileResource fr = new FileResource(filename);
         ArrayList<Movie> Movielist = new ArrayList<Movie>();
         for (CSVRecord record : fr.getCSVParser()){
             Movie currMovie = new Movie(record.get("id"),record.get("title"),
             record.get("year"),record.get("genre"),record.get("director"),
             record.get("country"),record.get("poster"),
             Integer.parseInt(record.get("minutes")));
             
             Movielist.add(currMovie);
         }
        
         return Movielist;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> loadMovies_short = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("loadMovies: " + loadMovies_short);
        System.out.println("Number of movies: " + loadMovies_short.size());
        
        int genreCount = 0;
        int minCount = 0;
        HashMap<String,Integer> directorCounts = new HashMap();
        for(int i=0; i<loadMovies_short.size(); i++){
            Movie currMovie = loadMovies_short.get(i);
            if (currMovie.getGenres().contains("Comedy")){
                genreCount++;
            }
            if (currMovie.getMinutes() > 150){
                minCount++;
            }
            String currDirector = currMovie.getDirector();
            if (directorCounts.containsKey(currDirector)){
                directorCounts.put(currDirector, directorCounts.get(currDirector)+1);
            }
            else {
                directorCounts.put(currDirector, 1);
            }
        }
        int dirWithMaxMovies = Collections.max(directorCounts.values());
        ArrayList<String> movieWithMaxdirs = new ArrayList();
        for(String dir:directorCounts.keySet()){
            if(directorCounts.get(dir) == dirWithMaxMovies){
                    movieWithMaxdirs.add(dir);
            }
        }
        System.out.println("Director with Max Movies: " + dirWithMaxMovies);
        System.out.println("Number of Comedy Movies: " + genreCount);
        System.out.println("Number of 150min+ Movies: " + minCount);
        System.out.println("Max movies by one director: "+ dirWithMaxMovies);
        System.out.println("Directors who directed the max movies: " + movieWithMaxdirs);
    }
    
    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> raters = new ArrayList<EfficientRater>();
        ArrayList<String> raterIDs = new ArrayList<String>();
        FileResource fr = new FileResource(filename);
        
        for (CSVRecord record : fr.getCSVParser()) {
            String raterId = record.get("rater_id");
            String movieId = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            String time = record.get("time");
            
            if (raterIDs.contains(raterId)) {
                Iterator<EfficientRater> raterIterator = raters.iterator();
                while (raterIterator.hasNext()) {
                    EfficientRater r = raterIterator.next();
                    if (r.getID().equals(raterId)) {
                        r.addRating(movieId, rating);
                        break;
                    }
                }
            } 
            else {
                EfficientRater newRater = new EfficientRater(raterId);
                newRater.addRating(movieId, rating);
                raters.add(newRater);
                raterIDs.add(raterId);
            }
        }
        return raters;
    }
    
    public ArrayList<String> getRaterWithMostRatings(ArrayList<EfficientRater> raters) {
        ArrayList<String> raterIDs = new ArrayList<String>();
        HashMap<Integer, ArrayList<EfficientRater>> numRaterHash = new HashMap<Integer, ArrayList<EfficientRater>>();
        
        for (EfficientRater r : raters) {
            int num = r.numRatings();
            
            ArrayList<EfficientRater> currRaters = numRaterHash.getOrDefault(num, new ArrayList<EfficientRater>());
            currRaters.add(r);
            numRaterHash.put(num, currRaters);
        }
        
        int maxRates = Collections.max(numRaterHash.keySet());
        System.out.println("Max ratings per rater: " + maxRates);
        for (EfficientRater r : numRaterHash.get(maxRates)) {
            raterIDs.add(r.getID());
        }
        return raterIDs;
    }
    
    public void testLoadRaters() {
        ArrayList<EfficientRater> raters = loadRaters("data/ratings.csv");
        System.out.println("Total number of raters: " + raters.size());

        for (EfficientRater r : raters) {
            if (r.getID().equals(new String("193"))) {
                System.out.println("Number of ratings " + r.numRatings());
                break;
            }
        }
        
        ArrayList<String> raterWithMostRatings = getRaterWithMostRatings(raters);
        System.out.println("Raters with most ratings:\n" + raterWithMostRatings);
        
        String movieID = "1798709";
        int raterOfMovie = 0;
        for (EfficientRater r : raters) {
            if (r.getItemsRated().contains(movieID)) {
                 raterOfMovie++;
            }
        }
        System.out.println("Num of ratings for " + movieID + " is " + raterOfMovie);

        int numMovieRated = 0;
        ArrayList<String> moviesRated = new ArrayList<String>();
        for (EfficientRater r : raters) {
            for (String item : r.getItemsRated()) {
                if (! moviesRated.contains(item)) {
                    moviesRated.add(item);
                    numMovieRated++;
                }
            }
        }
        System.out.println("Total number of movies rated: " + numMovieRated);
    }
}

