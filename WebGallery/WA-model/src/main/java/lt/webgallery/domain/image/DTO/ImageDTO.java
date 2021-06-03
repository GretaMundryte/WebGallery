package lt.webgallery.domain.image.DTO;

import lombok.Getter;
import lombok.Setter;
import lt.webgallery.domain.image.model.ImageQuality;
import lt.webgallery.domain.tag.model.Tag;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ImageDTO {

    private Long id;
    private String imageName;
    private LocalDate uploadDate;
    private ImageQuality imageQuality;
    private String imageDescription;
    private List<Tag> tags;
}
