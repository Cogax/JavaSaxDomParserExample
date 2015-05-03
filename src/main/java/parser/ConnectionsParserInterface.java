/**
 * @author Andreas Gyr <andygyr@gmx.ch>
 */
package parser;

import java.io.File;
import java.util.ArrayList;

import model.Connection;

public interface ConnectionsParserInterface {
	public void setXMLFile(File xmlFile);

	public void parse() throws Exception;

	public ArrayList<Connection> getConnections();
}
