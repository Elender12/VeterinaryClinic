package dao;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ConnectionProperties {
	private static final String FILE_NAME = "DBconection.xml";
	private static final String DATA_BASE = "Database";
	private static final String USER = "User";
	private static final String PASSWORD = "Password";
	private static final String DRIVER = "Driver";
	private static final String SERVER = "Server";
	private String user;
	private String password;
	private String driver;
	private String server;
	private String nameBD;

	/**
	 * Reads data for the connection
	 */
	public void readConnectionData() {

		try {
			File file = new File(FILE_NAME);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			Element root = doc.getDocumentElement();
			this.user = root.getElementsByTagName(USER).item(0).getTextContent();
			this.password = root.getElementsByTagName(PASSWORD).item(0).getTextContent();
			this.driver = root.getElementsByTagName(DRIVER).item(0).getTextContent();
			this.server = root.getElementsByTagName(SERVER).item(0).getTextContent();
			NamedNodeMap atributo = root.getElementsByTagName(DATA_BASE).item(0).getAttributes();
			this.nameBD = atributo.item(0).getTextContent();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getNameBD() {
		return nameBD;
	}

	public void setNameBD(String nameBD) {
		this.nameBD = nameBD;
	}

}
