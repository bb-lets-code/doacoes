package bb.com.donation.controller;

import bb.com.donation.dto.message.MessageDto;
import bb.com.donation.dto.message.MessageSaveDTO;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Message;
import bb.com.donation.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/message")
@Tag (name = "Messages", description = "Message")
public class MessageController {

    private final MessageService messageService;


    private final ModelMapper modelMapper;


    public MessageController(MessageService messageService, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    @Operation(summary = "List All Messages", tags = {"Messages"})
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @PostMapping()
    @Operation(summary = "Save Message", tags = {"Messages"})
    public ResponseEntity<MessageDto> save(@RequestBody @Valid MessageSaveDTO message) {

        try {
            MessageDto messageToSaveDTO = modelMapper.map(message, MessageDto.class);
            Message messageSaved = messageService.save (messageToSaveDTO);
            MessageDto messageDto = MessageDto.builder()
                    .id (messageSaved.getId ())
                    .subject (messageSaved.getSubject ())
                    .bodyMessage (messageSaved.getBodyMessage ())
                    .personTo (messageSaved.getPersonTo ().getId ())
                    .personBy (messageSaved.getPersonBy ().getId ())
                    .lastMessage (messageSaved.getLastMessage ())
                    .build ();
            return ResponseEntity.ok (messageDto);
        } catch (ValidacaoException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Message by ID", tags = {"Messages"})
    public ResponseEntity<MessageDto> getById(@PathVariable @Valid Long id) {
        Message message = messageService.getById (id);
        MessageDto messageDto = MessageDto.builder ()
                .id (message.getId ())
                .subject (message.getSubject ())
                .bodyMessage (message.getBodyMessage ())
//                .donationId (message.get)
                .personTo (message.getPersonTo ().getId ())
                .personBy (message.getPersonBy ().getId ())
                .lastMessage (message.getLastMessage ())
                .build ();
        return ResponseEntity.ok (messageDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Message", tags = {"Messages"})
    public ResponseEntity<String> delete(@PathVariable @Valid Long id) {
        messageService.delete(id);
        return ResponseEntity.ok("Message deleted");
    }






}
