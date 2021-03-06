package lt.webgallery.domain.image.service;

import lombok.RequiredArgsConstructor;
import lt.webgallery.domain.image.DTO.ImageDTO;
import lt.webgallery.domain.image.DTO.ImageView;
import lt.webgallery.domain.image.convertImage.ConvertImage;
import lt.webgallery.domain.image.exceptions.ResourceNotFoundException;
import lt.webgallery.domain.image.model.Image;
import lt.webgallery.domain.image.model.Image_;
import lt.webgallery.domain.image.repository.ImageRepository;
import lt.webgallery.domain.tag.model.Tag_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

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
        Specification<Image> imageSpecification = (root, cq, cb) -> {
            final Subquery<Long> subquery = cq.subquery(Long.class);
            final Root<Image> imageRoot = subquery.from(Image.class);
            subquery.select(imageRoot.get(Image_.id));
            subquery.where(cb.like(imageRoot.join(Image_.tags).get(Tag_.tag), "%" + keyword + "%"));
            return cb.or(
                    cb.in(root.get(Image_.id)).value(subquery),
                    cb.like(root.get(Image_.imageName), "%" + keyword + "%"));
        };
        return imageRepository
                .findAll(imageSpecification)
                .stream()
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
            image.setTags(imageInfo.getTags());
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
        image.setTags(imageInfo.getTags());
        imageRepository.save(image);
    }

    private ImageView convertToImageView(Image image) {
        ImageView imageDTO = ImageView.build(image);
        imageDTO.setFile(ConvertImage.encodeToString(image.getFile()));
        return imageDTO;
    }
}
