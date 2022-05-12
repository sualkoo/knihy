package sk.stuba.fei.uim.oop.assignment3.books.logic;

import sk.stuba.fei.uim.oop.assignment3.books.web.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.books.data.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAll();
    Book createBook(BookRequest request);
    Book getBookById(Long id);
    void deleteById(Long id);
    Book updateById(Long id, BookRequest request);
    Long getAmount(Long id);
    void addAmount(Long id, Long amount);
    Long getLendCount(Long id);
}
