package fr.dopse.maildump.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fr27a86n on 13/04/2017.
 */

@Entity
@Table(name = "ATTACHMENT")
public class AttachmentEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String contentType;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AttachmentContentEntity attachmentContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public AttachmentContentEntity getAttachmentContent() {
        return attachmentContent;
    }

    public void setAttachmentContent(AttachmentContentEntity attachmentContent) {
        this.attachmentContent = attachmentContent;
    }
}
