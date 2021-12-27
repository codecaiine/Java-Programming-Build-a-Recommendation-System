
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String[] dirList;
    
    public DirectorsFilter(String directors) {
        dirList = directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        String movieDirector = MovieDatabase.getDirector(id);
        for (String dir : dirList) {
            if (movieDirector.indexOf(dir) != -1) {
                return true;
            }
        }
        return false;
    }
    
    public String getName() {
        return "DirectorFilter " + dirList;
    }

}
