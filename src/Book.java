import java.lang.Integer;
public class Book {

    private String title;
    private String author;
    private String genre;
    private int numPages;


    public Book(String title, String author, String genre, int numPages) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.numPages = numPages;

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumPages() {
        return numPages;
    }

    public boolean equals(Book other) {
        boolean authorMatches = author.equals(other.getAuthor());
        boolean titleMatches = title.equals(other.getTitle());
        return authorMatches && titleMatches;
    }
    public Integer compareTo(Book other,String sortBy){
        if(sortBy.equals("author")){
            return(this.author.compareTo(other.getAuthor()));
        }
        if(sortBy.equals("title")){
            return(this.title.compareTo(other.getTitle()));
        }
        if(sortBy.equals("genre")){
            return(this.genre.compareTo(other.getGenre()));
        }
        if(sortBy.equals("numPages")){
            Integer a = new Integer(this.numPages);
            Integer b = new Integer(other.getNumPages());
            return(a.compareTo(b));
        }
        else{
            return null;
        }
    }

}