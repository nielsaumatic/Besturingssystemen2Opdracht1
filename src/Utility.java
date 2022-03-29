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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Utility {

    public static List<Process> readXML(String filename) {

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

                    processes.add(new Process(pid, arrivaltime, servicetime));
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return processes;
    }


    public static void exportCSV(String filename, Scheduler scheduler) {
        String DELIMITER = ",";
        String SEPARATOR = "\n";

        FileWriter file;

        List<Process> processes = scheduler.getScheduled();
        processes.sort(Comparator.comparingInt(Process::getServicetime));

        double[] normTATPerPercentage = new double[100];
        double[] waitPerPercentage = new double[100];
        int aantalPerPercentage = processes.size() / 100;

        for (int i = 0; i < 100; i++) {
            double totaalTAT = 0;
            double totaalWait = 0;
            for (int j = 0; j < aantalPerPercentage; j++) {
                totaalTAT += processes.get((i * aantalPerPercentage) + j).getNormtat();
                totaalWait += processes.get((i * aantalPerPercentage) + j).getWaittime();
            }
            normTATPerPercentage[i] = totaalTAT / aantalPerPercentage;
            waitPerPercentage[i] = totaalWait / aantalPerPercentage;
        }

        try {
            file = new FileWriter(new File("resultsCSV", filename +".csv"));
            file.append("percentage,gemiddeldNormTAT,gemiddeldWait");
            file.append(SEPARATOR);

            for (int i = 0; i < 100; i++) {
                file.append(Integer.toString(i+1));
                file.append(DELIMITER);
                file.append(Double.toString(normTATPerPercentage[i]));
                file.append(DELIMITER);
                file.append(Double.toString(waitPerPercentage[i]));
                file.append(SEPARATOR);
            }

            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void calcAverages(String algorithmName, Scheduler scheduler) {
        System.out.println("Average norm tat " + algorithmName + ": " + scheduler.getAverageNormTAT());
        System.out.println("Average wait " + algorithmName + ": " + scheduler.getAverageWait());
        System.out.println("Average tat " + algorithmName + ": " + scheduler.getAverageTat());
    }
}
