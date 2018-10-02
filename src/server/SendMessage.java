package server;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class SendMessage extends Commands{
	private List<String> parameters;

	public SendMessage(List<String> pParam, User pUser, boolean pEnd){
		super(pUser, pEnd);
		parameters = pParam;
	}
	
	@Override
	public void execute(PrintWriter out, Database db) throws SQLException {
		if(!db.sendEmail(getUser().getLogin(), parameters.get(0), parameters.get(1), parameters.get(2)))
			out.println("ERROR" + Session.SEPARATOR + "Email couldn't be sent. Check conection please.");
		else
			out.println("VALID");
	}
}
