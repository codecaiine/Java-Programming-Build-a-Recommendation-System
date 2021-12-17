
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
    private String myID;
    /* Change the ArrayList of type Rating private variable to a HashMap<String,Rating>.
       The key in the HashMap is a movie ID, and
        its value is a rating associated with this movie.*/
    //private HashMap<String, ArrayList<Rating>> myRatings;//why not this one?
    private HashMap<String, Rating> myRatings;
    
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }
    
    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item, rating));//item is string id?
    }
    
    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }
    
    public String getID() {
        return myID;
    }
    
    public double getRating(String item) {
        //        for (int k = 0; k < myRatings.size(); k++) {
        //            if (myRatings.get(k).getItem().equals(item)) {
        //                return myRatings.get(k).getValue();
        //            }
        //        }
        if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue();
        }
        return -1;
    }
    
    public int numRatings() {
        return myRatings.size();
    }
    
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>(myRatings.keySet());
        //        for (int k = 0; k < myRatings.size(); k++) {
        //            list.add(myRatings.get(k).getItem());
        //        }
        //
        return list;//arrayList of item;
    }
}
