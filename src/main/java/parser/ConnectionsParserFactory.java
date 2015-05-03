package parser;

import parser.dom.ConnectionsDOMParser;
import parser.sax.ConnectionsSAXParser;

public class ConnectionsParserFactory {
	public ConnectionsParserInterface getParser(String parserType) {
		if (parserType == "DOM") {
			return new ConnectionsDOMParser();
		} else {
			return new ConnectionsSAXParser();
		}
	}
}
