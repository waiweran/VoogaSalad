package server;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class GetMessages extends Commands{

	public GetMessages(List<String> pParam, User pUser, boolean pEnd){
		super(pUser, pEnd);
	}
	
	@Override
	public void execute(PrintWriter out, Database db) throws SQLException {
		List<Email> emails = db.getEmails(getUser().getLogin());
		if(emails==null)
			out.println("ERROR" + Session.SEPARATOR + "No messages available.");
		else{
			out.println("AMOUNT" + Session.SEPARATOR + emails.size());
			for(int i=0; i<emails.size(); i++){
				out.println(emails.get(i).toString());
			}
			out.println("VALID");
		}
	}
}
