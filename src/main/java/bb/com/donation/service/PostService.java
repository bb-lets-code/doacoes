package bb.com.donation.service;

import bb.com.donation.dto.post.PostGenericDTO;
import bb.com.donation.dto.post.PostSaveDTO;
import bb.com.donation.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import bb.com.donation.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService extends GenericService<Post, Long, PostGenericDTO> {

    public List<Post> getAll();

    public void delete(Long aLong);

    public Post save(PostGenericDTO postGenericDTO);

    public Post getById(Long aLong);

    Page<Post> getByName(String filtro, Pageable pageable);

}
