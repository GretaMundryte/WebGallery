package lt.webgallery.domain.image.DTO;

import lt.webgallery.domain.image.model.ImageQuality;

import java.time.LocalDate;

public class ImageDTO {

    private Long id;

    private Byte[] image;

    private LocalDate uploadDate;

    private ImageQuality imageQuality;

    private String imageDescription;
}
