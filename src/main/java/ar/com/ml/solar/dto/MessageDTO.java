package ar.com.ml.solar.dto;

public class MessageDTO {
	
	private String message;

	public MessageDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}