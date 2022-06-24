package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.ItemTransactionService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.CustomerDao;
import ecrow.backend.dataAccess.concretes.EmployeeDao;
import ecrow.backend.dataAccess.concretes.ItemTransactionDao;
import ecrow.backend.dataAccess.concretes.StatusDao;
import ecrow.backend.entities.concretes.ItemTransaction;
import ecrow.backend.entities.dtos.ItemTransactionAddDto;
import ecrow.backend.entities.dtos.ItemTransactionStatusUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemTransactionManager implements ItemTransactionService {
    private final ItemTransactionDao itemTransactionDao;
    private final EmployeeDao employeeDao;
    private final CustomerDao customerDao;
    private final StatusDao statusDao;

    @Autowired
    public ItemTransactionManager(ItemTransactionDao itemTransactionDao, EmployeeDao employeeDao, CustomerDao customerDao, StatusDao statusDao) {
        this.itemTransactionDao = itemTransactionDao;
        this.employeeDao = employeeDao;
        this.customerDao = customerDao;
        this.statusDao = statusDao;
    }

    @Override
    public DataResult<List<ItemTransaction>> getAll() {
        return new SuccessDataResult<>(itemTransactionDao.findAll());
    }

    @Override
    public DataResult<List<ItemTransaction>> getAllSortedByStatus() {
        return new SuccessDataResult<>(itemTransactionDao.getAllByOrderByFkStatus_NameAsc());
    }

    @Override
    public DataResult<ItemTransaction> getById(Integer id) {
        if(!itemTransactionDao.existsById(id)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        return new SuccessDataResult<>(itemTransactionDao.findById(id).get()) ;
    }

    @Override
    public DataResult<List<ItemTransaction>> getByFkEmployeeId(Integer employeeId) {
        if(!itemTransactionDao.existsByFkEmployeeId(employeeId)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        return new SuccessDataResult<>(itemTransactionDao.getByFkEmployeeId(employeeId));
    }

    @Override
    public DataResult<List<ItemTransaction>> getByFkBuyerId(Integer buyerId) {
        if(!itemTransactionDao.existsByFkBuyerId(buyerId)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        return new SuccessDataResult<>(itemTransactionDao.getByFkBuyerId(buyerId));
    }

    @Override
    public DataResult<List<ItemTransaction>> getByFkSellerId(Integer sellerId) {
        if(!itemTransactionDao.existsByFkSellerId(sellerId)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        return new SuccessDataResult<>(itemTransactionDao.getByFkSellerId(sellerId));
    }

    @Override
    public Result deleteById(Integer id) {
        if(!itemTransactionDao.existsById(id)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        itemTransactionDao.deleteById(id);
        return new SuccessResult("Transaction Deleted");
    }

    @Override
    public Result add(ItemTransactionAddDto itemTransactionAddDto) {
        if(!employeeDao.existsById(itemTransactionAddDto.getFkEmployeeId())){
            return new ErrorResult("Invalid Employee Id");
        }
        else if(!customerDao.existsById(itemTransactionAddDto.getFkBuyerId())){
            return new ErrorResult("Invalid Buyer Id");
        }
        else if(!customerDao.existsById(itemTransactionAddDto.getFkSellerId())){
            return new ErrorResult("Invalid Seller Id");
        }
        ItemTransaction itemTransaction = ItemTransaction.builder()
                .fkEmployee(employeeDao.findById(itemTransactionAddDto.getFkEmployeeId()).get())
                .fkBuyer(customerDao.findById(itemTransactionAddDto.getFkBuyerId()).get())
                .fkSeller(customerDao.findById(itemTransactionAddDto.getFkSellerId()).get())
                .itemName(itemTransactionAddDto.getItemName())
                .itemPrice(itemTransactionAddDto.getItemPrice())
                .employeeFee(itemTransactionAddDto.getEmployeeFee())
                .details(itemTransactionAddDto.getDetails())
                .dateCreated(LocalDateTime.now())
                .fkStatus(statusDao.findById(1).get())
                .build();
        itemTransactionDao.save(itemTransaction);
        return new SuccessResult("Transaction Added");
    }

    @Override
    public Result updateStatus(ItemTransactionStatusUpdateDto itemTransactionStatusUpdateDto) {
        if(!itemTransactionDao.existsById(itemTransactionStatusUpdateDto.getId())){
            return new ErrorResult("Transaction Not Found");
        }
        ItemTransaction itemTransaction = itemTransactionDao.findById(itemTransactionStatusUpdateDto.getId()).get();
        itemTransaction.setFkStatus(statusDao.findById(itemTransactionStatusUpdateDto.getFkStatusId()).get());
        itemTransactionDao.save(itemTransaction);
        return new SuccessResult("Updated Transaction Status");
    }
}
