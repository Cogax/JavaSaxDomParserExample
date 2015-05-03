package parser.dom;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.Connection;
import model.ConnectionCapacity;
import model.ConnectionDestination;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import parser.ConnectionsParserInterface;
import util.SimpleErrorHandler;

public class ConnectionsDOMParser implements ConnectionsParserInterface {
	private File xmlFile;
	private ArrayList<Connection> connections;

	public ConnectionsDOMParser() {
		connections = new ArrayList<Connection>();
	}

	public void setXMLFile(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	public void parse() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");

		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setErrorHandler(new SimpleErrorHandler());
		Document document = builder.parse(xmlFile);
		document.getDocumentElement().normalize();

		NodeList connectionNodes = document.getElementsByTagName("connection");
		for (int i = 0; i < connectionNodes.getLength(); i++) {
			Node connectionNode = connectionNodes.item(i);

			// check if node is an element
			if (connectionNode.getNodeType() == Node.ELEMENT_NODE) {
				Element connectionElement = (Element) connectionNode;

				// date
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = dateFormat.parse(connectionElement
						.getElementsByTagName("date").item(0).getTextContent());

				// set up connection object
				Connection connection = new Connection();
				connection.setDate(date);
				connection.setFrom(parseDestination(connectionElement, "from"));
				connection.setTo(parseDestination(connectionElement, "to"));
				connection
						.setTrain(connectionElement
								.getElementsByTagName("train").item(0)
								.getTextContent());
				connection.setCapacity(parseCapacity(connectionElement));
				connection.setNote(parseNote(connectionElement));
				connections.add(connection);
			}
		}
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	/**
	 * Creates an ConnectionDestionation object from given parent Element.
	 * 
	 * @param parentElement
	 *            Element of parent node
	 * @param destinationTagName
	 *            Tag name of the element
	 * @return An ConnectionDestination object
	 * @throws DOMException
	 * @throws ParseException
	 */
	private static ConnectionDestination parseDestination(
			Element parentElement, String destinationTagName)
			throws DOMException, ParseException {

		// get element by the given destinationTagName
		Node destinationNode = parentElement.getElementsByTagName(
				destinationTagName).item(0);

		ConnectionDestination destination = new ConnectionDestination();

		// check if node is an element
		if (destinationNode.getNodeType() == Node.ELEMENT_NODE) {
			Element destinationElement = (Element) destinationNode;

			// time
			DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			Date time = timeFormat.parse(destinationElement
					.getElementsByTagName("time").item(0).getTextContent());

			destination.setStation(destinationElement
					.getElementsByTagName("station").item(0).getTextContent());
			destination.setTime(time);
			destination.setTrack(Integer.parseInt(destinationElement
					.getElementsByTagName("track").item(0).getTextContent()));
		}
		return destination;
	}

	/**
	 * Creates an ConnectionDestionation object from given parent Element.
	 * 
	 * @param parentElement
	 *            Element of parent node
	 * @return An ConnectionCapacity object
	 */
	private static ConnectionCapacity parseCapacity(Element parentElement) {

		// get capacity element
		Node capacityNode = parentElement.getElementsByTagName("capacity")
				.item(0);

		ConnectionCapacity capacity = new ConnectionCapacity();

		// check if node is an element
		if (capacityNode.getNodeType() == Node.ELEMENT_NODE) {
			Element capacityElement = (Element) capacityNode;
			capacity.setFirstClass(Integer.parseInt(capacityElement
					.getElementsByTagName("firstClass").item(0)
					.getTextContent()));
			capacity.setSecondClass(Integer.parseInt(capacityElement
					.getElementsByTagName("secondClass").item(0)
					.getTextContent()));
		}

		return capacity;
	}

	/**
	 * Gives back the note String if available in the XML.
	 * 
	 * @param parentElement
	 *            Element of parent node
	 * @return Note String
	 */
	private static String parseNote(Element parentElement) {

		String note = new String();
		NodeList noteNodes = parentElement.getElementsByTagName("note");
		if (noteNodes.getLength() == 0) {
			return note;
		}

		return noteNodes.item(0).getTextContent();
	}
}
