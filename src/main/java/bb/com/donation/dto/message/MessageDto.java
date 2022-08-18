package bb.com.donation.dto.message;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class MessageDto implements Serializable {
    private Long id;
    private String subject;
    private String bodyMessage;
    private Long personTo;
    private Long personBy;
    private Long lastMessage;

    private Long donationId;


    public MessageDto(){}
}
