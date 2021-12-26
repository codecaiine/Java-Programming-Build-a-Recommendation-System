
public class Movie {
    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private String poster;
    private int minutes;
    
    public Movie(String anID, String aTitle, String aYear, String theGenres) {
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
    }
    
    public Movie(String anID, String aTitle, String aYear, String theGenres, String aDirector,
                 String aCountry, String aPoster, int theMinutes) {
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
        director = aDirector;
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }
   
    public String getID() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
   
    public int getYear() {
        return year;
    }
    
    public String getGenres() {
        return genres;
    }
    
    public String getCountry() {
        return country;
    }
    
    public String getDirector() {
        return director;
    }
    
    public String getPoster() {
        return poster;
    }
    
    public int getMinutes() {
        return minutes;
    }
    
    public String toString() {
        String result = "Movie [id=" + id + ", title=" + title + ", year=" + year;
        result += ", genres= " + genres + "]";
        return result;
    }
}
