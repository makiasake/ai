package ai.dto;

import java.io.Serializable;
import java.util.List;

public class AiResponse implements Serializable {

	private static final long serialVersionUID = -2517612300475324691L;

	private String model;
	private String created_at;
	private String response;
	private Boolean done;
	private String done_reason;
	private List<Integer> context;
	private Float total_duration;
	private Float load_duration;
	private Float prompt_eval_count;
	private Float prompt_eval_duration;
	private Float eval_count;
	private Float eval_duration;
	private Message message;

	public AiResponse() {
	}

	public AiResponse(String model, String created_at, String response, Boolean done, String done_reason,
			List<Integer> context, Float total_duration, Float load_duration, Float prompt_eval_count,
			Float prompt_eval_duration, Float eval_count, Float eval_duration, Message message) {
		this.model = model;
		this.created_at = created_at;
		this.response = response;
		this.done = done;
		this.done_reason = done_reason;
		this.context = context;
		this.total_duration = total_duration;
		this.load_duration = load_duration;
		this.prompt_eval_count = prompt_eval_count;
		this.prompt_eval_duration = prompt_eval_duration;
		this.eval_count = eval_count;
		this.eval_duration = eval_duration;
		this.message = message;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public String getDone_reason() {
		return done_reason;
	}

	public void setDone_reason(String done_reason) {
		this.done_reason = done_reason;
	}

	public List<Integer> getContext() {
		return context;
	}

	public void setContext(List<Integer> context) {
		this.context = context;
	}

	public Float getTotal_duration() {
		return total_duration;
	}

	public void setTotal_duration(Float total_duration) {
		this.total_duration = total_duration;
	}

	public Float getLoad_duration() {
		return load_duration;
	}

	public void setLoad_duration(Float load_duration) {
		this.load_duration = load_duration;
	}

	public Float getPrompt_eval_count() {
		return prompt_eval_count;
	}

	public void setPrompt_eval_count(Float prompt_eval_count) {
		this.prompt_eval_count = prompt_eval_count;
	}

	public Float getPrompt_eval_duration() {
		return prompt_eval_duration;
	}

	public void setPrompt_eval_duration(Float prompt_eval_duration) {
		this.prompt_eval_duration = prompt_eval_duration;
	}

	public Float getEval_count() {
		return eval_count;
	}

	public void setEval_count(Float eval_count) {
		this.eval_count = eval_count;
	}

	public Float getEval_duration() {
		return eval_duration;
	}

	public void setEval_duration(Float eval_duration) {
		this.eval_duration = eval_duration;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
