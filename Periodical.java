package librarymanagement;

public class Periodical extends LibraryItem {
    private static final long serialVersionUID = 1L;
    private int issueNumber;
    private String publisher;
    private int numberOfCopies;
    private Format format;

    public enum Format {
        PRINTED, ELECTRONIC
    }

    public Periodical(String id, String title, Author author, int issueNumber, String publisher) {
        super(id, title, author);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    public Periodical(String id, String title, Author author, int issueNumber, String publisher, int numberOfCopies, Format format) {
        super(id, title, author);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
        this.numberOfCopies = numberOfCopies;
        this.format = format;
    }


    public int getIssueNumber() {
        return issueNumber;
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
        return "Periodical{" +
                "issueNumber=" + issueNumber +
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

