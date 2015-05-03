package xmlexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Connection;
import model.ConnectionCapacity;
import model.ConnectionDestination;

import org.junit.Before;
import org.junit.Test;

import parser.ConnectionsParserFactory;
import parser.ConnectionsParserInterface;

public class ConnectionsParserTests {
	private ConnectionsParserFactory parserFactory;

	@Before
	public void setUp() {
		this.parserFactory = new ConnectionsParserFactory("DOM");
		// this.parserFactory = new ConnectionsParserFactory("SAX");
	}

	@Test
	public void testNotValidXMLDate() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("src/test/resources/notValidDate.xml"));
		try {
			parser.parse();
			fail("XML is valid!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void testNotValidXMLFrom() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("src/test/resources/notValidFrom.xml"));
		try {
			parser.parse();
			fail("XML is valid!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void testNotValidXMLTo() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("src/test/resources/notValidTo.xml"));
		try {
			parser.parse();
			fail("XML is valid!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void testNotValidXMLTrain() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("src/test/resources/notValidTrain.xml"));
		try {
			parser.parse();
			fail("XML is valid!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void testNotValidXMLCapacity() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("src/test/resources/notValidCapacity.xml"));
		try {
			parser.parse();
			fail("XML is valid!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void testValidXML() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("data.xml"));
		try {
			parser.parse();
			assertTrue(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("parse Exception was thrown!");
		}
	}

	@Test
	public void testCountConnections() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("src/test/resources/multipleConnections.xml"));
		try {
			parser.parse();
			assertEquals(3, parser.getConnections().size());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("parse Exception was thrown!");
		}
	}

	@Test
	public void testConnectionDate() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("data.xml"));
		try {
			parser.parse();

			ArrayList<Connection> connections = parser.getConnections();

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse("2014-12-01");

			assertEquals(date, connections.get(0).getDate());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("parse Exception was thrown!");
		}
	}

	@Test
	public void testConnectionFrom() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("data.xml"));
		try {
			parser.parse();

			ArrayList<Connection> connections = parser.getConnections();
			ConnectionDestination from = connections.get(0).getFrom();

			// station
			assertEquals("St. Gallen", from.getStation());

			// time
			DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			Date time = timeFormat.parse("12:11:00");
			assertEquals(time, from.getTime());

			// track
			assertEquals(3, from.getTrack());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("parse Exception was thrown!");
		}
	}

	@Test
	public void testConnectionTo() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("data.xml"));
		try {
			parser.parse();

			ArrayList<Connection> connections = parser.getConnections();
			ConnectionDestination from = connections.get(0).getTo();

			// station
			assertEquals("Zürich", from.getStation());

			// time
			DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			Date time = timeFormat.parse("15:11:00");
			assertEquals(time, from.getTime());

			// track
			assertEquals(1, from.getTrack());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("parse Exception was thrown!");
		}
	}

	@Test
	public void testConnectionTrain() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("data.xml"));
		try {
			parser.parse();

			ArrayList<Connection> connections = parser.getConnections();
			assertEquals("ICN", connections.get(0).getTrain());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("parse Exception was thrown!");
		}
	}

	@Test
	public void testConnectionCapacity() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("data.xml"));
		try {
			parser.parse();

			ArrayList<Connection> connections = parser.getConnections();
			ConnectionCapacity capacity = connections.get(0).getCapacity();
			assertEquals(1, capacity.getFirstClass());
			assertEquals(3, capacity.getSecondClass());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("parse Exception was thrown!");
		}
	}

	@Test
	public void testConnectionNote() {
		ConnectionsParserInterface parser = parserFactory.getParser();
		parser.setXMLFile(new File("data.xml"));
		try {
			parser.parse();

			ArrayList<Connection> connections = parser.getConnections();
			assertEquals("Richtung: Genève", connections.get(0).getNote());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("parse Exception was thrown!");
		}
	}
}
