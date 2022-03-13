import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SRT extends Scheduler{

    public SRT(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {
        int time = processes.get(0).getArrivaltime();

        while (!processes.isEmpty()) {
            List<Process> available = new ArrayList<>();

            for (Process process : processes) {
                if(process.getArrivaltime() <= time) {
                    available.add(process);
                }
            }

            if(available.isEmpty()){
                available.add(processes.get(0));
                time = processes.get(0).getArrivaltime();
            }

            available.sort(Comparator.comparingInt(Process::getServicetime));

            Process process = available.get(0);

            Process originalProcess = this.getProcessInScheduled(process.getPid());
            if (originalProcess == null) {
                originalProcess = new Process(process.getPid(), process.getArrivaltime(), process.getServicetime());
                scheduled.add(originalProcess);
            }

            if (originalProcess.getStarttime() == 0) {
                originalProcess.setStartAndEnd(time, ++time);
            }
            else {
                originalProcess.setEndtime(++time);
            }

            process.setServicetime(process.getServicetime() - 1);

            if (process.getServicetime() == 0) {
                for (int i = 0; i < processes.size(); i++) {
                    if (processes.get(i).getPid() == process.getPid()) {
                        processes.remove(i);
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
