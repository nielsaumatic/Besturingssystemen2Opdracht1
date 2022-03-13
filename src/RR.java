import java.util.List;

public class RR extends  Scheduler{

    public RR(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {
        int time = processes.get(0).getArrivaltime();

        while (!processes.isEmpty()) {
            Process process = processes.get(0);
            if(process.getArrivaltime() > time){
                time = process.getArrivaltime();
            }
            int ts = Math.min(process.getServicetime(), timeslice);

            Process originalProcess = this.getProcessInScheduled(process.getPid());
            if (originalProcess == null) {
                originalProcess = new Process(process.getPid(), process.getArrivaltime(), process.getServicetime());
                scheduled.add(originalProcess);
            }

            if (originalProcess.getStarttime() == 0) {
                originalProcess.setStartAndEnd(time, time + ts);
            }
            else {
                originalProcess.setEndtime(time + ts);
            }
            time += ts;
            processes.remove(0);

            if (process.getServicetime() > timeslice) {
                process.setServicetime(process.getServicetime() - timeslice);

                for (int i = 0; i < processes.size(); i++) {
                    if(processes.get(i).getArrivaltime() > time) {
                        processes.add(i, process);
                        break;
                    }
                    else if (i == processes.size() - 1) {
                        processes.add(process);
                        break;
                    }
                }
            }
        }

        for (Process process: scheduled) {
            process.setWaittime(process.getEndtime() - process.getServicetime() - process.getArrivaltime());
            process.setTat(process.getWaittime() + process.getServicetime());
            process.setNormtat(process.getTat() / (double) process.getServicetime());
        }
    }
}
