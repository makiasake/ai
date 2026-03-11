package ai.dto;

import java.io.Serializable;
import java.util.List;

public class AiRequest implements Serializable {

	private static final long serialVersionUID = 7883522951553838590L;

	// llama3.2
	private String model = "llama3.2";
	private String prompt;
	private Boolean stream = false;
	private String system;
	private List<Message> messages;

	public AiRequest(final String prompt, final Boolean stream, final List<Message> messages) {
		this.prompt = prompt;
		this.stream = stream;
		this.messages = messages;
	}

	public AiRequest(final String prompt, final Boolean stream) {
		this.prompt = prompt;
		this.stream = stream;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Boolean getStream() {
		return stream;
	}

	public void setStream(Boolean stream) {
		this.stream = stream;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
