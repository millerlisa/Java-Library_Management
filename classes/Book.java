package librarymanagement;


public class Book extends LibraryItem {
    private static final long serialVersionUID = 1L;
    private String isbn;
    private String publisher;
    private int numberOfCopies;
    private Format format;

    public enum Format {
        PRINTED, ELECTRONIC, AUDIO
    }   

    public Book(String id, String title, Author author, String isbn, String publisher, int numberOfCopies, Format format) {
        super(id, title, author);
        this.isbn = isbn;
        this.publisher = publisher;
        this.numberOfCopies = numberOfCopies;
        this.format = format;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public Format getFormat() {
        return format;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", format='" + format + '\'' +
                ", " + super.toString() +
                '}';
    }

    @Override
    protected void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}

