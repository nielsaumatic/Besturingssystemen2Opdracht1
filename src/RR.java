import java.util.ArrayList;
import java.util.List;

public class RR extends  Scheduler{

    public RR(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {

        List<Process> scheduled = this.getScheduled();
        for (Process process: processes) {
            scheduled.add(new Process(process.getPid(), process.getArrivaltime(), process.getServicetime()));
        }
        int time = scheduled.get(0).getArrivaltime();


        while (!scheduled.isEmpty()) {
            Process process = scheduled.get(0);
            if(process.getArrivaltime() > time){
                time = process.getArrivaltime();
            }
            int ts = Math.min(process.getServicetime(), timeslice);
            Process originalProcess = this.getProcess(process.getPid());

            if (originalProcess.getStarttime() == 0) {
                originalProcess.setStartAndEnd(time, time + ts);
            }
            else {
                originalProcess.setEndtime(time + ts);
            }
            time += ts;
            scheduled.remove(0);

            if (process.getServicetime() > timeslice) {
                process.setServicetime(process.getServicetime() - timeslice);

                for (int i = 0; i < scheduled.size(); i++) {
                    if(scheduled.get(i).getArrivaltime() > time) {
                        scheduled.add(i, process);
                        break;
                    }
                    else if (i == processes.size() - 1) {
                        scheduled.add(process);
                        break;
                    }
                }
            }
        }

        for (Process process: processes) {
            process.setWaittime(process.getEndtime() - process.getServicetime() - process.getArrivaltime());
            process.setTat(process.getWaittime() + process.getServicetime());
            process.setNormtat(process.getTat() / (double) process.getServicetime());
        }
    }
}
