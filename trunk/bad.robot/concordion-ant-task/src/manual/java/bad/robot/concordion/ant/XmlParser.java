package bad.robot.concordion.ant;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class XmlParser {

    private final Document document;

    public XmlParser(File file) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (ParserConfigurationException e) {
            throw new IOException(e);
        } catch (SAXException e) {
            throw new IOException(e);
        }
    }

    public String getTestSection() throws TransformerException {
        NodeList list = getByTagName("div", document);
        return prettyPrint(list.item(0));
    }

    private static Element getById(String id, Document document) {
        Element element = document.getElementById(id);
        if (element == null)
            throw new IllegalStateException("missing element with id " + id);
        return element;
    }

    private static NodeList getByTagName(String tag, Document document) {
        NodeList list = document.getElementsByTagName(tag);
        if (list == null || list.getLength() == 0)
            throw new IllegalStateException("missing element with id " + tag);
        return list;
    }

    private static String prettyPrint(Node node) throws TransformerException {
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(node), new StreamResult(writer));
        return tidyUp(writer.toString());
    }

    private static String tidyUp(String string) {
        return stripNamespace(stripDivSection(stripNewlinesForWindows(string)));
    }

    private static String stripNewlinesForWindows(String string) {
        return string.replaceAll("\r\n\r\n", "\r\n");
    }

    private static String stripDivSection(String string) {
        return string.replaceFirst("<div id=\"tests\">", "").replaceFirst("</div>", "");
    }

    private static String stripNamespace(String string) {
        return string.replaceFirst("xmlns:concordion=\"http://www.concordion.org/2007/concordion\"", "");
    }
    
}