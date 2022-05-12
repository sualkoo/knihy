package sk.stuba.fei.uim.oop.assignment3.books.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.books.web.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.books.data.Book;
import sk.stuba.fei.uim.oop.assignment3.books.data.IBookRepository;

import java.util.List;

@Service
public class BookService implements IBookService{

    private final IBookRepository repository;

    @Autowired
    public BookService(IBookRepository repository)
    {
        this.repository = repository;
        //Book book = new Book();

    }

    public List<Book> getAll()
    {
        return this.repository.findAll();
    }

    @Override
    public Book createBook(BookRequest request)
    {
        Book newBook = new Book();
        newBook.setName(request.getName());
        newBook.setDescription(request.getDescription());
        newBook.setAuthor(request.getAuthor());
        newBook.setAmount(request.getAmount());
        newBook.setPages(request.getPages());
        newBook.setLendCount(request.getLendCount());
        return this.repository.save(newBook);
    }

    @Override
    public Book getBookById(Long id)
    {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id)
    {
        this.repository.delete(this.repository.findById(id).orElseThrow());
    }

    @Override
    public Book updateById(Long id, BookRequest request)
    {
        Book book = this.repository.findById(id).orElseThrow();

        if (request.getName() != null)
        {
            book.setName(request.getName());
        }
        if (request.getDescription() != null)
        {
            book.setDescription(request.getDescription());
        }
        if (request.getAuthor() != null)
        {
            book.setAuthor(request.getAuthor());
        }
        if (request.getPages() != null)
        {
            book.setPages(request.getPages());
        }
        return this.repository.save(book);
    }

    @Override
    public Long getAmount(Long id)
    {
        Book book = this.repository.findById(id).orElseThrow();
        return book.getAmount();
    }

    @Override
    public void addAmount(Long id, Long amount)
    {
        Book book = this.repository.findById(id).orElseThrow();
        book.setAmount(book.getAmount() + amount);
        this.repository.save(book);
    }

    @Override
    public Long getLendCount(Long id)
    {
        Book book = this.repository.findById(id).orElseThrow();
        return book.getLendCount();
    }


}
