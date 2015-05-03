package parser.sax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Connection;
import model.ConnectionCapacity;
import model.ConnectionDestination;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConnectionsSAXHandler extends DefaultHandler {

	private ArrayList<Connection> connections;
	private Connection connection;
	private ConnectionDestination destination;
	private ConnectionCapacity capacity;
	private String reading;

	public ConnectionsSAXHandler() {
		super();
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("connections")) {
			connections = new ArrayList<Connection>();
		} else if (qName.equals("connection")) {
			connection = new Connection();
		} else if (qName.equals("from")) {
			destination = new ConnectionDestination();
		} else if (qName.equals("to")) {
			destination = new ConnectionDestination();
		} else if (qName.equals("capacity")) {
			capacity = new ConnectionCapacity();
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("connection")) {
			connection.setCapacity(capacity);
			connections.add(connection);
			connection = null;
		}
		if (qName.equals("date")) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = dateFormat.parse(reading);
				connection.setDate(date);
			} catch (ParseException e) {
				throw new SAXException("wrong date format");
			}
		}
		if (qName.equals("station")) {
			destination.setStation(reading);
		}
		if (qName.equals("time")) {
			DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			try {
				Date time = timeFormat.parse(reading);
				destination.setTime(time);
			} catch (ParseException e) {
				throw new SAXException("wrong time format");
			}
		}
		if (qName.equals("track")) {
			destination.setTrack(Integer.parseInt(reading));
		}
		if (qName.equals("from")) {
			connection.setFrom(destination);
			destination = null;
		}
		if (qName.equals("to")) {
			connection.setTo(destination);
			destination = null;
		}
		if (qName.equals("train")) {
			connection.setTrain(reading);
		}
		if (qName.equals("firstClass")) {
			capacity.setFirstClass(Integer.parseInt(reading));
		}
		if (qName.equals("secondClass")) {
			capacity.setSecondClass(Integer.parseInt(reading));
		}
		if (qName.equals("capacity")) {
			connection.setCapacity(capacity);
		}
		if (qName.equals("note")) {
			connection.setNote(reading);
		}

	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		reading = new String(ch, start, length);
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}
}
