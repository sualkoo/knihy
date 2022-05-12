package sk.stuba.fei.uim.oop.assignment3.list.web;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.list.logic.IListService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private IListService service;

    @GetMapping()
    public List<ListResponse> getAll()
    {
        return this.service.getAll()
                .stream()
                .map(ListResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ListResponse> getLendingListById(@PathVariable("id") Long id)
    {
        if (this.service.getLendingListById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var result = new ListResponse(this.service.getLendingListById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ListResponse createList(@RequestBody ListRequest request)
    {
        return new ListResponse(this.service.createLendingList(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteListById(@PathVariable("id") Long id)
    {
        try{
            this.service.deleteListById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{id}/add")
    public ResponseEntity<JSONObject> addBookToList(@PathVariable("id") Long id, @RequestBody ListRequest request)
    {
        if (this.service.getLendingListById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return null;
    }

}
