package converter.logics.structures;

import java.util.ArrayList;
import java.util.List;

public class CheckPoint {
	
	//Attributes
	private String id;
	private List<Integer> path;
	
	//Constructor
	public CheckPoint () {
		path = new ArrayList<Integer>();
	}
	
	//Methods
	public void setId (String id) {
		this.id = id;
	}
	
	public String getId () {
		return id;
	}
	
	public void setPath (List<Integer> path) {
		this.path = path;
	}
	
	public List<Integer> getPath () {
		return path;
	}

}
