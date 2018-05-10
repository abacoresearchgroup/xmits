package reader.list;

public class Node {

	//Attributes
	private Content content;
	private int level;
	
	//Constructor
	public Node () {
		//Empty
	}
	
	//Methods
	public void setContent (Content content) {
		this.content = content;
	}
	
	public Content getContent () {
		return this.content;
	}
	
	public Node getClone () {
		Node clone = new Node();
		clone.setContent(this.content);
		return clone;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
		
}
