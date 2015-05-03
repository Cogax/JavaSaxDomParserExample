package parser;

public class ParserFactory {
	private String deliverOnly;

	public ParserFactory(String deliverOnlyParserOfType) {
		this.deliverOnly = deliverOnlyParserOfType;
	}

	public ParserInterface getParser() {
		// if(...
		return new DOMParser();
	}
}
