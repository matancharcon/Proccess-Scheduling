//מתן שרקון 209542448
//ליעד מזרחי 318518677
package hafala3.src;

public class Process {

    int arrivalTime;
    int computationTime;
    int compilationTime;
    int turnAroundTime;
    int remainingTime;
    boolean terminated;

    public Process(int arrivalTime, int computationTime){
        setArrivalTime(arrivalTime);
        setComputationTime(computationTime);
        setCompilationTime(0);
        setTurnAroundTime(0);
        setRemainingTime(computationTime);
        setTerminated(false);
    }
    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getComputationTime() {
        return computationTime;
    }

    public void setComputationTime(int computationTime) {
        this.computationTime = computationTime;
    }

    public int getCompilationTime() {
        return compilationTime;
    }

    public void setCompilationTime(int compilationTime) {
        this.compilationTime = compilationTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public boolean isTerminated() {
        return terminated;
    }


    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}

