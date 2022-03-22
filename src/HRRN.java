import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HRRN extends Scheduler{

    public HRRN(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {
        int time = processes.get(0).getArrivaltime();

        while(!processes.isEmpty()){
            List<Process> available = new ArrayList<>();

            for(Process process : processes){
                if(process.getArrivaltime() <= time){
                    available.add(process);
                }
            }

            if(available.isEmpty()){
                available.add(processes.get(0));
                time = processes.get(0).getArrivaltime();
            }

            int finalTime = time;
            available.sort((p1, p2) -> ((finalTime - p2.getArrivaltime() + p2.getServicetime()) / p2.getServicetime()) - ((finalTime - p1.getArrivaltime() + p1.getServicetime()) / p1.getServicetime()));

            Process process = available.get(0);
            process.setStartAndEnd(time, time + process.getServicetime());
            scheduled.add(process);
            time += process.getServicetime();

            for(Process processed : processes){
                if(processed.getPid() == process.getPid()){
                    processes.remove(processed);
                    break;
                }
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
