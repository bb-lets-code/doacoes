package bb.com.donation.dto.community;

import bb.com.donation.model.Community;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class CommunitySaveDTO implements CommunityGenericDTO {
    private String name;

    @Override
    public Community toCommunity() {
        return Community.builder()
                .id(null)
                .name(name)
                .build();
    }
}
