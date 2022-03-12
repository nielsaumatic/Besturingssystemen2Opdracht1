import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XML {

    public static List<Process> readXML(String filename){

        List<Process> processes = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(filename));

            NodeList list = doc.getElementsByTagName("process");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if ( node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    int pid = Integer.parseInt(element.getElementsByTagName("pid").item(0).getTextContent());
                    int arrivaltime = Integer.parseInt(element.getElementsByTagName("arrivaltime").item(0).getTextContent());
                    int servicetime = Integer.parseInt(element.getElementsByTagName("servicetime").item(0).getTextContent());

                    //System.out.println("pid: " + pid + " arrivaltime: " + arrivaltime + " servicetime: " + servicetime);

                    processes.add(new Process(pid, arrivaltime, servicetime));
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return processes;
    }
}
