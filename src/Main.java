import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = XML.readXML("processen10000.xml");

        Scheduler fcfs = new FCFS(processes);
        fcfs.schedule();
        CSV.exportCSV("fcfs", fcfs);

        Scheduler sjf = new SJF(processes);
        sjf.schedule();
        CSV.exportCSV("sjf", sjf);

        Scheduler srt = new SRT(processes);
        srt.schedule();
        CSV.exportCSV("srt", srt);

        Scheduler rr2 = new RR(processes);
        rr2.setTimeslice(2);
        rr2.schedule();
        CSV.exportCSV("rr2", rr2);

        Scheduler rr4 = new RR(processes);
        rr4.setTimeslice(4);
        rr4.schedule();
        CSV.exportCSV("rr4", rr4);

        Scheduler rr8 = new RR(processes);
        rr8.setTimeslice(8);
        rr8.schedule();
        CSV.exportCSV("rr8", rr8);

        Scheduler mlfb25 = new MLFB(processes);
        mlfb25.setTimeslice(25);
        mlfb25.schedule();
        CSV.exportCSV("mlfb25", mlfb25);

        Scheduler mlfb50 = new MLFB(processes);
        mlfb50.setTimeslice(50);
        mlfb50.schedule();
        CSV.exportCSV("mlfb50", mlfb50);
    }
}
