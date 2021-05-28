package lt.webgallery.domain.image.model;

import lombok.Getter;
import lombok.Setter;
import lt.webgallery.domain.tag.model.Tag;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "file")
    private byte[] file;
//    private String file;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_quality")
    private ImageQuality imageQuality;

    @Column(name = "image_description", length = 250)
    private String imageDescription;

//    skliausteliuose, po name: , nullable = false

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "image_tags", joinColumns = {@JoinColumn(name = "image_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;

    public void addTag(Tag tag) {
        tags.add(tag);
    }
}