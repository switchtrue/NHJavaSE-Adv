package com.mycom.sax;

import java.io.File;
import java.io.IOException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXNodeCount {

	private class DocHandler extends DefaultHandler {
		int elementCount;


		public DocHandler() {
			// TODO Auto-generated constructor stub
			super();
		}

		public void startElement(String uri, String localName, String qName,
				Attributes att) {
			elementCount++;
			for(int i=0;i<att.getLength();i++){
				System.out.println("Got attribute:  " + att.getQName(i)+ " and value is:  "+ att.getValue(i) );
			}
		
		}

		public void endDocument() {
			System.out.printf("Document contained %d elements. \n",
					elementCount);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SAXNodeCount saxCount = new SAXNodeCount();
		String filename = args[0];
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(new File(filename), saxCount.new DocHandler());
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

	}

}
