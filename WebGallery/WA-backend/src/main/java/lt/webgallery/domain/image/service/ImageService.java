package lt.webgallery.domain.image.service;

import lt.webgallery.domain.image.exceptions.ResourceNotFoundException;
import lt.webgallery.domain.image.model.Image;
import lt.webgallery.domain.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public List<Image> getAllByDescription(String description) {
        return imageRepository.findByImageDescription(description);
    }

    public void createImage(Image createdImage) {
        Image image = new Image();
        image.setId(createdImage.getId());
        image.setImage(createdImage.getImage());
        image.setImageQuality(createdImage.getImageQuality());
        image.setImageDescription(createdImage.getImageDescription());
    }

    public void deleteImage(@PathVariable("id") Long id) {
        imageRepository.deleteById(id);
    }

    public ResponseEntity<Image> getImageById(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image with id: " + id + " does not exist."));
        return ResponseEntity.ok(image);
    }

    public ResponseEntity<Image> updateImage(Long id, Image imageForUpdate) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image with id: " + id + " does not exist."));
        image.setId(imageForUpdate.getId());
        image.setImage(imageForUpdate.getImage());
        image.setImageQuality(imageForUpdate.getImageQuality());
        image.setImageDescription(imageForUpdate.getImageDescription());

        Image updateImage = imageRepository.save(image);
        return ResponseEntity.ok(image);
    }
}
