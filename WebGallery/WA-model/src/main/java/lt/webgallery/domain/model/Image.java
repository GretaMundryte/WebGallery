package lt.webgallery.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "image", nullable = false)
    private Byte[] image;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    //    @Enumerated(EnumType.STRING)
    @Column(name = "image_quality", nullable = false)
    private String imageQuality;

    @Column(name = "image_description", length = 250)
    private String imageDescription;

}