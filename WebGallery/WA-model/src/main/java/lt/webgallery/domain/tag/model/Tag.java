package lt.webgallery.domain.tag.model;

import lombok.Getter;
import lombok.Setter;
import lt.webgallery.domain.image.model.Image;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "tag")
    private String tag;
//
//    @Column
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
//    List<Image> images;
}
