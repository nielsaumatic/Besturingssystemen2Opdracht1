import java.util.List;

public class FCFS extends Scheduler{

    public FCFS(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {
        List<Process> scheduled = getScheduled();

        for (Process process: getProcesses()) {
            if (scheduled.isEmpty()) {
                process.setStartAndEnd(process.getArrivaltime(), process.getArrivaltime() + process.getServicetime());
                scheduled.add(process);
            }
            else {
                Process prevProcess = this.getLastScheduled();
                process.setStartAndEnd(prevProcess.getEndtime(), prevProcess.getEndtime() + process.getServicetime());
                scheduled.add(process);
            }
        }

        for (Process process : getScheduled()) {
            if (process.getStarttime() > process.getArrivaltime()){
                process.setWaittime(process.getStarttime() - process.getArrivaltime());
            }

            process.setTat(process.getWaittime() + process.getServicetime());
            process.setNormtat(process.getTat() / (double) process.getServicetime());
        }
    }

}
