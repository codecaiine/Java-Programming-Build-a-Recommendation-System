public class GenreFilter implements Filter{
    private String genre;
    public GenreFilter(String withGenre){
        genre = withGenre;
    }
    
    @Override
    public boolean satisfies(String id){
        String thisGenres = MovieDatabase.getGenres(id).toLowerCase();
        if (thisGenres.indexOf(genre.toLowerCase()) != -1) {
            return true;
        } else {
            return false;
        }
    }
    
    public String getName() {
        return "GenreFilter[" + genre + "]";
    }
    

}
