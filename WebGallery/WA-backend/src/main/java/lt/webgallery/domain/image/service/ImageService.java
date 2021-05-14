package lt.webgallery.domain.image.service;

import lombok.RequiredArgsConstructor;
import lt.webgallery.domain.image.DTO.ImageDTO;
import lt.webgallery.domain.image.exceptions.ResourceNotFoundException;
import lt.webgallery.domain.image.model.Image;
import lt.webgallery.domain.image.model.Image_;
import lt.webgallery.domain.image.repository.ImageRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<ImageDTO> getAllImages() {
        return imageRepository.findAll()
                .stream()
                .map(ImageDTO::build)
                .collect(Collectors.toList());
    }

    public ImageDTO getImageById(Long id) {
        return imageRepository
                .findById(id)
                .map(ImageDTO::build)
                .orElseThrow(() -> new ResourceNotFoundException("Image with id: " + id + " does not exist."));
    }

    public List<ImageDTO> search(String keyword) {
        Specification<Image> imageSpecification = (root, cq, cb) -> cb.or(cb.like(root.get(Image_.imageName), "%" + keyword + "%"),
                cb.like(root.get(Image_.imageDescription), "%" + keyword + "%"));
        return imageRepository.findAll(imageSpecification).stream()
                .map(ImageDTO::build)
                .collect(Collectors.toList());
    }

    public void createImage(ImageDTO imageInfo, MultipartFile imageFile) {
        Image image = new Image();
        try {
            image.setId(imageInfo.getId());
            image.setImage(imageFile.getBytes());
            image.setImageName(imageFile.getOriginalFilename());
            image.setUploadDate(LocalDate.now());
            image.setImageQuality(imageInfo.getImageQuality());
            image.setImageDescription(imageInfo.getImageDescription());
            imageRepository.save(image);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public void updateImage(Long id, ImageDTO imageInfo, MultipartFile imageFile) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image with id: " + id + " does not exist."));

        if (imageFile != null) {
            try {
                image.setImage(imageFile.getBytes());
                image.setImageName(imageFile.getOriginalFilename());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        image.setUploadDate(LocalDate.now());
        image.setImageQuality(imageInfo.getImageQuality());
        image.setImageDescription(imageInfo.getImageDescription());
        imageRepository.save(image);
    }
}
