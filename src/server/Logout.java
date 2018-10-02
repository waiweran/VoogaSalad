package server;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class Logout extends Commands{

	public Logout(List<String> pParam, User pUser, boolean pEnd){
		super(pUser, pEnd);
	}
	
	@Override
	public void execute(PrintWriter out, Database db) throws SQLException {
		setEnd(db.cerrarSesion(getUser().getLogin()));
		if(!getEnd())
			out.println("ERROR" + Session.SEPARATOR + "Couldn't log out.");
		else
			out.println("VALID");
	}

}
