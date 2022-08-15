package bb.com.donation.service;

import bb.com.donation.MessageDto;
import bb.com.donation.model.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService extends GenericService<Message, Long, MessageDto> {


}
