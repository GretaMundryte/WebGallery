package lt.webgallery.domain.image.model;

import lombok.Getter;
import lombok.Setter;
import lt.webgallery.domain.tag.model.Tag;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Column(name = "image", nullable = false)
    private byte[] image;

    @Column(name = "image_name", nullable = false)
    private String imageName;

    @Column(name = "upload_date", nullable = false)
    private LocalDate uploadDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_quality", nullable = false)
    private ImageQuality imageQuality;

    @Column(name = "image_description", length = 250, nullable = false)
    private String imageDescription;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "image_tags", joinColumns = {@JoinColumn(name = "image_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags;

    public void addTag(Tag tag) {
        tags.add(tag);
    }
}