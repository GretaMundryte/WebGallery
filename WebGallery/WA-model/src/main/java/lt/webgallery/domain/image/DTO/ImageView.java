package lt.webgallery.domain.image.DTO;

import lombok.Getter;
import lombok.Setter;
import lt.webgallery.domain.image.model.Image;
import lt.webgallery.domain.image.model.ImageQuality;
import lt.webgallery.domain.tag.model.Tag;
import org.springframework.web.servlet.View;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ImageView {
    private Long id;
    private String file;
    private String imageName;
    private LocalDate uploadDate;
    private ImageQuality imageQuality;
    private String imageDescription;
    private List<Tag> tags;

    public static ImageView build(Image image) {
        ImageView dto = new ImageView();
        dto.setId(image.getId());
        dto.setImageName(image.getImageName());
        dto.setUploadDate(image.getUploadDate());
        dto.setImageQuality(image.getImageQuality());
        dto.setImageDescription(image.getImageDescription());
//        dto.setTags(image.getTags().stream().map(tag -> tag.getTag()).collect(Collectors.toList()));
        dto.setTags(image.getTags());
        return dto;
    }
}
