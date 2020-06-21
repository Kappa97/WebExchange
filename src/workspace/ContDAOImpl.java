package workspace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import login.MyConnectionProvider;

public class ContDAOImpl implements ContDAO {
	static Connection con;
	static PreparedStatement ps;

	@Override
	public int insertCont(Cont cont) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("insert into conturi (valuta, suma, coefc, coefv) values(?, ?, ?, ?)");
			ps.setString(1, cont.getValuta());
			ps.setDouble(2, cont.getSuma());
			ps.setDouble(3, cont.getCoefc());
			ps.setDouble(4, cont.getCoefv());

			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public Cont getCont(String valuta) {
		Cont cont = new Cont();
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from conturi where valuta = ?");
			ps.setString(1, valuta);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cont.setValuta(rs.getString(2));
				cont.setSuma(rs.getDouble(3));
				cont.setCoefc(rs.getDouble(4));
				cont.setCoefv(rs.getDouble(5));

			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return cont;
	}

	@Override
	public int updateCont(Cont cont) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("update conturi set valuta = ?, suma = ?, coefc = ?, coefv = ? where valuta = ?");
			ps.setString(1, cont.getValuta());
			ps.setDouble(2, cont.getSuma());
			ps.setDouble(3, cont.getCoefc());
			ps.setDouble(4, cont.getCoefv());
			ps.setString(5, cont.getValuta());

			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public int deleteCont(String valuta) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("delete from conturi where valuta = ?");
			ps.setString(1, valuta);

			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

}
