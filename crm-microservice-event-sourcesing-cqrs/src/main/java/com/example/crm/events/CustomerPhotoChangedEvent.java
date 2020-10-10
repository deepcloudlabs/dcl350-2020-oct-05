package com.example.crm.events;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class CustomerPhotoChangedEvent extends CustomerBaseEvent {
	private String photo;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "CustomerPhotoChangedEvent []";
	}

}
