import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler {
    protected final List<Process> processes;
    protected final List<Process> scheduled;
    protected int timeslice;

    public Scheduler(List<Process> processes) {
        this.processes = new ArrayList<>();
        for (Process p: processes) {
            this.processes.add(new Process(p.getPid(), p.getArrivaltime(), p.getServicetime()));
        }

        this.scheduled = new ArrayList<>();
        this.timeslice = 1;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public List<Process> getScheduled() {
        return scheduled;
    }

    public void setTimeslice(int timeslice) {
        this.timeslice = timeslice;
    }

    public int getTimeslice() {
        return timeslice;
    }

    public Process getLastScheduled() {
        return scheduled.get(scheduled.size() - 1);
    }

    public abstract void schedule();
}
