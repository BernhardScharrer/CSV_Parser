package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Serializable {
	
	private static final long serialVersionUID = 9047562244997537278L;
	private List<User> users;
	
	public Table() {
		this.users = new ArrayList<>();
	}
	
	public List<User> getUsers() {
		return users;
	}
	
}
