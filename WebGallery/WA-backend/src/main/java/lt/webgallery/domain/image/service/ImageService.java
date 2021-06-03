package lt.webgallery.domain.image.service;

import lombok.RequiredArgsConstructor;
import lt.webgallery.domain.image.DTO.ImageDTO;
import lt.webgallery.domain.image.DTO.ImageView;
import lt.webgallery.domain.image.convertImage.ConvertImage;
import lt.webgallery.domain.image.exceptions.ResourceNotFoundException;
import lt.webgallery.domain.image.model.Image;
import lt.webgallery.domain.image.model.Image_;
import lt.webgallery.domain.image.repository.ImageRepository;
import lt.webgallery.domain.tag.model.Tag;
import lt.webgallery.domain.tag.repository.TagRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;

    public List<ImageView> getAllImages() {
        return imageRepository.findAll()
                .stream()
                .map(this::convertToImageView)
                .collect(Collectors.toList());
    }

    public ImageView getImageById(Long id) {
        return imageRepository
                .findById(id)
                .map(this::convertToImageView)
                .orElseThrow(() -> new ResourceNotFoundException("Image with id: " + id + " does not exist."));
    }

    public List<ImageView> search(String keyword) {
        Specification<Image> imageSpecification = (root, cq, cb) -> cb.or(cb.like(root.get(Image_.imageName), "%" + keyword + "%"),
                cb.like(root.get(Image_.imageDescription), "%" + keyword + "%"));
        return imageRepository.findAll(imageSpecification).stream()
                .map(this::convertToImageView)
                .collect(Collectors.toList());
    }

    public void createImage(ImageDTO imageInfo, MultipartFile imageFile) {
        Image image = new Image();
        try {
            image.setId(imageInfo.getId());
            image.setFile(imageFile.getBytes());
            image.setImageName(imageInfo.getImageName());
            image.setUploadDate(LocalDate.now());
            image.setImageQuality(imageInfo.getImageQuality());
            image.setImageDescription(imageInfo.getImageDescription());
//            if (imageInfo.getTags() != null) {
//                }


//            image.getTags().addAll(imageInfo.getTags());
//            if (imageInfo.getTags() != null) {
//                image.getTags().addAll(imageInfo.getTags());
//            }

            image.setTags(imageInfo.getTags());

//            image.setTags().addAll(imageInfo.getTags());


            imageRepository.save(image);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public void updateImage(Long id, ImageDTO imageInfo, MultipartFile multipartFile) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image with id: " + id + " does not exist."));

        if (multipartFile != null) {
            try {
                image.setFile(multipartFile.getBytes());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        image.setImageName(imageInfo.getImageName());
        image.setUploadDate(LocalDate.now());
        image.setImageQuality(imageInfo.getImageQuality());
        image.setImageDescription(imageInfo.getImageDescription());
//        if (imageInfo.getTags() != null) {
//            for (int i = 0; i < imageInfo.getTags().size(); i++) {
//                image.getTags().add(image.getTags().get(i));
//            }
//        }
//                image.getTags().addAll(imageInfo.getTags());

        image.setTags(imageInfo.getTags());

        imageRepository.save(image);
    }

    private ImageView convertToImageView(Image image) {
        ImageView imageDTO = ImageView.build(image);
        imageDTO.setFile(ConvertImage.encodeToString(image.getFile()));
        return imageDTO;
    }

    //tag service dalis

//    public void createTag(Tag providedTag) {
//        Tag tag = new Tag();
//        tag.setId(providedTag.getId());
//        tag.setTag(providedTag.getTag());
//    }
}
