package lt.webgallery.domain.image.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lt.webgallery.domain.image.DTO.ImageDTO;
import lt.webgallery.domain.image.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public List<ImageDTO> findAll() {
        return imageService.getAllImages();
    }

    @GetMapping("/{id}")
    public ImageDTO findById(@PathVariable Long id) {
        return imageService.getImageById(id);
    }

    @GetMapping("/search/{query}")
    public List<ImageDTO> findByDescription(@PathVariable("query") String query) {
        return imageService.search(query);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestPart ImageDTO imageInfo, @RequestPart MultipartFile imageFIle) {
        imageService.createImage(imageInfo, imageFIle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        imageService.deleteImage(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void update(@PathVariable("id") Long id, @RequestPart ImageDTO imageInfo, @RequestPart(required = false) MultipartFile imageFile) {
        imageService.updateImage(id, imageInfo, imageFile);
    }
}
