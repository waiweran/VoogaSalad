package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Session extends Thread{
	// TODO Careful here!!!
	private static final String COMMAND_PACKAGE = "commands.DisplayCommands.";
	public static final String SEPARATOR = ";;;";
	private Queue<String> code = new LinkedList<String>();
	private PropertiesHolder classes = new PropertiesHolder();
	private Database database;
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;
	private User user = null;
	private boolean end = false;

	public Session(Socket pChannel, Database pDatabase)throws IOException{
		database = pDatabase;
		out = new PrintWriter(pChannel.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(pChannel.getInputStream()));
		socket = pChannel;
		// TODO add classes PropertiesHolder!!!
	}
	
	public Socket getSocket(){
		return socket;
	}

	public Database getDatabase(){
		return database;
	}
	
	public void run() {
		try{
			while(!end){
				readLine();
				String command = code.poll();
				executeCommand(createCommand(getParameters(command), command));
//				else if(comando.startsWith(CERRAR_SESION))
//					cerrarSesion();
//				else if(comando.startsWith(MARCAR_COMO_LEIDO)){
//					String[] para=comando.split(SEPARADOR_COMANDO);
//					String[] parametros=para[1].split(SEPARADOR_PARAMETROS);
//					marcarComoLeido(parametros[0], parametros[1]);
//				} else if(comando.startsWith(CONSULTAR_CORREOS))
//					consultarCorreos();
//				else if(comando.startsWith(ENVIAR_CORREO)){
//					String[] para=comando.split(SEPARADOR_COMANDO);
//					String[] parametros=para[1].split(SEPARADOR_PARAMETROS);
//					enviarCorreo(parametros[0], parametros[1], parametros[2]);
//				} else
//					out.println(ERROR+SEPARADOR_COMANDO+"El programa no soporta esta actividad.");
			}
		}
		catch(Exception e){
			end = true;
			close();
		}
	}

	private void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO what to do?
		}
	}

	private void readLine() throws IOException {
		code.addAll(Arrays.asList(in.readLine().split(SEPARATOR)));
	}

	private void executeCommand(Commands c) {
		try {
			c.execute(out, database);
			user = c.getUser();
			end = c.getEnd();
		} catch (SQLException e) {
			out.println("ERROR" + SEPARATOR + "Database error.");
		}
	}

	private Commands createCommand(List<String> parameters, String name) throws SessionException {
		try {
			return (Commands) getClazz(name).getDeclaredConstructor(List.class, User.class, boolean.class).newInstance(parameters, user, end);
		} catch (ClassNotFoundException e) {
			throw new InexistentCommandException(name);
		} catch (Exception e) {
			throw new IncompleteClassException("Constructor method", name);
		}
	}
	
	private List<String> getParameters(String name) throws SessionException {
		List<String> parameters = new LinkedList<>();
		while (parameters.size() != getAmount(name, "VARIABLES"))
			parameters.add(code.poll());
		return parameters;
	}
	
	private int getAmount(String name, String cons) throws SessionException {
		try {
			return (int) getClazz(name).getDeclaredField(cons).get(null);
		} catch (PropertiesInfoException | ClassNotFoundException e1) {
			throw new InexistentCommandException(name);
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException e) {
			throw new ReflectionException();
		} catch (NoSuchFieldException e) {
			throw new IncompleteClassException(cons + " constant field", name);
		}
	}
	
	private Class<?> getClazz(String name) throws SessionException, ClassNotFoundException {
		return Class.forName(getCommandLocation(getCommandClass(name)));
	}

	protected String getCommandClass(String info) throws PropertiesInfoException {
		return classes.getKey(info);
	}
	
	private String getCommandLocation(String name) {
		return COMMAND_PACKAGE + name;
	}
}