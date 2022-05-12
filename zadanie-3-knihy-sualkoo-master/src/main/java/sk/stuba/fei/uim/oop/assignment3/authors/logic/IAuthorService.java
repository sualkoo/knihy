package sk.stuba.fei.uim.oop.assignment3.authors.logic;

import sk.stuba.fei.uim.oop.assignment3.authors.web.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.authors.data.Author;

import java.util.List;

public interface IAuthorService {
    List<Author> getAll();
    Author getAuthorById(Long id);
    Author createAuthor(AuthorRequest request);
    void deleteById(Long id);
    Author updateAuthor(Long id, AuthorRequest author);

}
