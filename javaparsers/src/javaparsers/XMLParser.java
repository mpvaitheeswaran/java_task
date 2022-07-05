package javaparsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser implements MyParser {
	private String filename;
	private String tagName;
	private NodeList nList;

	
	public XMLParser(String filename,String tagName) {
		this.filename = filename;
		this.tagName = tagName;
		this.nList = getElementNodeList(filename,tagName);
		
	}
	
	private NodeList getElementNodeList(String filename,String tagName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		NodeList nList = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File(filename));
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName(tagName);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nList;
	}


	@Override
	public List<Map<String, String>> getListOfMaps() {
		List<Map<String, String>> listOfMaps = new ArrayList<Map<String,String>>();
		for (int nodeIndex = 0; nodeIndex < nList.getLength(); nodeIndex++) {
			Node nNode = nList.item(nodeIndex);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Map<String, String> data = new HashMap<String, String>();
				Element eElement = (Element) nNode;
				data.put("rollno", eElement.getAttribute("rollno"));
				data.put("name", eElement.getElementsByTagName("name").item(0).getTextContent());
				data.put("dept", eElement.getElementsByTagName("dept").item(0).getTextContent());
				data.put("age", eElement.getElementsByTagName("age").item(0).getTextContent());
				listOfMaps.add(data);
			}
		}
		return listOfMaps;
	}

}
