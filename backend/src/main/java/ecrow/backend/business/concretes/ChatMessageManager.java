package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.ChatMessageService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.ChatMessageDao;
import ecrow.backend.dataAccess.concretes.CustomerDao;
import ecrow.backend.dataAccess.concretes.EmployeeDao;
import ecrow.backend.entities.concretes.ChatMessage;
import ecrow.backend.entities.dtos.ChatMessageAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageManager implements ChatMessageService {
    private final ChatMessageDao chatMessageDao;
    private final EmployeeDao employeeDao;
    private final CustomerDao customerDao;

    @Autowired
    public ChatMessageManager(ChatMessageDao chatMessageDao, EmployeeDao employeeDao, CustomerDao customerDao) {
        this.chatMessageDao = chatMessageDao;
        this.employeeDao = employeeDao;
        this.customerDao = customerDao;
    }

    @Override
    public DataResult<List<ChatMessage>> getAll() {
        return new SuccessDataResult<>(chatMessageDao.findAll());
    }

    @Override
    public DataResult<ChatMessage> getById(Integer id) {
        if(!chatMessageDao.existsById(id)){
            return new ErrorDataResult<>("Chat Message Not Found");
        }
        return new SuccessDataResult<>(chatMessageDao.findById(id).get());
    }

    @Override
    public DataResult<List<ChatMessage>> getByFkEmployeeId(Integer employeeId) {
        if(!chatMessageDao.existsByFkEmployeeId(employeeId)){
            return new ErrorDataResult<>("Chat Message Not Found");
        }
        return new SuccessDataResult<>(chatMessageDao.getByFkEmployeeId(employeeId));
    }

    @Override
    public DataResult<List<ChatMessage>> getByFkCustomerId(Integer customerId) {
        if(!chatMessageDao.existsByFkCustomerId(customerId)){
            return new ErrorDataResult<>("Chat Message Not Found");
        }
        return new SuccessDataResult<>(chatMessageDao.getByFkCustomerId(customerId));
    }

    @Override
    public DataResult<List<ChatMessage>> getByFkCustomerIdAndFkEmployeeId(Integer customerId, Integer employeeId) {
        if(!chatMessageDao.existsByFkCustomerIdAndFkEmployeeId(customerId,employeeId)){
            return new ErrorDataResult<>("Chat Message Not Found");
        }
        return new SuccessDataResult<>(chatMessageDao.getByFkCustomerIdAndFkEmployeeId(customerId,employeeId));
    }

    @Override
    public Result deleteById(Integer id) {
        if(!chatMessageDao.existsById(id)){
            return new ErrorDataResult<>("Chat Message Not Found");
        }
        chatMessageDao.deleteById(id);
        return new SuccessResult("Chat Message Deleted");
    }

    @Override
    public Result add(ChatMessageAddDto chatMessageAddDto) {
        if (!customerDao.existsById(chatMessageAddDto.getFkCustomerId())){
            return new ErrorResult("Invalid Customer Id");
        }
        else if(!employeeDao.existsById(chatMessageAddDto.getFkEmployeeId())){
            return new ErrorResult("Invalid Employee Id");
        }
        ChatMessage chatMessage = ChatMessage.builder()
                .fkCustomer(customerDao.findById(chatMessageAddDto.getFkCustomerId()).get())
                .fkEmployee(employeeDao.findById(chatMessageAddDto.getFkEmployeeId()).get())
                .message(chatMessageAddDto.getMessage())
                .dateCreated(LocalDateTime.now())
                .build();
        chatMessageDao.save(chatMessage);
        return new SuccessResult("Chat Message Saved");
    }
}
