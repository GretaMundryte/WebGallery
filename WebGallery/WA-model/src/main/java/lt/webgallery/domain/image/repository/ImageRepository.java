package lt.webgallery.domain.image.repository;


import lt.webgallery.domain.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {
    List<Image> findByTags_TagOrImageNameLike(String tag, String imageNameLike);
}
