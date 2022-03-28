import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler {
    protected final List<Process> processes;
    protected final List<Process> scheduled;
    protected int timeslice;

    public Scheduler(List<Process> processes) {
        this.processes = new ArrayList<>();
        for (Process process: processes) {
            this.processes.add(new Process(process.getPid(), process.getArrivaltime(), process.getServicetime()));
        }

        this.scheduled = new ArrayList<>();
        this.timeslice = 1;
    }

    public List<Process> getScheduled() {
        return scheduled;
    }

    public void setTimeslice(int timeslice) {
        this.timeslice = timeslice;
    }

    public Process getLastScheduled() {
        return scheduled.get(scheduled.size() - 1);
    }

    public Process getProcessInProcesses(int pid) {
        for (Process process: processes) {
            if (process.getPid() == pid) {
                return process;
            }
        }
        return  null;
    }

    public Process getProcessInScheduled(int pid) {
        for (Process process: scheduled) {
            if (process.getPid() == pid) {
                return process;
            }
        }
        return  null;
    }

    public double getAverageNormTAT() {
        double totaal = 0;
        for (Process p: scheduled) {
            totaal += p.getNormtat();
        }
        return totaal / scheduled.size();
    }

    public double getAverageWait() {
        double totaal = 0;
        for (Process p: scheduled) {
            totaal += p.getWaittime();
        }
        return totaal / scheduled.size();
    }

    public double getAverageTat() {
        double totaal = 0;
        for (Process p: scheduled) {
            totaal += p.getTat();
        }
        return totaal / scheduled.size();
    }

    public abstract void schedule();
}
