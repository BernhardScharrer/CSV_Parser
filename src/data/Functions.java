package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Functions implements Serializable {
	
	private static final long serialVersionUID = 6844926560365398503L;
	
	private List<String> functions;
	
	public Functions() {
		functions = new ArrayList<>();
	}
	
	public void addFunction(String function) {
		functions.add(function.toUpperCase());
	}
	
	public List<String> getFunctions() {
		return functions;
	}
	
	public String print() {
		String result = "";
		for (String s : functions) result = result + s + ",";
		if (!(result.length()>1)) return "-";
		return result.substring(0, result.length()-1);
	}
	
}
