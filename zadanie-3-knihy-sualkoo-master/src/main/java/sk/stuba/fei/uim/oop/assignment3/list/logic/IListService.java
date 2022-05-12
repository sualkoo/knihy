package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.list.web.ListRequest;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;

import java.util.List;

public interface IListService {
    List<LendingList> getAll();
    LendingList getLendingListById(Long id);
    LendingList createLendingList(ListRequest request);
    void deleteListById(Long id);
    void removeBookFromList(Long id);
    LendingList addBookToList(Long id);
    void lendList(Long id);
}
