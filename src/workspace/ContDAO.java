package workspace;

import java.awt.List;
import java.sql.SQLException;

public interface ContDAO {

	public int insertCont(Cont cont);
	public Cont getCont(String valuta);
	public int updateCont(Cont cont);
	public int deleteCont(String valuta); 
	
}
