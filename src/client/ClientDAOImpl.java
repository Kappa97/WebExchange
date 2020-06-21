package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import login.MyConnectionProvider;

public class ClientDAOImpl implements ClientDAO {

	static Connection con;
	static PreparedStatement ps;

	@Override
	public int insertClient(Client client) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("insert into Client (nume, prenume, cnp, adresa, telefon) values(?, ?, ?, ?, ?)");
			ps.setString(1, client.getNume());
			ps.setString(2, client.getPrenume());
			ps.setString(3, client.getCnp());
			ps.setString(4, client.getAdresa());
			ps.setString(5, client.getTelefon());
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public Client getClient(String cnp) {
		Client client = new Client();
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from client where cnp = ?");
			ps.setString(1, cnp);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				client.setNume(rs.getString(2));
				client.setPrenume(rs.getString(3));
				client.setCnp(rs.getString(4));
				client.setAdresa(rs.getString(5));
				client.setTelefon(rs.getString(6));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return client;
	}

	@Override
	public int getCodClient(String cnp) {
		int codClient = 0;

		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select codc from client where cnp = ?");
			ps.setString(1, cnp);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				codClient = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return codClient;
	}

}
