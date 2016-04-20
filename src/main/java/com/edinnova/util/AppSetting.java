package com.edinnova.util;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xpath.internal.XPathAPI;

public class AppSetting {
	/** The Constant CONFIG_NAME. */
	private static final String CONFIG_NAME = "app-settings.xml";

	/** The params. */
	private static Hashtable<String, String> params;
	static {
		try {
			ClassLoader loader = AppSetting.class.getClassLoader();
			InputStream configStream = loader.getResourceAsStream(CONFIG_NAME);
			buildConfig(configStream);
		} catch (Exception ex) {
			params = new Hashtable<String, String>();
		}
	}

	/**
	 * Instantiates a new app setting.
	 */
	public AppSetting() {
	}

	/**
	 * Gets the string value.
	 * 
	 * @param name
	 *            the name
	 * @return the string value
	 */
	public static String getStringValue(String name) {
		return params.get(name);
	}

	/**
	 * Gets the boolean value.
	 * 
	 * @param name
	 *            the name
	 * @return the boolean value
	 */
	public static boolean getBooleanValue(String name) {
		String val = getStringValue(name);
		return "true".equalsIgnoreCase(val) || "yes".equalsIgnoreCase(val);
	}

	/**
	 * Gets the integer value.
	 * 
	 * @param name
	 *            the name
	 * @return the integer value
	 */
	public static int getIntegerValue(String name) {
		String val = getStringValue(name);
		return Integer.parseInt(val);
	}

	/**
	 * Gets the long value.
	 * 
	 * @param name
	 *            the name
	 * @return the integer value
	 */
	public static long getLongValue(String name) {
		String val = getStringValue(name);
		return Long.parseLong(val);
	}

	/**
	 * Gets the double value.
	 * 
	 * @param name
	 *            the name
	 * @return the double value
	 */
	public static double getDoubleValue(String name) {
		String val = getStringValue(name);
		return Double.parseDouble(val);
	}

	/**
	 * Gets the date value.
	 * 
	 * @param name
	 *            the name
	 * @return the date value
	 */
	public static Date getDateValue(String name) {
		String val = getStringValue(name);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = format.parse(val);
		} catch (Exception ex) {
			date = null;
		}
		return date;
	}

	/**
	 * Builds the config.
	 * 
	 * @param configStream
	 *            the config stream
	 * @throws Exception
	 *             the exception
	 */
	private static void buildConfig(InputStream configStream) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(configStream);
		params = new Hashtable<String, String>();
		NodeList paramNodeList = XPathAPI.selectNodeList(document, "/configuration/app-setting/param");
		for (int i = 0; i < paramNodeList.getLength(); i++) {
			Node paramNode = paramNodeList.item(i);
			String name = getNodeValue(paramNode, "./@name");
			String value = getNodeValue(paramNode, "./@value");
			params.put(name, value);
		}
	}

	/**
	 * Gets the node value.
	 * 
	 * @param contextNode
	 *            the context node
	 * @param xpath
	 *            the xpath
	 * @return the node value
	 */
	private static String getNodeValue(Node contextNode, String xpath) {
		String val = null;
		try {
			Node node = XPathAPI.selectSingleNode(contextNode, xpath);
			val = node.getNodeValue();
		} catch (TransformerException ex) {
			val = null;
		}
		return val;
	}
}
