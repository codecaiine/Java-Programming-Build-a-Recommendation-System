import java.util.ArrayList;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        this("ratings.csv");
    }
 
 
    public ThirdRatings(String ratingFile) {
        FirstRatings a = new FirstRatings();
        myRaters = a.loadRaters(ratingFile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    //private helper method
    private double getAverageByID(String movieID, int minimalRaters) {
        int count = 0;
        double total = 0;
        for (Rater i : myRaters) {
            // if (i.hasRating(movieID)) {
            double rating = i.getRating(movieID);
            if (rating != -1) {
                count++;
                total += rating;
                // System.out.println(count + " : " + "id = " + i.getID() + " rating " + rating + " ave " + total);
            }
        }
        System.out.printf("Movie ID : Count : Total : Rating = %-10s%-5d%-7.2f%-7.2f%n", movieID, count, total, total / count);
        if (count >= minimalRaters) return total / count;
        return 0.0;
    }
    
    /**
     * Note that myMovies no longer exists. Instead, you’ll need to get all the movies
     * from the MovieDatabase class and store them in an ArrayList of movie IDs.
     * Thus, you will need to modify getAverageRatings to call MovieDatabase
     * with a filter, and in this case you can use the TrueFilter to get every movie.
     **/
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        //must initialize the filter first.
        Filter trueFilter = new TrueFilter();
        for (String i : MovieDatabase.filterBy(trueFilter)) {
            double ave = getAverageByID(i, minimalRaters);
            if (ave > 0)
                ratingList.add(new Rating(i, ave));//item is string id?
        }
        return ratingList;
    }
    

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter f) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        //must initialize the filter first.
        Filter trueFilter = new TrueFilter();
        ArrayList<String> movieID = MovieDatabase.filterBy(trueFilter);
        
        //        for (String i : movieID) {
        //            double ave = getAverageByID(i, minimalRaters);
        //            if (ave > 0)
        //                ratingList.add(new Rating(i, ave));
        //        }
        for (String i : movieID) {
            if (f.satisfies(i)) {
                double ave = getAverageByID(i, minimalRaters);
                if (ave > 0)
                    ratingList.add(new Rating(i, ave));
            }
        }
        return ratingList;
    }
    
    public static void main(String[] args) {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        //System.out.println(sr.getAverageByID("0790636", 2));
        System.out.println("---------------test-------------");
        System.out.println(sr.getAverageRatings(2));
    }
    
}
