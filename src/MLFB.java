import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MLFB extends Scheduler{
    
    

    public MLFB(List<Process> processes) {
        super(processes);
    }

    @Override
    public void schedule() {
        Queue<Process> firstQueue = new LinkedList<>();
        Queue<Process> secondQueue = new LinkedList<>();
        Queue<Process> thirdQueue = new LinkedList<>();
        Queue<Process> fourthQueue = new LinkedList<>();
        Queue<Process> fifthQueue = new LinkedList<>();
        int firstTime = timeslice;
        int secondTime = timeslice * 2;
        int thirdTime = timeslice * 4;
        int fourthTime = timeslice * 6;
        int fifthTime = timeslice * 8;

        int time = processes.get(0).getArrivaltime();

        List<Process> processesToRemove = new ArrayList<>();

        Process process;
        Process originalProcess;

        while (!processes.isEmpty() || !firstQueue.isEmpty() || !secondQueue.isEmpty() || !thirdQueue.isEmpty() || !fourthQueue.isEmpty() || !fifthQueue.isEmpty()) {
            processesToRemove.clear();
            for (Process p : processes) {
                if(p.getArrivaltime() <= time) {
                    firstQueue.add(p);
                    processesToRemove.add(p);
                }
            }
            processes.removeAll(processesToRemove);

            if (!processes.isEmpty() && firstQueue.isEmpty() && secondQueue.isEmpty() && thirdQueue.isEmpty() && fourthQueue.isEmpty() && fifthQueue.isEmpty()) {
                time = processes.get(0).getArrivaltime();
                continue;
            }

            if(!firstQueue.isEmpty()) {
                process = firstQueue.poll();
                originalProcess = new Process(process.getPid(), process.getArrivaltime(), process.getServicetime());
                scheduled.add(originalProcess);
                int ts = Math.min(process.getServicetime(), firstTime);
                originalProcess.setStartAndEnd(time, time + ts);

                time += ts;

                process.setServicetime(process.getServicetime() - firstTime);

                if (process.getServicetime() > 0) {
                    secondQueue.add(process);
                }
            }
            else if (!secondQueue.isEmpty()) {
                process = secondQueue.poll();
                originalProcess = this.getProcessInScheduled(process.getPid());
                int ts = Math.min(process.getServicetime(), secondTime);
                originalProcess.setEndtime(time + ts);
                time += ts;
                process.setServicetime(process.getServicetime() - secondTime);
                if (process.getServicetime() > 0) {
                    thirdQueue.add(process);
                }
            }
            else  if (!thirdQueue.isEmpty()) {
                process = thirdQueue.poll();
                originalProcess = this.getProcessInScheduled(process.getPid());
                int ts = Math.min(process.getServicetime(), thirdTime);
                originalProcess.setEndtime(time + ts);
                time += ts;
                process.setServicetime(process.getServicetime() - thirdTime);
                if (process.getServicetime() > 0) {
                    fourthQueue.add(process);
                }
            }
            else if (!fourthQueue.isEmpty()) {
                process = fourthQueue.poll();
                originalProcess = this.getProcessInScheduled(process.getPid());
                int ts = Math.min(process.getServicetime(), fourthTime);
                originalProcess.setEndtime(time + ts);
                time += ts;
                process.setServicetime(process.getServicetime() - fourthTime);
                if (process.getServicetime() > 0) {
                    fifthQueue.add(process);
                }
            }
            else if (!fifthQueue.isEmpty()) {
                process = fifthQueue.poll();
                originalProcess = this.getProcessInScheduled(process.getPid());
                int ts = Math.min(process.getServicetime(), fifthTime);
                originalProcess.setEndtime(time + ts);
                time += ts;
                process.setServicetime(process.getServicetime() - fifthTime);
                if(process.getServicetime() > 0) {
                    fifthQueue.add(process);
                }
            }

        }
        for (Process p: scheduled) {
            p.setWaittime(p.getEndtime() - p.getServicetime() - p.getArrivaltime());
            p.setTat(p.getWaittime() + p.getServicetime());
            p.setNormtat(p.getTat() / (double) p.getServicetime());
        }
    }
}
