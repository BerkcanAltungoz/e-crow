package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.ChatMessage;
import ecrow.backend.entities.dtos.ChatMessageAddDto;

import java.util.List;

public interface ChatMessageService {
    DataResult<List<ChatMessage>> getAll();
    DataResult<ChatMessage> getById(Integer id);
    DataResult<List<ChatMessage>> getByFkEmployeeId(Integer employeeId);
    DataResult<List<ChatMessage>> getByFkCustomerId(Integer customerId);
    DataResult<List<ChatMessage>> getByFkCustomerIdAndFkEmployeeId(Integer customerId, Integer employeeId);
    Result deleteById(Integer id);

    Result add(ChatMessageAddDto chatMessageAddDto);
}
