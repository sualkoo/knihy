package sk.stuba.fei.uim.oop.assignment3.authors.web;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.authors.data.Author;
import sk.stuba.fei.uim.oop.assignment3.books.data.Book;
import sk.stuba.fei.uim.oop.assignment3.books.web.BookResponse;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AuthorResponse {
    private final Long id;
    private final String name;
    private final String surname;
    private final List<BookResponse> bookList;

    public AuthorResponse(Author author)
    {
        this.id = author.getId();
        this.name = author.getName();
        this.surname = author.getSurname();
        this.bookList = author.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
    }

}
