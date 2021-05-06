package lt.webgallery.domain.repository;

import lt.webgallery.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByImageDescription(Long id);
}
