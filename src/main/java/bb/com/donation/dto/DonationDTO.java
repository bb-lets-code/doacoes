package bb.com.donation.dto;

import bb.com.donation.model.Donation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DonationDTO {

    private String name;

    public Donation toDonation() {
        Donation donation = new Donation ();
        donation.setId (null);
        donation.setName(name);
        donation.setRequerimentId (null);
        return donation;
    }
}
