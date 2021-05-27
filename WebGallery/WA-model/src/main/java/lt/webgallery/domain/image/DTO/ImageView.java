package lt.webgallery.domain.image.DTO;

import lombok.Getter;
import lombok.Setter;
import lt.webgallery.domain.image.model.Image;
import lt.webgallery.domain.image.model.ImageQuality;

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

    public static ImageView build(Image image) {
        ImageView dto = new ImageView();
        dto.setId(image.getId());
        dto.setImageName(image.getImageName());
        dto.setUploadDate(image.getUploadDate());
        dto.setImageQuality(image.getImageQuality());
        dto.setImageDescription(image.getImageDescription());
        return dto;
    }
}
