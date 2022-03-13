import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSF extends Scheduler{

    public JSF(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule(){
        List<Process> scheduled = getScheduled();
        List<Process> processen = getProcesses();
        int endtime = processen.get(0).getArrivaltime();

        while(!processen.isEmpty()){
            List<Process> available = new ArrayList<>();

            for(Process process : processen){
                if(process.getArrivaltime() <= endtime){
                    available.add(process);
                }
            }

            //Als er geen processen in de wachtrij staan skip dan direct naar het punt dat de volgende toekomt
            if(available.isEmpty()){
                available.add(processen.get(0));
                endtime = processen.get(0).getArrivaltime();
            }
            //

            Collections.sort(available, (Process p1, Process p2) -> {
                if (((Process) p1).getServicetime() == ((Process) p2).getServicetime())
                {
                    return 0;
                }
                else if (((Process) p1).getServicetime() < ((Process) p2).getServicetime())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            });

            Process process = available.get(0);
            process.setStartAndEnd(endtime, endtime + process.getServicetime());
            scheduled.add(process);
            endtime += process.getServicetime();

            for(Process processd : processen){
                if(processd.getPid() == process.getPid()){
                    processen.remove(processd);
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