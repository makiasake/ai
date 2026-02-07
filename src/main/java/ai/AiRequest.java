package ai;

import java.io.Serializable;

public class AiRequest implements Serializable {

	private static final long serialVersionUID = 7883522951553838590L;

	private String model = "llama3.2";
	private String prompt;
	private Boolean stream = false;
	private String system;

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

}
