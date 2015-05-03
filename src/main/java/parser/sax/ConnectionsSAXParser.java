package parser.sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import model.Connection;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
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
		SAXParser parser = getValidParser(xmlFile, factory);

		// parse connections
		ConnectionsSAXHandler connectionsHandler = new ConnectionsSAXHandler();
		parser.parse(xmlFile, connectionsHandler);
		connections = connectionsHandler.getConnections();
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	/**
	 * ------------------------------------------------------------------------
	 * The following methods are public and static for reusability reasons. I
	 * tried to implement the method dependency injection pattern.
	 * ------------------------------------------------------------------------
	 */

	/**
	 * If the xmlFile is valid it creates a Document, otherwise it will throw
	 * Exceptions.
	 * 
	 * @param xmlFile
	 *            XML Source File
	 * @param factory
	 *            Factory for SAX Parser
	 * @return A validated Parser or Exceptions
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static SAXParser getValidParser(File xmlFile,
			SAXParserFactory factory) throws ParserConfigurationException,
			SAXException, IOException {
		factory.setValidating(true);
		factory.setNamespaceAware(true);

		SAXParser parser = factory.newSAXParser();
		parser.setProperty(
				"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");

		XMLReader reader = parser.getXMLReader();
		reader.setErrorHandler(new SimpleErrorHandler());
		reader.parse(new InputSource(xmlFile.getPath()));

		return parser;
	}

}
