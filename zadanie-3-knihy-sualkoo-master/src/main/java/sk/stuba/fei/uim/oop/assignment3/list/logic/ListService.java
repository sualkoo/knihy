package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.list.web.ListRequest;
import sk.stuba.fei.uim.oop.assignment3.list.data.IListRepository;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;

import java.util.List;

@Service
public class ListService implements IListService{
    private final IListRepository repository;

    @Autowired
    public ListService(IListRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public List<LendingList> getAll()
    {
        return this.repository.findAll();
    }

    @Override
    public LendingList getLendingListById(Long id)
    {
        var listOptional = repository.findById(id);
        return listOptional.orElse(null);
    }

    @Override
    public LendingList createLendingList(ListRequest request)
    {
        LendingList newList = new LendingList();
        newList.setLendingList(request.getLendingList());
        newList.setLended(request.getLended());
        return repository.save(newList);
    }

    @Override
    public void deleteListById(Long id)
    {
        this.repository.delete(this.repository.findById(id).orElseThrow());
    }

    @Override
    public void removeBookFromList(Long id)
    {

    }

    @Override
    public LendingList addBookToList(Long id)
    {
        return null;
    }

    @Override
    public void lendList (Long id)
    {

    }
}
