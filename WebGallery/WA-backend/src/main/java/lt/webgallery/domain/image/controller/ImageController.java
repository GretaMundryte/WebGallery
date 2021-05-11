package lt.webgallery.domain.image.controller;

import lombok.RequiredArgsConstructor;
import lt.webgallery.domain.image.DTO.ImageDTO;
import lt.webgallery.domain.image.service.ImageService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ImageDTO createdImage, MultipartFile multipartFile) {
        imageService.createImage(createdImage, multipartFile);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        imageService.deleteImage(id);
    }

    @PutMapping("/{id}")
    public void updateImage(@RequestParam(value = "data", required = false) @RequestBody ImageDTO image, @PathVariable Long id) {
        imageService.updateImage(id, image);
    }
}
