package edu.cvsu.dcit50.taskmaster;

/**
 *
 * @author rlvillacarlos
 */
public class Task {
    private String name;
    private int startTime = -1;
    private int endTime = -1;
    private int deadline;
    private int executionTime;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartTime() {
        return this.startTime;
    }
    public int getEndTime() {
        return this.endTime;
    }

    public int getDeadline() {
        return this.deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getExecutionTime() {
        return this.executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }
    
    public void execute(int time) {
        this.startTime = time;
        this.endTime = this.startTime + this.executionTime;
    }

    public boolean delayed(){
        return this.startTime == -1 ? false : (this.endTime > this.deadline);
    }
    
}
