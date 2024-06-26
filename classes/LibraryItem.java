package librarymanagement;

import java.io.Serializable;

public abstract class LibraryItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Author author;

    public LibraryItem(String id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }

    public void setAuthor(Author author2) {
        this.author = author2;
    }

    protected abstract void setNumberOfCopies(int numberOfCopies);


}

