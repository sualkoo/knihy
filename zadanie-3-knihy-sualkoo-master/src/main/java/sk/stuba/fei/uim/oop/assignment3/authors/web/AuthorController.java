package sk.stuba.fei.uim.oop.assignment3.authors.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.authors.logic.IAuthorService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private IAuthorService service;

    @GetMapping()
    public List<AuthorResponse> getAllAuthors()
    {
        return this.service.getAll()
                .stream()
                .map(AuthorResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable("id") Long id)
    {
        if (this.service.getAuthorById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var result = new AuthorResponse(this.service.getAuthorById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public AuthorResponse createAuthor(@RequestBody AuthorRequest request)
    {
        return new AuthorResponse(this.service.createAuthor(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuthorById(@PathVariable("id") Long id)
    {
        try {
            this.service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<AuthorResponse> updateAuthorById(@PathVariable("id") Long id, @RequestBody AuthorRequest request)
    {
        try
        {
            var result = new AuthorResponse(this.service.updateAuthor(id, request));
            return new ResponseEntity<>(result, HttpStatus.OK);

        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
