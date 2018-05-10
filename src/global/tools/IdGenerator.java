package global.tools;

import java.util.ArrayList;
import java.util.List;

public class IdGenerator {

	//Attributes
	private static IdGenerator uniqueInstance;
	private Integer integerId;
	private Integer stringId;
	private List<String> alphabet;
	
	//Constructor
	private IdGenerator () {
		integerId = 0;
		stringId = 0;
		alphabet = new ArrayList<String>();
		for (String letter : "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",")) {
			alphabet.add(letter);
		}
	}
	
//--Static Methods----------------------------------------------------------------------------------------------------------------------------------------//
	public static IdGenerator getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new IdGenerator();
		}
		return uniqueInstance;
	}

//--Public Methods----------------------------------------------------------------------------------------------------------------------------------------//
	public Integer getIntegerId () {
		return ++ integerId;
	}
	
	public String getStringId () {
		return generateString(stringId ++);
	}
	
	public void reset () {
		integerId = 0;
		stringId = 0;
	}

//--Private Methods----------------------------------------------------------------------------------------------------------------------------------------//
	private String generateString (Integer n) {
		if (n < alphabet.size()) {
			return alphabet.get(n);
		}else{
			return generateString(n / alphabet.size() - 1) + generateString(n % alphabet.size());
		}
	}
	
}
