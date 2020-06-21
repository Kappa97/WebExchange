package login;

public interface OperatorDAO {

	public int insertOperator(Operator operator);
	public Operator getOperator(String username, String password);
	public Operator getOperatorUsername(String username);
	public int updateOperatorWithPassword(Operator operator);
	public int updateOperatorWithoutPassword(Operator operator);
	public int deleteOperator(String username);
	public int getCodOperator(String username);
}
