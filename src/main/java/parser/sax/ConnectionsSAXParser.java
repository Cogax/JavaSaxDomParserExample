package parser.sax;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import model.Connection;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import parser.ConnectionsParserInterface;
import util.SimpleErrorHandler;

public class ConnectionsSAXParser implements ConnectionsParserInterface {
	private File xmlFile;
	private ArrayList<Connection> connections;

	public ConnectionsSAXParser() {
		this.connections = new ArrayList<Connection>();
	}

	public void setXMLFile(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	public void parse() throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);

		SAXParser parser = factory.newSAXParser();
		parser.setProperty(
				"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");

		// parse for validations
		XMLReader reader = parser.getXMLReader();
		reader.setErrorHandler(new SimpleErrorHandler());
		reader.parse(new InputSource(xmlFile.getPath()));

		// parse connections
		ConnectionsSAXHandler connectionsHandler = new ConnectionsSAXHandler();
		parser.parse(xmlFile, connectionsHandler);
		connections = connectionsHandler.getConnections();
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

}
