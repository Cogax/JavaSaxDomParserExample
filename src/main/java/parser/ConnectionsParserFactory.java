package parser;

import parser.dom.ConnectionsDOMParser;
import parser.sax.ConnectionsSAXParser;

public class ConnectionsParserFactory {
	private String deliverOnly;

	public ConnectionsParserFactory(String deliverOnlyParserOfType) {
		this.deliverOnly = deliverOnlyParserOfType;
	}

	public ConnectionsParserInterface getParser() {
		if (deliverOnly == "DOM")
			return new ConnectionsDOMParser();
		else
			return new ConnectionsSAXParser();
	}
}
