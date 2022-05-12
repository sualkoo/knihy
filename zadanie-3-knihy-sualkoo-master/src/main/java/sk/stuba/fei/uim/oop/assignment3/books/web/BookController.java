package sk.stuba.fei.uim.oop.assignment3.books.web;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.books.logic.IBookService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService service;

    @GetMapping()
    public List<BookResponse> getAllBooks()
    {
        return this.service.getAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public BookResponse createBook(@RequestBody BookRequest request)
    {
        return new BookResponse(this.service.createBook(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") Long id)
    {
        if (this.service.getBookById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var result = new BookResponse(this.service.getBookById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") Long id)
    {
        try{
            this.service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<BookResponse> updateBookById(@PathVariable("id") Long id, @RequestBody BookRequest request)
    {
        try{
            var result = new BookResponse(this.service.updateById(id, request));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/amount")
    public ResponseEntity<JSONObject> getAmount(@PathVariable("id") Long id)
    {
        if (this.service.getBookById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        JSONObject result = new JSONObject();
        result.put("amount", this.service.getBookById(id).getAmount());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{id}/amount")
    public ResponseEntity<JSONObject> addAmount(@PathVariable("id") Long id, @RequestBody BookRequest request)
    {
        if (this.service.getBookById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.addAmount(id, request.getAmount());
        JSONObject result = new JSONObject();
        result.put("amount", this.service.getBookById(id).getAmount());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/lendCount")
    public ResponseEntity<JSONObject> getLendCount(@PathVariable("id") Long id)
    {
        if (this.service.getBookById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        JSONObject result = new JSONObject();
        result.put("lendCount", this.service.getBookById(id).getLendCount());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
