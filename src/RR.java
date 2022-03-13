import java.util.ArrayList;
import java.util.List;

public class RR extends  Scheduler{

    public RR(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {

        List<Process> processesNotFinished = new ArrayList<>();
        for (Process process: processes) {
            processesNotFinished.add(new Process(process.getPid(), process.getArrivaltime(), process.getServicetime()));
        }
        int time = processesNotFinished.get(0).getArrivaltime();


        while (!processesNotFinished.isEmpty()) {
            Process process = processesNotFinished.get(0);
            int ts = Math.min(process.getServicetime(), timeslice);
            Process originalProcess = this.getProcess(process.getPid());

            if (process.getStarttime() == 0) {
                originalProcess.setStartAndEnd(time, time + ts);
            }
            else {
                originalProcess.setEndtime(time + ts);
            }
            time += ts;
            processesNotFinished.remove(0);

            if (process.getServicetime() > timeslice) {
                process.setServicetime(process.getServicetime() - timeslice);

                for (int i = 0; i < processesNotFinished.size(); i++) {
                    if(processesNotFinished.get(i).getArrivaltime() > time) {
                        processesNotFinished.add(i, process);
                        break;
                    }
                    else if (i == processes.size() - 1) {
                        processesNotFinished.add(process);
                        break;
                    }
                }
            }
        }


    }
}
