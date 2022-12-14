package bb.com.donation.service;

import bb.com.donation.dto.donation.DonationSaveDTO;
import bb.com.donation.model.Donation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public interface DonationService extends GenericService<Donation, Long, DonationSaveDTO> {


    Donation save(DonationSaveDTO donation);

    Donation getById(Long id);

    List<Donation> getAll();

    Page<Donation> getByName(String name, Pageable pageable);
}
