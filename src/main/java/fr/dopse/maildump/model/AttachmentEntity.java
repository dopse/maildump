package fr.dopse.maildump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by fr27a86n on 13/04/2017.
 */

@Embeddable
public class AttachmentEntity implements Serializable {

	@Column(name = "NAME")
	private String name;

	@Column(name = "DATA")
	private String data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
