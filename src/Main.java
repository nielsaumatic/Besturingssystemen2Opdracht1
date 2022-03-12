import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = XML.readXML("test.xml");

        Scheduler fcfs = new FCFS(processes);
    }

}
