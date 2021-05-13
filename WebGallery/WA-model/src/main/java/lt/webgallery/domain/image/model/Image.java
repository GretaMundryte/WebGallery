package lt.webgallery.domain.image.model;

import lombok.Getter;
import lombok.Setter;
import lt.webgallery.domain.tag.model.Tag;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_name")
    private String imageName;

    //pridet prie butinu lauku po column name skliaustuose: , nullable = false

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_quality")
    private ImageQuality imageQuality;

    @Column(name = "image_description", length = 250)
    private String imageDescription;

    @Column(name = "imageTag")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "image_tags", joinColumns = {@JoinColumn(name = "image_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;

    public void addTag(Tag tag) {
        tags.add(tag);
    }
}