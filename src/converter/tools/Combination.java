package converter.tools;

import java.util.ArrayList;
import java.util.List;

public class Combination {

	public List<List<Integer>> generate (Integer n) {
		
		List<List<Integer>> comb = new ArrayList<List<Integer>>();
		
		if (n > 0) {
			
			List<Integer> first = new ArrayList<Integer>();
			first.add(1);
			comb.add(first);
			
			while (comb.get(comb.size() -1).get(0) != n) {
				
				List<Integer> lastComb = new ArrayList<Integer>();
				for (int i = 0; i < comb.get(comb.size() - 1).size(); i++) {
					lastComb.add(comb.get(comb.size() - 1).get(i));
				}
				
				Integer lastNum = lastComb.get(lastComb.size() - 1);

				if (lastNum < n) {
					lastComb.add(lastNum + 1);
					comb.add(lastComb);
				}else{
					List<Integer> nextComb = new ArrayList<Integer>();
					for (int i = 0; i < lastComb.size() - 1; i++) {
						nextComb.add(lastComb.get(i));
					}
					Integer nextLast = nextComb.get(nextComb.size() - 1) + 1;
					nextComb = nextComb.subList(0, nextComb.size() - 1);
					nextComb.add(nextLast);
					comb.add(nextComb);
				}
			}
			return comb;
		}else{
			return null;
		}
	}
	
}
