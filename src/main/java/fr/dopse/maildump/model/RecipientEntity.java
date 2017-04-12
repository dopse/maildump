package fr.dopse.maildump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by fr27a86n on 11/04/2017.
 */

@Embeddable
public class RecipientEntity implements Serializable {

	@Column(name = "EMAIL")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
