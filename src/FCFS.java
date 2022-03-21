import java.util.List;

public class FCFS extends Scheduler{

    public FCFS(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {
        for (Process process: processes) {
            if (scheduled.isEmpty()) {
                process.setStartAndEnd(process.getArrivaltime(), process.getArrivaltime() + process.getServicetime());
                scheduled.add(process);
            }
            else {
                Process prevProcess = this.getLastScheduled();
                if (process.getArrivaltime() > prevProcess.getEndtime()) {
                    process.setStartAndEnd(process.getArrivaltime(), process.getArrivaltime() + process.getServicetime());
                }
                else {
                    process.setStartAndEnd(prevProcess.getEndtime(), prevProcess.getEndtime() + process.getServicetime());
                }
                scheduled.add(process);
            }
        }

        for (Process process : scheduled) {
            if (process.getStarttime() > process.getArrivaltime()){
                process.setWaittime(process.getStarttime() - process.getArrivaltime());
            }
            process.setTat(process.getWaittime() + process.getServicetime());
            process.setNormtat(process.getTat() / (double) process.getServicetime());
        }
    }

}
