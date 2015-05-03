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

import parser.ParserFactory;
import parser.ParserInterface;

public class DOMParserTests {
	private ParserFactory parserFactory;

	@Before
	public void setUp() {
		this.parserFactory = new ParserFactory("DOM");
	}

	@Test
	public void testNotValidXMLDate() {
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
		ParserInterface parser = parserFactory.getParser();
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
