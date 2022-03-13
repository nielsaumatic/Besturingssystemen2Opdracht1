import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = XML.readXML("processen10000.xml");

        Scheduler fcfs = new FCFS(processes);
        fcfs.schedule();
        System.out.println();

        Scheduler sjf = new SJF(processes);
        sjf.schedule();
        System.out.println();

        Scheduler srt = new SRT(processes);
        srt.schedule();
        System.out.println();

        Scheduler RR = new RR(processes);
        RR.schedule();
        System.out.println();
    }

}
