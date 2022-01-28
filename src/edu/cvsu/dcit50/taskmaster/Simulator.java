package edu.cvsu.dcit50.taskmaster;

import java.util.Scanner;

/**
 *
 * @author rlvillacarlos
 */
public class Simulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initialize();
    }
    
    private static void initialize(){
        Scanner in = new Scanner(System.in);
        
        System.out.print("Number of tasks: ");
        int taskCount = in.nextInt();
        in.nextLine();
        System.out.println();
        
        //Array of tasks
        Task tasks[] = new Task[taskCount];
                
        for(int i = 0; i < taskCount; i++){
            //Create new task and set name, execution time, deadline
            tasks[i] = new Task();
            System.out.printf("Task %d: ", i+1);
            tasks[i].setName(in.nextLine());
            
            System.out.print("Execution: ");
            tasks[i].setExecutionTime(in.nextInt());
            in.nextLine();
            
            System.out.print("Deadline: ");
            tasks[i].setDeadline(in.nextInt());
            in.nextLine();
            System.out.println("");
        }
        
        //Run simulator at time 0
        simulate(tasks, 0);
        //Output report
        showReport(tasks);
    }
    
    private static void simulate(Task tasks[], int startTime){
        int currentTime = startTime;
        
        for(Task task:tasks){
            //Execute the task with the given time
            task.execute(currentTime);
            
            //Update the current time to be the end time of the current task
            currentTime = task.getEndTime();            
        }
    }
    
    
    private static void showReport(Task tasks[]){
        System.out.println("Tasks\tStart\tEnd\tDeadline\tMissed");
        
        for(Task task:tasks){
            System.out.printf("%s\t%d\t%d\t%d\t\t%s%n", 
                task.getName(), task.getStartTime(), task.getEndTime(),
                task.getDeadline(), task.delayed()? "Yes":"No");
        }
        
    }
}
