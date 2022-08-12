package bb.com.donation.service.impl;

import bb.com.donation.dto.message.MessageSaveDTO;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Donation;
import bb.com.donation.model.Message;
import bb.com.donation.model.Person;
import bb.com.donation.repository.MessageRepository;
import bb.com.donation.service.MessageService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    final MessageRepository messageRepository;
    final PersonServiceImp personService;
    final DonationServiceImp donationService;

    public MessageServiceImpl(MessageRepository messageRepository, PersonServiceImp personService, DonationServiceImp donationService) {
        this.messageRepository = messageRepository;
        this.personService = personService;
        this.donationService = donationService;
    }

    public List<Message> getAll() {
            return messageRepository.findAll ();
    }

    public Message getById(Long id) {
        return messageRepository.findById (id).orElseThrow (() -> new ValidacaoException ("Message not found"));
    }

    public Message save(MessageSaveDTO messageSaveDTO) {
        Person personBy = personService.getById (messageSaveDTO.getPersonById ());
        Message lastMessage = this.getById (messageSaveDTO.getLastMessageId ());
        Person personTo = personService.getById (messageSaveDTO.getPersonToId ());
        Donation donation = donationService.getById (messageSaveDTO.getDonationId ());
        if(personBy == null || personTo == null ) {
            throw new ValidacaoException ("Person not found");
        }
        if(lastMessage == null) {
            throw new ValidacaoException ("Donation not found");
        }
        if(donation == null) {
            throw new ValidacaoException ("Donation not found");
        }
        try {

            Message messagePost = Message.builder ()
                    .person_by (personBy)
                    .bodyMessage (messageSaveDTO.getBodyMessage ())
                    .lastMessage (lastMessage)
                    .subject (messageSaveDTO.getSubject ())
                    .person_to (personTo)
                    .donation (donation)
                    .build ();
            return messageRepository.save (messagePost);
        } catch (Exception e) {
            throw new ValidacaoException ("Message not found");
        }
//        return messageRepository.save (messagePost);
    }

    public Message update(Message message) {
        return messageRepository.save (message);
    }

    public void delete(Long id) {
        messageRepository.deleteById (id);
    }

    public Page<Message> getBySubject(String subject, Pageable pageable) {
        Message message = Message.builder ().subject (subject).build ();
        final ExampleMatcher matcher = ExampleMatcher.matching ()
                .withMatcher ("subject", ExampleMatcher.GenericPropertyMatcher::contains);
        final Example<Message> example = Example.of (message, matcher);
        return messageRepository.findAll (example, pageable);
    }
}
