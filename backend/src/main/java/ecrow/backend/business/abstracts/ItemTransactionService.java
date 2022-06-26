package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.ItemTransaction;
import ecrow.backend.entities.dtos.ItemTransactionAddDto;
import ecrow.backend.entities.dtos.ItemTransactionStatusUpdateDto;

import java.util.List;

public interface ItemTransactionService {
    DataResult<List<ItemTransaction>> getAll();
    DataResult<List<ItemTransaction>> getAllSortedByStatus();
    DataResult<ItemTransaction> getById(Integer id);
    DataResult<List<ItemTransaction>> getByFkEmployeeId(Integer employeeId);
    DataResult<List<ItemTransaction>> getByFkBuyerId(Integer buyerId);
    DataResult<List<ItemTransaction>> getByFkSellerId(Integer sellerId);
    DataResult<Boolean> existsByFkEmployeeId(Integer employeeId);
    DataResult<Boolean> existsByFkBuyerId(Integer buyerId);
    DataResult<Boolean> existsByFkSellerId(Integer sellerId);
    Result deleteById(Integer id);

    Result add(ItemTransactionAddDto itemTransactionAddDto);
    Result updateStatus(ItemTransactionStatusUpdateDto itemTransactionStatusUpdateDto);
}
