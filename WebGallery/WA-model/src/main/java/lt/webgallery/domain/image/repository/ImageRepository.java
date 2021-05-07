package lt.webgallery.domain.image.repository;


import lt.webgallery.domain.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByImageDescription(Long id);

    List<Image> findByImageDescription(String description);
}
