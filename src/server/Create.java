package server;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


public class Create extends Commands {
	
	private List<String> parameters;

	public Create(List<String> pParam, User pUser, boolean pEnd){
		super(pUser, pEnd);
		parameters = pParam;
	}
	 
	@Override
	public void execute(PrintWriter out, Database db) throws SQLException {
		if(!db.create(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3)))
			out.println("ERROR" + Session.SEPARATOR + "Login already exists.");
		else{
			setUser(new User(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), 0, true));
			out.println("VALID");
		}
	}

}
