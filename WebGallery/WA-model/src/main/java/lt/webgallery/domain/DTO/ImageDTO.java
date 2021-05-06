package lt.webgallery.domain.DTO;

import java.time.LocalDate;

public class ImageDTO {

    private Long id;

    private Byte[] image;

    private LocalDate uploadDate;

    private String imageQuality;

    private String imageDescription;
}
