package lt.webgallery.domain.image.DTO;

import lombok.Getter;
import lombok.Setter;
import lt.webgallery.domain.image.model.Image;
import lt.webgallery.domain.image.model.ImageQuality;

import java.time.LocalDate;

@Getter
@Setter
public class ImageDTO {

    private Long id;

    private byte[] image;

    private String imageName;

    private LocalDate uploadDate;

    private ImageQuality imageQuality;

    private String imageDescription;

    public static ImageDTO build(Image image) {
        ImageDTO dto = new ImageDTO();
        dto.setId(image.getId());
        dto.setImage(image.getImage());
        dto.setImageName(image.getImageName());
        dto.setImageQuality(image.getImageQuality());
        dto.setImageDescription(image.getImageDescription());
        return dto;
    }
}
