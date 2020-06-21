package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.org.apache.bcel.internal.generic.Select;

public class OperatorDAOImpl implements OperatorDAO {

	static Connection con;
	static PreparedStatement ps;

	@Override
	public int insertOperator(Operator operator) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement(
					"insert into Operator (nume, prenume, cnp, username, parola, adresa, telefon, admin) values(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, operator.getNume());
			ps.setString(2, operator.getPrenume());
			ps.setString(3, operator.getCnp());
			ps.setString(4, operator.getUsername());
			ps.setString(5, operator.getPassword());
			ps.setString(6, operator.getAdresa());
			ps.setString(7, operator.getTelefon());
			ps.setString(8, operator.getAdmin());

			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public Operator getOperator(String username, String password) {
		Operator operator = new Operator();
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from operator where username = ? and parola = ?");
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				operator.setNume(rs.getString(2));
				operator.setPrenume(rs.getString(3));
				operator.setCnp(rs.getString(4));
				operator.setUsername(rs.getString(5));
				operator.setPassword(rs.getString(6));
				operator.setAdresa(rs.getString(7));
				operator.setTelefon(rs.getString(8));
				operator.setAdmin(rs.getString(9));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return operator;
	}

	@Override
	public Operator getOperatorUsername(String username) {
		Operator operator = new Operator();
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from operator where username = ?");
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				operator.setNume(rs.getString(2));
				operator.setPrenume(rs.getString(3));
				operator.setCnp(rs.getString(4));
				operator.setUsername(rs.getString(5));
				operator.setPassword(rs.getString(6));
				operator.setAdresa(rs.getString(7));
				operator.setTelefon(rs.getString(8));
				operator.setAdmin(rs.getString(9));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return operator;
	}

	@Override
	public int updateOperatorWithPassword(Operator operator) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement(
					"update Operator set nume = ?, prenume = ?, cnp = ?, username = ?, parola = ?, adresa = ?, telefon = ?, admin = ? where username = ?");
			ps.setString(1, operator.getNume());
			ps.setString(2, operator.getPrenume());
			ps.setString(3, operator.getCnp());
			ps.setString(4, operator.getUsername());
			ps.setString(5, operator.getPassword());
			ps.setString(6, operator.getAdresa());
			ps.setString(7, operator.getTelefon());
			ps.setString(8, operator.getAdmin());
			ps.setString(9, operator.getUsername());
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public int updateOperatorWithoutPassword(Operator operator) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement(
					"update Operator set nume = ?, prenume = ?, cnp = ?, username = ?, adresa = ?, telefon = ?, admin = ? where username = ?");
			ps.setString(1, operator.getNume());
			ps.setString(2, operator.getPrenume());
			ps.setString(3, operator.getCnp());
			ps.setString(4, operator.getUsername());
			ps.setString(5, operator.getAdresa());
			ps.setString(6, operator.getTelefon());
			ps.setString(7, operator.getAdmin());
			ps.setString(8, operator.getUsername());
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public int deleteOperator(String username) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement(
					"delete from operator where username = ?");
			ps.setString(1, username);

			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public int getCodOperator(String username) {
		int codOperator = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps= con.prepareStatement("select codo from operator where username = ?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				codOperator = Integer.parseInt(rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return codOperator;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
