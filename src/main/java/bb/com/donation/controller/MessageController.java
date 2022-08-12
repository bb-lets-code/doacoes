package bb.com.donation.controller;

import bb.com.donation.dto.message.MessageSaveDTO;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Message;
import bb.com.donation.service.impl.MessageServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    final MessageServiceImpl messageService;


    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Message>> getAll() {
        try {
            return ResponseEntity.ok (messageService.getAll ());
        }catch (Exception e){
            throw new ValidacaoException (e.getMessage ());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable @Valid Long id) {
        try {
            return ResponseEntity.ok (messageService.getById (id));
        }catch (Exception e){
            throw new ValidacaoException (e.getMessage ());

        }
    }

    @GetMapping("/filter/{subject}")
    public ResponseEntity<Page<Message>> getBySubject(@PathVariable("subject") @Valid String subject, Pageable pageable) {
        try {
            Page<Message> message = messageService.getBySubject (subject, pageable);
            return ResponseEntity.ok(message);
        } catch (Exception e){
            throw new ValidacaoException (e.getMessage ());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody @Valid MessageSaveDTO message) {
        try {
            return ResponseEntity.ok (messageService.save (message));
        }catch (ValidacaoException e){
            throw new ValidacaoException (e.getMessage ());
        }
    }
}
