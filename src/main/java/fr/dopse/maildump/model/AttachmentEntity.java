package fr.dopse.maildump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 * Created by fr27a86n on 13/04/2017.
 */

@Embeddable
public class AttachmentEntity implements Serializable {

	@Column(name = "NAME")
	private String name;

	@Lob
	@Column(name = "DATA")
	private byte[] data;

	@Column(name = "CONTENT_TYPE")
    private String contentType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
