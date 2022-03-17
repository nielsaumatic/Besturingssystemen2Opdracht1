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

        Scheduler rr2 = new RR(processes);
        rr2.setTimeslice(2);
        rr2.schedule();
        System.out.println();

        Scheduler rr4 = new RR(processes);
        rr4.setTimeslice(4);
        rr4.schedule();
        System.out.println();

        Scheduler rr8 = new RR(processes);
        rr8.setTimeslice(8);
        rr8.schedule();
        System.out.println();

        Scheduler mlfb25 = new MLFB(processes);
        mlfb25.setTimeslice(25);
        mlfb25.schedule();
        System.out.println();

        Scheduler mlfb50 = new MLFB(processes);
        mlfb50.setTimeslice(50);
        mlfb50.schedule();
        System.out.println();
    }
}
