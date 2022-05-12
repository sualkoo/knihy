package sk.stuba.fei.uim.oop.assignment3.authors.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.authors.web.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.authors.data.Author;
import sk.stuba.fei.uim.oop.assignment3.authors.data.IAuthorRepository;

import java.util.List;

@Service
public class AuthorService implements IAuthorService{
    private final IAuthorRepository repository;

    @Autowired
    public AuthorService(IAuthorRepository repository) {
        this.repository = repository;
        var author1 = new Author();
        author1.setName("anton");
        var author2 = new Author();
        author2.setName("feri");
        repository.save(author1);
        repository.save(author2);
        System.out.println("Added author1 with id: " + author1.getId());
        System.out.println("Added author2 with id: " + author2.getId());
    }

    @Override
    public List<Author> getAll()
    {
        return this.repository.findAll();
    }

    @Override
    public Author createAuthor(AuthorRequest request)
    {
        Author newAuthor = new Author();
        newAuthor.setName(request.getName());
        newAuthor.setSurname(request.getSurname());
        return repository.save(newAuthor);
    }

    @Override
    public Author getAuthorById(Long id)
    {
        var authorOptional = repository.findById(id);
        return authorOptional.orElse(null);
    }

    @Override
    public  Author updateAuthor(Long id, AuthorRequest author)
    {
        Author oldAuthor = this.repository
                .findById(id)
                .orElseThrow();
        if (author.getName() != null)
        {
            oldAuthor.setName(author.getName());
        }
        if (author.getSurname() != null)
        {
            oldAuthor.setSurname(author.getSurname());
        }
        return this.repository.save(oldAuthor);
    }

    @Override
    public void deleteById(Long id)
    {
        this.repository.delete(this.repository.findById(id).orElseThrow());
    }
}
