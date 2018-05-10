package reader.list;

public class Body extends Content{

	//Attribute
	private String content;
	
	//Constructor
	public Body () {
		
	}
	
	//Methods
	@Override
	public void setContent (String content) {
		this.content = content;
	}
	
	@Override
	public String getContent () {
		return this.content;
	}
	
}
