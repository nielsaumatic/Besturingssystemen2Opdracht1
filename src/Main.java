import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = XML.readXML("processen10000.xml");

        Scheduler fcfs = new FCFS(processes);
        fcfs.schedule();
        System.out.println("Average norm tat fcfs: " + fcfs.getAverageNormTAT());
        System.out.println("Average wait fcfs: " + fcfs.getAverageWait());
        CSV.exportCSV("fcfs", fcfs);

        Scheduler sjf = new SJF(processes);
        sjf.schedule();
        System.out.println("Average norm tat sjf: " + sjf.getAverageNormTAT());
        System.out.println("Average wait sjf: " + sjf.getAverageWait());
        CSV.exportCSV("sjf", sjf);

        Scheduler srt = new SRT(processes);
        srt.schedule();
        System.out.println("Average norm tat srt: " + srt.getAverageNormTAT());
        System.out.println("Average wait srt: " + srt.getAverageWait());
        CSV.exportCSV("srt", srt);

        Scheduler rr2 = new RR(processes);
        rr2.setTimeslice(2);
        rr2.schedule();
        System.out.println("Average norm tat rr2: " + rr2.getAverageNormTAT());
        System.out.println("Average wait rr2: " + rr2.getAverageWait());
        CSV.exportCSV("rr2", rr2);

        Scheduler rr4 = new RR(processes);
        rr4.setTimeslice(4);
        rr4.schedule();
        System.out.println("Average norm tat rr4: " + rr4.getAverageNormTAT());
        System.out.println("Average wait rr4: " + rr4.getAverageWait());
        CSV.exportCSV("rr4", rr4);

        Scheduler rr8 = new RR(processes);
        rr8.setTimeslice(8);
        rr8.schedule();
        System.out.println("Average norm tat rr8: " + rr8.getAverageNormTAT());
        System.out.println("Average wait rr8: " + rr8.getAverageWait());
        CSV.exportCSV("rr8", rr8);

        Scheduler hrrn = new HRRN(processes);
        hrrn.schedule();
        System.out.println("Average norm tat hrrn: " + hrrn.getAverageNormTAT());
        System.out.println("Average wait hrrn: " + hrrn.getAverageWait());
        CSV.exportCSV("hrrn", hrrn);

        Scheduler mlfb25 = new MLFB(processes);
        mlfb25.setTimeslice(25);
        mlfb25.schedule();
        System.out.println("Average norm tat mlfb25: " + mlfb25.getAverageNormTAT());
        System.out.println("Average wait mlfb25: " + mlfb25.getAverageWait());
        CSV.exportCSV("mlfb25", mlfb25);

        Scheduler mlfb50 = new MLFB(processes);
        mlfb50.setTimeslice(50);
        mlfb50.schedule();
        System.out.println("Average norm tat mlfb50: " + mlfb50.getAverageNormTAT());
        System.out.println("Average wait mlfb50: " + mlfb50.getAverageWait());
        CSV.exportCSV("mlfb50", mlfb50);
    }
}
