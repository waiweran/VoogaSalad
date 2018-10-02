package server;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class Database{
	
	private Connection connection;

	private Properties config;
	
	public Database(Properties prop) {
		config = prop;
		File data = new File(config.getProperty("admin.db.path"));
		System.setProperty("derby.system.home", data.getAbsolutePath());
	}
	
	public void connect()throws SQLException, Exception{
		String driver=config.getProperty("admin.db.driver");
		Class.forName(driver).newInstance();
		String url=config.getProperty("admin.db.url");
		connection=DriverManager.getConnection(url);
	}
	
	public void disconnect()throws SQLException{ 
		connection.close();
		String down=config.getProperty("admin.db.shutdown");
		DriverManager.getConnection(down);
	}

	public void createTables()throws SQLException{
		createTable("users", " (login varchar(32), names varchar(50), last varchar(50), pass varchar(32), connected varchar(1), PRIMARY KEY (login))");
		createTable("emails", " (writer varchar(32), reader varchar(32), date varchar(20), matter varchar(140), message varchar(512), PRIMARY KEY (destination, date, matter))");
	}

	private void createTable(String name, String info) throws SQLException {
		Statement s = connection.createStatement();
		boolean createTables = false;
		try{
			s.executeQuery("SELECT * FROM " + name +" WHERE 1=2");
		}catch(SQLException se){
			createTables=true;
		}
		if(createTables)
			s.execute("CREATE TABLE " + name + info);
		s.close();
	}

	public boolean create(String login, String name, String last, String pass)throws SQLException{
		Statement st = connection.createStatement();
		ResultSet result = st.executeQuery("SELECT login FROM users WHERE login ='" + login + "'");
		boolean ans = false;
		if(!result.next()){
			st.execute("INSERT INTO users (login, names, last, pass, connected) VALUES ('" + login + "', '" + name + "','" + last + "', '" + pass + "', '0')");
			ans = true;
		}
		result.close();
		st.close();
		return ans;
	}

	public User validate(String login, String pass)throws SQLException{
		User user = null;
		String sql = "SELECT * FROM users WHERE login ='" + login + "' AND pass ='" + pass + "'";
		Statement st = connection.createStatement();
		ResultSet result = st.executeQuery(sql);
		if(result.next()){
			user = getUser(st, result);
			st.executeUpdate("UPDATE users SET connected ='1' WHERE login ='" + login + "'");
		}
		result.close();
		st.close();
		return user;
	}

	private User getUser(Statement st, ResultSet result) throws SQLException {
		User user = null;
		ResultSet result2 = st.executeQuery("SELECT read FROM emails WHERE reader ='" + result.getString(1) + "'");
		int i=0;
		while(result2.next())
			i++;
		user = new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4), i, "1");
		result2.close();
		return user;
	}

	public Collection<User> getUsers()throws SQLException{
		Collection<User> list = new ArrayList<User>();
		Statement st = connection.createStatement();
		ResultSet result = st.executeQuery("SELECT * FROM users");
		while(result.next())
			list.add(getUser(st, result));
		result.close();
		st.close();
		return list;
	}

	public boolean cerrarSesion(String login) {
		try{
			return connection.createStatement().executeUpdate("UPDATE users SET connected ='0' WHERE login ='" + login + "'") == 1;
		} catch(Exception e){
			return false;
		}
	}
	
	public List<Email> getEmails(String login) {
		try{
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("SELECT writer, date, matter, message FROM emails WHERE reader ='" + login + "'");
			ArrayList<Email> emails = new ArrayList<Email>();
			while(result.next())
				emails.add(new Email(result.getString(1), result.getDate(2), result.getString(3), result.getString(4)));
			result.close();
			st.close();
			return emails;
		} catch(Exception e){
			return null;
		}
	}

	public boolean sendEmail(String login, String readers, String matter, String message){
		try{
			Statement st = connection.createStatement();
			String[] destin = readers.split(",");
			Date date = new Date(Calendar.getInstance().getTimeInMillis());
			for(int i=0; i<destin.length; i++)
				st.execute("INSERT INTO emails (writer, reader, date, matter, message) VALUES ('" + login + "', '" + destin[i] + "','" + date + "', '" + matter + "', '" + message + "', '0')");
			return true;
		} catch(Exception e){
			return false;
		}
	}
}
