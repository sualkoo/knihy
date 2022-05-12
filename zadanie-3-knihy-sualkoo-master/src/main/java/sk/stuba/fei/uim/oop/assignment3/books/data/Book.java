package sk.stuba.fei.uim.oop.assignment3.books.data;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.authors.data.Author;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long author;
    private Long pages;
    private Long amount;
    private Long lendCount;
    @ManyToOne
    private Author authorObject;
}
