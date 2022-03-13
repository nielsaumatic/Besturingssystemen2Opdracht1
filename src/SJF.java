import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF extends Scheduler{

    public SJF(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule(){
        List<Process> scheduled = getScheduled();
        List<Process> processes = getProcesses();
        int time = processes.get(0).getArrivaltime();

        while(!processes.isEmpty()){
            List<Process> available = new ArrayList<>();

            for(Process process : processes){
                if(process.getArrivaltime() <= time){
                    available.add(process);
                }
            }

            //Als er geen processen in de wachtrij staan skip dan direct naar het punt dat de volgende toekomt
            if(available.isEmpty()){
                available.add(processes.get(0));
                time = processes.get(0).getArrivaltime();
            }

            available.sort(Comparator.comparingInt(Process::getServicetime));

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

        for (Process process : getScheduled()) {
            if (process.getStarttime() > process.getArrivaltime()){
                process.setWaittime(process.getStarttime() - process.getArrivaltime());
            }

            process.setTat(process.getWaittime() + process.getServicetime());
            process.setNormtat(process.getTat() / (double) process.getServicetime());
        }

    }
}