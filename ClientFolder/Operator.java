
public class Operator {
	MyConnector connector = null;
	LoginFrame lf = null;
	MainFrame mf = null;
	
	public static void main(String[] args) {
		Operator operator = new Operator();
		operator.connector = new MyConnector(operator);
		operator.mf = new MainFrame(operator);
		operator.lf = new LoginFrame(operator);	
	}
}