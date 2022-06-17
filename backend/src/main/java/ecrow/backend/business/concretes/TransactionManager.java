package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.TransactionService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.CustomerDao;
import ecrow.backend.dataAccess.concretes.EmployeeDao;
import ecrow.backend.dataAccess.concretes.StatusDao;
import ecrow.backend.dataAccess.concretes.TransactionDao;
import ecrow.backend.entities.concretes.Transaction;
import ecrow.backend.entities.dtos.TransactionAddDto;
import ecrow.backend.entities.dtos.TransactionStatusUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionManager implements TransactionService {
    private final TransactionDao transactionDao;
    private final EmployeeDao employeeDao;
    private final CustomerDao customerDao;
    private final StatusDao statusDao;

    @Autowired
    public TransactionManager(TransactionDao transactionDao, EmployeeDao employeeDao, CustomerDao customerDao, StatusDao statusDao) {
        this.transactionDao = transactionDao;
        this.employeeDao = employeeDao;
        this.customerDao = customerDao;
        this.statusDao = statusDao;
    }

    @Override
    public DataResult<List<Transaction>> getAll() {
        return new SuccessDataResult<>(transactionDao.findAll());
    }

    @Override
    public DataResult<List<Transaction>> getAllSortedByStatus() {
        return new SuccessDataResult<>(transactionDao.getAllByOrderByFkStatus_NameAsc());
    }

    @Override
    public DataResult<Transaction> getById(Integer id) {
        if(!transactionDao.existsById(id)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        return new SuccessDataResult<>(transactionDao.findById(id).get()) ;
    }

    @Override
    public DataResult<List<Transaction>> getByFkEmployeeId(Integer employeeId) {
        if(!transactionDao.existsByFkEmployeeId(employeeId)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        return new SuccessDataResult<>(transactionDao.getByFkEmployeeId(employeeId));
    }

    @Override
    public DataResult<List<Transaction>> getByFkBuyerId(Integer buyerId) {
        if(!transactionDao.existsByFkBuyerId(buyerId)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        return new SuccessDataResult<>(transactionDao.getByFkBuyerId(buyerId));
    }

    @Override
    public DataResult<List<Transaction>> getByFkSellerId(Integer sellerId) {
        if(!transactionDao.existsByFkSellerId(sellerId)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        return new SuccessDataResult<>(transactionDao.getByFkSellerId(sellerId));
    }

    @Override
    public Result deleteById(Integer id) {
        if(!transactionDao.existsById(id)){
            return new ErrorDataResult<>("Transaction Not Found");
        }
        transactionDao.deleteById(id);
        return new SuccessResult("Transaction Deleted");
    }

    @Override
    public Result add(TransactionAddDto transactionAddDto) {
        if(!employeeDao.existsById(transactionAddDto.getFkEmployeeId())){
            return new ErrorResult("Invalid Employee Id");
        }
        else if(!customerDao.existsById(transactionAddDto.getFkBuyerId())){
            return new ErrorResult("Invalid Buyer Id");
        }
        else if(!customerDao.existsById(transactionAddDto.getFkSellerId())){
            return new ErrorResult("Invalid Seller Id");
        }
        Transaction transaction = Transaction.builder()
                .fkEmployee(employeeDao.findById(transactionAddDto.getFkEmployeeId()).get())
                .fkBuyer(customerDao.findById(transactionAddDto.getFkBuyerId()).get())
                .fkSeller(customerDao.findById(transactionAddDto.getFkSellerId()).get())
                .itemName(transactionAddDto.getItemName())
                .itemPrice(transactionAddDto.getItemPrice())
                .employeeFee(transactionAddDto.getEmployeeFee())
                .build();
        transactionDao.save(transaction);
        return new SuccessResult("Transaction Added");
    }

    @Override
    public Result updateStatus(TransactionStatusUpdateDto transactionStatusUpdateDto) {
        if(!transactionDao.existsById(transactionStatusUpdateDto.getId())){
            return new ErrorResult("Transaction Not Found");
        }
        Transaction transaction = transactionDao.findById(transactionStatusUpdateDto.getId()).get();
        transaction.setFkStatus(statusDao.findById(transactionStatusUpdateDto.getFkStatusId()).get());
        return new SuccessResult("Updated Transaction Status");
    }
}
