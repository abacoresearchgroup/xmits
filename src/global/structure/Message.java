package global.structure;

public class Message {

	//Attributes
	private String content;
	private String contentId;
	
	//Constructor
	public Message () {
		contentId = "";
	}
	
	//Methods
	public void setContent (String content) {
		this.content = content;
	}
	
	public String getContent () {
		return this.content;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public Message getClone() {
		Message clone = new Message();
		clone.setContent(this.getContent());
		clone.setContentId(this.getContentId());
		return clone;
	}
	
}
