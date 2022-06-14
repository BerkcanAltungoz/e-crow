package ecrow.backend.business.abstracts;

import ecrow.backend.core.utilities.results.DataResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.entities.concretes.ContactMessage;
import ecrow.backend.entities.dtos.ContactMessageAddDto;

import java.util.List;

public interface ContactMessageService {
    DataResult<List<ContactMessage>> getAll();
    DataResult<ContactMessage> getById(Integer id);
    Result deleteById(Integer id);
    Result deleteAll();

    Result add(ContactMessageAddDto contactMessageAddDto);
}
