package login;

public interface MyProvider {

	String host = "localhost";
	String db = "mydb";
	String user = "root";
	String password = "";

	String connURL = "jdbc:mysql://" + host + "/" + db;

}
