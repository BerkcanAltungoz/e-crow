package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.Employee;
import ecrow.backend.entities.concretes.Transaction;
import ecrow.backend.entities.dtos.TransactionAddDto;
import ecrow.backend.entities.dtos.TransactionStatusUpdateDto;

import java.util.List;

public interface TransactionService {
    DataResult<List<Transaction>> getAll();
    DataResult<List<Transaction>> getAllSortedByStatus();
    DataResult<Transaction> getById(Integer id);
    DataResult<List<Transaction>> getByFkEmployeeId(Integer employeeId);
    DataResult<List<Transaction>> getByFkBuyerId(Integer buyerId);
    DataResult<List<Transaction>> getByFkSellerId(Integer sellerId);
    Result deleteById(Integer id);

    Result add(TransactionAddDto transactionAddDto);
    Result updateStatus(TransactionStatusUpdateDto transactionStatusUpdateDto);
}
