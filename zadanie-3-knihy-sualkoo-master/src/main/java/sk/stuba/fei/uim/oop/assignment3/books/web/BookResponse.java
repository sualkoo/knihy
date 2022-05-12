package sk.stuba.fei.uim.oop.assignment3.books.web;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.books.data.Book;

@Getter
public class BookResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Long author;
    private final Long pages;
    private final Long amount;
    private final Long lendCount;

    public BookResponse(Book book)
    {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.author = book.getAuthor();
        this.pages = book.getPages();
        this.amount = book.getAmount();
        this.lendCount = book.getLendCount();
    }
}
