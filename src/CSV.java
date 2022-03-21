import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class CSV {

    private static final String DELIMITER = ",";
    private static final String SEPARATOR = "\n";

    public static void exportCSV(String filename, Scheduler scheduler) {
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
}

