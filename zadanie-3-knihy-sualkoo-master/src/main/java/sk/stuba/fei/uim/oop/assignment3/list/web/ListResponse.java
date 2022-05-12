package sk.stuba.fei.uim.oop.assignment3.list.web;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.books.data.Book;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;

import java.util.List;

@Getter
public class ListResponse {
    private final Long id;
    private final List<Book> lendingList;
    private final Boolean lended;

    public ListResponse(LendingList list)
    {
        this.id = list.getId();
        this.lendingList = list.getLendingList();
        this.lended = list.getLended();
    }
}
