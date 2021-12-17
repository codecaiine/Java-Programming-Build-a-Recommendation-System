
public class DirectorsFilter implements Filter {
    private String directors;
    
    public DirectorsFilter(String directors) {
        this.directors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] dir = directors.split(",");
        for (String i : dir) {
            if (MovieDatabase.getDirector(id).contains(i)) {
                return true;
            }
        }
        return false;
    }
}
