import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = XML.readXML("processen10000.xml");

        Scheduler fcfs = new FCFS(processes);
        fcfs.schedule();
        System.out.println("Average norm tat fcfs: " + fcfs.getAverageNormTAT());
        System.out.println("Average wait fcfs: " + fcfs.getAverageWait());
        System.out.println("Average tat fcfs: " + fcfs.getAverageTat());
        CSV.exportCSV("fcfs", fcfs);

        Scheduler sjf = new SJF(processes);
        sjf.schedule();
        System.out.println("Average norm tat sjf: " + sjf.getAverageNormTAT());
        System.out.println("Average wait sjf: " + sjf.getAverageWait());
        System.out.println("Average tat sjf: " + sjf.getAverageTat());
        CSV.exportCSV("sjf", sjf);

        Scheduler srt = new SRT(processes);
        srt.schedule();
        System.out.println("Average norm tat srt: " + srt.getAverageNormTAT());
        System.out.println("Average wait srt: " + srt.getAverageWait());
        System.out.println("Average tat srt: " + srt.getAverageTat());
        CSV.exportCSV("srt", srt);

        Scheduler rr2 = new RR(processes);
        rr2.setTimeslice(2);
        rr2.schedule();
        System.out.println("Average norm tat rr2: " + rr2.getAverageNormTAT());
        System.out.println("Average wait rr2: " + rr2.getAverageWait());
        System.out.println("Average tat rr2: " + rr2.getAverageTat());
        CSV.exportCSV("rr2", rr2);

        Scheduler rr4 = new RR(processes);
        rr4.setTimeslice(4);
        rr4.schedule();
        System.out.println("Average norm tat rr4: " + rr4.getAverageNormTAT());
        System.out.println("Average wait rr4: " + rr4.getAverageWait());
        System.out.println("Average tat rr4: " + rr4.getAverageTat());
        CSV.exportCSV("rr4", rr4);

        Scheduler rr8 = new RR(processes);
        rr8.setTimeslice(8);
        rr8.schedule();
        System.out.println("Average norm tat rr8: " + rr8.getAverageNormTAT());
        System.out.println("Average wait rr8: " + rr8.getAverageWait());
        System.out.println("Average tat rr8: " + rr8.getAverageTat());
        CSV.exportCSV("rr8", rr8);

        Scheduler hrrn = new HRRN(processes);
        hrrn.schedule();
        System.out.println("Average norm tat hrrn: " + hrrn.getAverageNormTAT());
        System.out.println("Average wait hrrn: " + hrrn.getAverageWait());
        System.out.println("Average tat hrrn: " + hrrn.getAverageTat());
        CSV.exportCSV("hrrn", hrrn);

        Scheduler mlfb5 = new MLFB(processes);
        mlfb5.setTimeslice(5);
        mlfb5.schedule();
        System.out.println("Average norm tat mlfb5: " + mlfb5.getAverageNormTAT());
        System.out.println("Average wait mlfb5: " + mlfb5.getAverageWait());
        System.out.println("Average tat mlfb5: " + mlfb5.getAverageTat());
        CSV.exportCSV("mlfb5", mlfb5);

        Scheduler mlfb25 = new MLFB(processes);
        mlfb25.setTimeslice(25);
        mlfb25.schedule();
        System.out.println("Average norm tat mlfb25: " + mlfb25.getAverageNormTAT());
        System.out.println("Average wait mlfb25: " + mlfb25.getAverageWait());
        System.out.println("Average tat mlfb25: " + mlfb25.getAverageTat());
        CSV.exportCSV("mlfb25", mlfb25);

    }
}
