public class Process {
    private final int pid;
    private final int arrivaltime;
    private final int servicetime;
    private int starttime;
    private int endtime;
    private int tat;
    private double normtat;
    private int waittime;

    public Process(int pid, int arrivaltime, int servicetime) {
        this.pid = pid;
        this.arrivaltime = arrivaltime;
        this.servicetime = servicetime;
    }

    public int getPid() {
        return pid;
    }

    public int getArrivaltime() {
        return arrivaltime;
    }

    public int getServicetime() {
        return servicetime;
    }

    public void setStartAndEnd(int starttime, int endtime) {
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public int getTat() {
        return tat;
    }

    public void setTat(int tat) {
        this.tat = tat;
    }

    public double getNormtat() {
        return normtat;
    }

    public void setNormtat(double normtat) {
        this.normtat = normtat;
    }

    public int getWaittime() {
        return waittime;
    }

    public void setWaittime(int waittime) {
        this.waittime = waittime;
    }
}
