package parser;

import java.io.File;
import java.util.ArrayList;

import model.Connection;

public interface ParserInterface {
	public void setXMLFile(File xmlFile);

	public void parse() throws Exception;

	public ArrayList<Connection> getConnections();
}
