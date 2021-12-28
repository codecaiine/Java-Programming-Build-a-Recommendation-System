import java.util.*;
public interface Recommender {
    public ArrayList<String> getItemsToRate ();
    public void printRecommendationsFor (String webRaterID);
}

