
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    private double getAverageByID(String id, int minimalRaters) {
        double totalRating = 0;
        int count = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for (Rater r : myRaters) {
            if (r.getRating(id) != -1) {
                totalRating += r.getRating(id);
                count += 1;
            }
        }
        
        if (count < minimalRaters) {
            return 0.0;
        } else {
            return totalRating/count;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : myMovies) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            if (avgRating != 0.0){
                ret.add(new Rating(movieID, avgRating));
            }
        }
        return ret;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : myMovies) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            if (avgRating != 0.0){
                ret.add(new Rating(movieID, avgRating));
            }
        }
        return ret;
    }
        
    private double dotProduct(Rater me, Rater r) {
        double product = 0.0;
        ArrayList<String> myRatedMovies = me.getItemsRated();
        ArrayList<String> rRatedMovies = r.getItemsRated();
        
        for (String movieID : myRatedMovies) {
            if (rRatedMovies.contains(movieID)) {
                product += (me.getRating(movieID) - 5) * (r.getRating(movieID) - 5);
            }
        }
        return product;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        
        for (Rater r : raters) {
            if (! r.equals(me)) {
                double similarity = dotProduct(me, r);
                if (similarity > 0) {
                    similarities.add(new Rating(r.getID(), similarity));
                }
            }
        }
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> weightedMovieRatings = new ArrayList<Rating>();
        ArrayList<String> movieList = MovieDatabase.filterBy(new TrueFilter());
       
        ArrayList<Rating> raterSimilarities = getSimilarities(id);
        int sampleSize;
        if (raterSimilarities.size() > numSimilarRaters) {
            sampleSize = numSimilarRaters;
        } else {
            sampleSize = raterSimilarities.size();
        }
        
        for (String movieID : movieList) {
            double weightedRating = 0.0;
            int countRating = 0;
            for (int k = 0; k < sampleSize; k++) {
                Rating r = raterSimilarities.get(k);
                String raterID = r.getItem();
                double similarity = r.getValue();
                
                if (RaterDatabase.getRater(raterID).hasRating(movieID)) {
                    weightedRating += RaterDatabase.getRater(raterID).getRating(movieID) * similarity;
                    countRating += 1;
                }
            }
            
            if (countRating >= minimalRaters) {
                weightedMovieRatings.add(new Rating(movieID, weightedRating/countRating));
            }
        }
        return weightedMovieRatings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> weightedMovieRatings = new ArrayList<Rating>();
        ArrayList<String> movieList = MovieDatabase.filterBy(filterCriteria);
       
        ArrayList<Rating> raterSimilarities = getSimilarities(id);
        int sampleSize;
        if (raterSimilarities.size() > numSimilarRaters) {
            sampleSize = numSimilarRaters;
        } else {
            sampleSize = raterSimilarities.size();
        }
        
        for (String movieID : movieList) {
            double weightedRating = 0.0;
            int countRating = 0;
            for (int k = 0; k < sampleSize; k++) {
                Rating r = raterSimilarities.get(k);
                String raterID = r.getItem();
                double similarity = r.getValue();
                
                if (RaterDatabase.getRater(raterID).hasRating(movieID)) {
                    weightedRating += RaterDatabase.getRater(raterID).getRating(movieID) * similarity;
                    countRating += 1;
                }
            }
            
            if (countRating >= minimalRaters) {
                weightedMovieRatings.add(new Rating(movieID, weightedRating/countRating));
            }
        }
        return weightedMovieRatings;
    }
    
}
