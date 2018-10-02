package server;

import java.io.PrintWriter;
import java.sql.SQLException;

public abstract class Commands {

	private User user;
	private boolean end;
	
	public Commands(User pUser, boolean pEnd) {
		user = pUser;
		end = pEnd;
	}
	public abstract void execute(PrintWriter out, Database db) throws SQLException;

	protected void setUser(User pUser) {
		user = pUser;
	}
	
	protected void setEnd(boolean pEnd) {
		end = pEnd;
	}
	
	public boolean getEnd() {
		return end;
	}
	
	public User getUser() {
		return user;
	}
}
