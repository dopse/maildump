package fr.dopse.maildump.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fr27a86n on 13/04/2017.
 */

@Entity
@Table(name = "ATTACHMENT_CONTENT")
public class AttachmentContentEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

	@Lob
	private byte[] data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
