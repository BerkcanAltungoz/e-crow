package ecrow.backend.business.concretes;

import ecrow.backend.business.abstracts.ContactMessageService;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.dataAccess.concretes.ContactMessageDao;
import ecrow.backend.entities.concretes.ContactMessage;
import ecrow.backend.entities.dtos.ContactMessageAddDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactMessageManager implements ContactMessageService {
    private final ContactMessageDao contactMessageDao;

    public ContactMessageManager(ContactMessageDao contactMessageDao) {
        this.contactMessageDao = contactMessageDao;
    }


    @Override
    public DataResult<List<ContactMessage>> getAll() {
        return new SuccessDataResult<>(contactMessageDao.findAll());
    }

    @Override
    public DataResult<ContactMessage> getById(Integer id) {
        if(!contactMessageDao.existsById(id)){
            return new ErrorDataResult<>("Contact Message Not Found");
        }
        return new SuccessDataResult<>(contactMessageDao.findById(id).get());
    }

    @Override
    public Result deleteById(Integer id) {
        if(!contactMessageDao.existsById(id)){
            return new ErrorResult("Contact Message Not Found");
        }
        contactMessageDao.deleteById(id);
        return new SuccessResult("Contact Message Deleted");
    }

    @Override
    public Result deleteAll() {
        contactMessageDao.deleteAll();
        return new SuccessResult("All Contact Messages Deleted");
    }

    @Override
    public Result add(ContactMessageAddDto contactMessageAddDto) {
        ContactMessage contactMessage = ContactMessage.builder()
                .name(contactMessageAddDto.getName())
                .surname(contactMessageAddDto.getSurname())
                .email(contactMessageAddDto.getEmail())
                .subject(contactMessageAddDto.getSubject())
                .message(contactMessageAddDto.getMessage())
                .dateCreated(LocalDateTime.now())
                .build();
        contactMessageDao.save(contactMessage);
        return new SuccessResult("Contact Message Saved");
    }
}
