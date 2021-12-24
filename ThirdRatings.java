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
   
    private double getAverageByID(String movieID, int minimalRaters) {
        int count = 0;
        double total = 0;
        for (Rater i : myRaters) {
            double rating = i.getRating(movieID);
            if (rating != -1) {
                count++;
                total += rating;
            }
        }
        System.out.printf("Movie ID : Count : Total : Rating = %-10s%-5d%-7.2f%-7.2f%n", movieID, count, total, total / count);
        if (count >= minimalRaters) return total / count;
        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
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
        Filter trueFilter = new TrueFilter();
        ArrayList<String> movieID = MovieDatabase.filterBy(trueFilter);
 
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
        System.out.println("---------------test-------------");
        System.out.println(sr.getAverageRatings(2));
    }
    
}
