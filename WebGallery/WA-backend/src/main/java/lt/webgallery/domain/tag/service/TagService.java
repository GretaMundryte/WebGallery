package lt.webgallery.domain.tag.service;


import lombok.RequiredArgsConstructor;
import lt.webgallery.domain.image.exceptions.ResourceNotFoundException;
import lt.webgallery.domain.tag.DTO.TagDTO;
import lt.webgallery.domain.tag.model.Tag;
import lt.webgallery.domain.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image with id: " + id + " does not exist."));
    }

    public void createTag(Tag providedTag) {
        Tag tag = new Tag();
        tag.setId(providedTag.getId());
        tag.setTag(providedTag.getTag());
    }

//    public void deleteTag(Long id) {
//        tagRepository.deleteById(id);
//    }
}
