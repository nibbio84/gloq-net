package org.ppi.core.parse.network;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.ppi.core.parse.network.parser.DIPNetworkParser;
import org.ppi.core.parse.network.parser.TabNetworkParser;


public class NetworkParserFactory {

protected static NetworkParserFactory instance;
	
	Map<String, Class<? extends AbstractNetworkParser>> parsers;
	
	public NetworkParserFactory() {
		
		this.parsers = new HashMap<String, Class<? extends AbstractNetworkParser>>();
		
		this.parsers.put("Tab separated file", TabNetworkParser.class);
		this.parsers.put("DIP File Format", DIPNetworkParser.class);
		
	}
	
	public static NetworkParserFactory getInstance() {
		if(instance==null)
			instance = new NetworkParserFactory();
		return instance;
	}
	
	
	public Set<String> getParserNames() {
		return new TreeSet<String>(parsers.keySet());
	}
	
	public Class<? extends AbstractNetworkParser> getClass(String parserName) {
		return parsers.get(parserName);
	}
	
	
}
