package lt.webgallery.domain.image.DTO;

import lombok.Getter;
import lombok.Setter;
import lt.webgallery.domain.image.model.Image;
import lt.webgallery.domain.image.model.ImageQuality;
import lt.webgallery.domain.tag.model.Tag;

import java.time.LocalDate;

@Getter
@Setter
public class ImageView {
    private Long id;
    private String file;
    private String imageName;
    private LocalDate uploadDate;
    private ImageQuality imageQuality;
    private String imageDescription;
    private Tag tag;

    public static ImageView build(Image image) {
        ImageView dto = new ImageView();
        dto.setId(image.getId());
        dto.setImageName(image.getImageName());
        dto.setUploadDate(image.getUploadDate());
        dto.setImageQuality(image.getImageQuality());
        dto.setImageDescription(image.getImageDescription());
//        dto.setTag(image.getTags());
        return dto;
    }
}
