package org.ppi.core.parse.network.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import org.ppi.core.graph.Graph;
import org.ppi.core.graph.Node;
import org.ppi.core.graph.NodeFactory;
import org.ppi.core.parse.network.AbstractNetworkParser;
import org.ppi.core.parse.network.NetworkParserResult;

public class DIPNetworkParser extends AbstractNetworkParser {

	public DIPNetworkParser() {
	}

	@Override
	protected void execute() throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
		Graph graph = new Graph(file.getName());
		int errorCount = 0;
		
		signalCurrentOperation("Loading the file");

		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line, " \t:-");

			String first = null;
			String second = null;

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if(s.endsWith("N")) {
					if (first == null) {
						first = s;
					} else if (second == null) {
						second = s;
						break;
					}
				}
			}

			if (first != null && second != null) {
				if (first.equals(second)) {
					// loop
					continue;
				}

				Node n1 = NodeFactory.getInstance().createNode(first);
				Node n2 = NodeFactory.getInstance().createNode(second);
				
				graph.addEdge(n1, n2);

			} else {
				errorCount++;
			}
			
			checkPoint();
		}

		result = new NetworkParserResult();
		
		result.setErrorCount(errorCount);
		result.setGraph(graph);

	}

}
