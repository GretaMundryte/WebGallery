package lt.webgallery.domain.image.controller;

import lombok.RequiredArgsConstructor;
import lt.webgallery.domain.image.model.Image;
import lt.webgallery.domain.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public List<Image> findAll() {
        return imageService.getAllImages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> findById(@PathVariable Long id) {
        return imageService.getImageById(id);
    }

    @GetMapping("/description/{imageDescription}")
    public List<Image> findByDescription(@PathVariable String imageDescription) {
        return imageService.getAllByDescription(imageDescription);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Image createdImage) {
        imageService.createImage(createdImage);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        imageService.deleteImage(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@RequestBody Image image, @PathVariable Long id) {
        return imageService.updateImage(id, image);
    }
}
