package client;

public interface ClientDAO {
	public int insertClient(Client client);
	public Client getClient(String cnp);
	public int getCodClient(String cnp);

}
