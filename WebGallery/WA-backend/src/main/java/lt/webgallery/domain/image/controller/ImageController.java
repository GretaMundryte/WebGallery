package lt.webgallery.domain.image.controller;

import lombok.RequiredArgsConstructor;
import lt.webgallery.domain.image.DTO.ImageDTO;
import lt.webgallery.domain.image.DTO.ImageView;
import lt.webgallery.domain.image.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public List<ImageView> findAll() {
        return imageService.getAllImages();
    }

    @GetMapping("/{id}")
    public ImageView findById(@PathVariable Long id) {
        return imageService.getImageById(id);
    }

    @GetMapping("/search/{query}")
    public List<ImageView> findByDescription(@PathVariable("query") String query) {
        return imageService.search(query);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestPart ImageDTO imageInfo, @RequestPart MultipartFile file) {
        imageService.createImage(imageInfo, file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        imageService.deleteImage(id);
    }

    @PutMapping(value = "/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void update(@PathVariable("id") Long id, @RequestPart ImageDTO imageInfo, @RequestPart(required = false) MultipartFile multipartFile) {
        imageService.updateImage(id, imageInfo, multipartFile);
    }
}
