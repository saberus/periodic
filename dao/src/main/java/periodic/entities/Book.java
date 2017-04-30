package periodic.entities;

import periodic.dao.Identified;
import periodic.entities.enums.EGenreBook;

import java.io.Serializable;

public class Book implements Identified<Integer>{

    private Integer id;
    private String name;
    private String author;
    private EGenreBook genre;

    public Book() {
    }

    public Book(Integer id, String name,
                String author,
                EGenreBook genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public EGenreBook getGenre() {
        return genre;
    }

    public void setGenre(EGenreBook genre) {
        this.genre = genre;
    }


}
