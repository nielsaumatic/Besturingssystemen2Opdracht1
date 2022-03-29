import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = Utility.readXML("processen10000.xml");

        Scheduler fcfs = new FCFS(processes);
        fcfs.schedule();
        Utility.calcAverages("fcfs", fcfs);
        Utility.exportCSV("fcfs", fcfs);

        Scheduler sjf = new SJF(processes);
        sjf.schedule();
        Utility.calcAverages("sjf", sjf);
        Utility.exportCSV("sjf", sjf);

        Scheduler srt = new SRT(processes);
        srt.schedule();
        Utility.calcAverages("srt", srt);
        Utility.exportCSV("srt", srt);

        Scheduler rr2 = new RR(processes);
        rr2.setTimeslice(2);
        rr2.schedule();
        Utility.calcAverages("rr2", rr2);
        Utility.exportCSV("rr2", rr2);

        Scheduler rr4 = new RR(processes);
        rr4.setTimeslice(4);
        rr4.schedule();
        Utility.calcAverages("rr4", rr4);
        Utility.exportCSV("rr4", rr4);

        Scheduler rr8 = new RR(processes);
        rr8.setTimeslice(8);
        rr8.schedule();
        Utility.calcAverages("rr8", rr8);
        Utility.exportCSV("rr8", rr8);

        Scheduler hrrn = new HRRN(processes);
        hrrn.schedule();
        Utility.calcAverages("hrrn", hrrn);
        Utility.exportCSV("hrrn", hrrn);

        Scheduler mlfb5 = new MLFB(processes);
        mlfb5.setTimeslice(5);
        mlfb5.schedule();
        Utility.calcAverages("mlfb5", mlfb5);
        Utility.exportCSV("mlfb5", mlfb5);

        Scheduler mlfb25 = new MLFB(processes);
        mlfb25.setTimeslice(25);
        mlfb25.schedule();
        Utility.calcAverages("mlfb25", mlfb25);
        Utility.exportCSV("mlfb25", mlfb25);

    }
}
