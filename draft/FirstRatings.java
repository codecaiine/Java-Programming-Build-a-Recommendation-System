
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;


public class FirstRatings {

    public ArrayList<Movie> loadMovies(String fileName) {
        ArrayList<Movie> movies = new ArrayList<>();
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord i : parser) {
            String id = i.get("id");
            String title = i.get("title");
            String year = i.get("year");
            String country = i.get("country");
            String genre = i.get("genre");
            String director = i.get("director");
            int minutes = Integer.parseInt(i.get("minutes"));
            String poster = i.get("poster");
            Movie m = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(m);
        }
        return movies;
    }
    
    public void testLoadMovies() {
        
        ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");
        for (Movie i : movies) {
 
        }
        System.out.println("The size of movie list is = " + movies.size());
        
        HashMap<String, ArrayList<Movie>> mapDirector = new HashMap<>();
        for (Movie i : movies) {

            String director = i.getDirector();
            String[] directors = director.split(", ");
            
            for (String j : directors) {
                if (!mapDirector.containsKey(j)) {
                    ArrayList<Movie> a = new ArrayList<>();
                    a.add(i);
                    mapDirector.put(j, a);
                } else {
                    ArrayList<Movie> a = mapDirector.get(j);
                    a.add(i);
                    mapDirector.put(j, a);
                }
            }
        }
        
        int maxNumMovieByDirector = 0;
        String maxDirector = "";
        for (String key : mapDirector.keySet()) {
            if (mapDirector.get(key).size() > maxNumMovieByDirector) {
                maxNumMovieByDirector = mapDirector.get(key).size();
                //maxDirector = key;
            }
        }
      
        for (String key : mapDirector.keySet()) {
            if (mapDirector.get(key).size() == maxNumMovieByDirector) {
                System.out.println("The director who produce the most movie is : " + maxNumMovieByDirector + " : " + key);
            }
        }
    }
    
    public ArrayList<Rater> loadRaters(String fileName) {
        ArrayList<Rater> raters = new ArrayList<>();// why no need <PlainRater>?
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord i : parser) {
            String rater_id = i.get("rater_id");
            String movie_id = i.get("movie_id");
            double rating = Double.parseDouble(i.get("rating"));
            int count = 0;
            for (Rater j : raters) {
                if (j.getID().contains(rater_id)) {
                    //Rater m = new Rater(rater_id);
                    j.addRating(movie_id, rating);
                    count++;
                    break;
                }
            }
            if (count == 0) {
  
                EfficientRater m = new EfficientRater(rater_id);
                m.addRating(movie_id, rating);
                raters.add(m);
            }
        }
        return raters;
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
        System.out.println("The size of rater list is = " + raters.size());
        for (Rater i : raters) {
            if (i.getID().equals("2")) {
                System.out.println("USER # " + i.getID() + " : " + i.numRatings() + " ratings");
                ArrayList<String> rating = i.getItemsRated();
                for (String j : rating) {
                    System.out.print("movie_id: " + j + " ");
                    System.out.println(i.getRating(j) + " rating");
                }
            }
        }
        int max = 0;
        for (Rater i : raters) {
            if (i.numRatings() > max) {
                max = i.numRatings();
            }
        }
        for (Rater i : raters) {
            if (i.numRatings() == max) {
                System.out.println("The maximum rate is from USER # " + i.getID() + " : " + i.numRatings() + " ratings");
            }
        }

        int count = 0;
        String movie_id = "1798709";
        for (Rater i : raters) {
            ArrayList<String> rating = i.getItemsRated();
            if (rating.contains(movie_id)) {
                count++;
                System.out.println(count + " : " + "id = " + i.getID() + rating);
            }
        }
        System.out.println("The total # of " + movie_id + " that been rated is " + count);
        ArrayList<String> differentMovie = new ArrayList<>();
        for (Rater i : raters) {
            ArrayList<String> rating = i.getItemsRated();
            for (String j : rating) {
                if (!differentMovie.contains(j)) {
                    differentMovie.add(j);
                }
            }
        }
        System.out.println("The total # of movie is " + differentMovie.size());
    }
    
    public static void main(String[] args) {
        FirstRatings a = new FirstRatings();
        System.out.println("-------------------MOVIES-------------------");
        a.testLoadMovies();
        System.out.println();
        System.out.println();
        System.out.println("-------------------RATERS-------------------");
        a.testLoadRaters();
    }
}
