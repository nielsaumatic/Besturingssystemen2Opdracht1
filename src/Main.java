import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = XML.readXML("processen10000.xml");

//        Scheduler fcfs = new FCFS(processes);
//        fcfs.schedule();
//        System.out.println();

        Scheduler jsf = new JSF(processes);
        jsf.schedule();
        System.out.println();
    }

}
