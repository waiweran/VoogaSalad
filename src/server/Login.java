package server;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class Login extends Commands{

	private List<String> parameters;

	public Login(List<String> pParam, User pUser, boolean pEnd){
		super(pUser, pEnd);
		parameters = pParam;
	}
	 
	@Override
	public void execute(PrintWriter out, Database db) throws SQLException {
		setUser(db.validate(parameters.get(0), parameters.get(1)));
		if(getUser() == null)
			out.println("ERROR" + Session.SEPARATOR + "Incorrect login or password.");
		else
			out.println("VALID");
	}

}
