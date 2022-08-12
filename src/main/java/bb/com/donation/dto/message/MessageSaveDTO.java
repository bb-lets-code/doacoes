package bb.com.donation.dto.message;

import bb.com.donation.model.Donation;
import bb.com.donation.model.Message;
import bb.com.donation.model.Person;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageSaveDTO implements MessageGenericDTO {
    private String subject;
    private String bodyMessage;
    private Long personToId;
    private Long personById;
    private Long donationId;
    private Long lastMessageId;


    @Override
    public Message toMessage() {
        return Message.builder()
                .subject(subject)
                .bodyMessage(bodyMessage)
                .person_to (Person.builder().id (personToId).build())
                .person_by (Person.builder().id (personById).build())
                .donation(Donation.builder().id (donationId).build())
                .lastMessage(Message.builder().id (lastMessageId).build())
                .build();
    }

}
