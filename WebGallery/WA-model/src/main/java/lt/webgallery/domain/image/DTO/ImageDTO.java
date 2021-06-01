package lt.webgallery.domain.image.DTO;

import lombok.Getter;
import lombok.Setter;
import lt.webgallery.domain.image.model.ImageQuality;
import lt.webgallery.domain.tag.model.Tag;

import java.time.LocalDate;

@Getter
@Setter
public class ImageDTO {

    private Long id;
    private String imageName;
    private LocalDate uploadDate;
    private ImageQuality imageQuality;
    private String imageDescription;
    private Tag[] tags;
}
