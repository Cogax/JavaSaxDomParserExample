/**
 * @author Andreas Gyr <andygyr@gmx.ch>
 */
package util;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {
	public void warning(SAXParseException e) throws SAXException {
		throw new SAXException(e.getMessage());
	}

	public void error(SAXParseException e) throws SAXException {
		throw new SAXException(e.getMessage());
	}

	public void fatalError(SAXParseException e) throws SAXException {
		throw new SAXException(e.getMessage());
	}
}
