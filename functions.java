
//מתן שרקון 209542448
//ליעד מזרחי 318518677
package hafala3.src;

import java.math.BigInteger;
import java.util.*;

public class functions {
    List<Process> processes;

    public functions(List<Process> p) {
        processes = new ArrayList<Process>();
        processes = p;
    }

    public double FCFS() {
        for(Process p:processes) {
            p.remainingTime = p.computationTime;
            p.terminated=false;
        }
        List<Process> p = new LinkedList<>(processes);
        p.get(0).
                setCompilationTime(p.get(0).getArrivalTime() + p.get(0).getComputationTime());
        p.get(0).
                setTurnAroundTime(p.get(0).getCompilationTime() - p.get(0).getArrivalTime());
        p.get(0).
                terminated = true;

        for (int i = 1; i < p.size() && !p.get(i).terminated; i++) {
            if (p.get(i).getArrivalTime() > p.get(i - 1).getCompilationTime()) {
                p.get(i).
                        setCompilationTime(p.get(i).getComputationTime() + p.get(i).getArrivalTime());
                p.get(i).
                        setTurnAroundTime(p.get(i).getCompilationTime() - p.get(i).getArrivalTime());
                p.get(i).
                        terminated = true;
            } else {
                p.get(i).
                        setCompilationTime(p.get(i - 1).getCompilationTime() + p.get(i).getComputationTime());
                p.get(i).
                        setTurnAroundTime(p.get(i).getCompilationTime() - p.get(i).getArrivalTime());
                p.get(i).
                        terminated = true;
            }
        }
        double avg = p.stream().mapToDouble(pro -> pro.getTurnAroundTime()).average().getAsDouble();

        return avg;
    }

    public double LCFSNP() {
        for(Process p:processes) {
            p.remainingTime = p.computationTime;
            p.terminated=false;
        }

        LinkedList<Process> p=new LinkedList<>();
        p.addAll(processes);
        LinkedList<Process> towork = new LinkedList<>();
        int index=0;
        int j=0;
        int totaltime=p.get(0).arrivalTime;
        boolean flag;

        while (true) {
            for (int i = 0; i < p.get(index).computationTime; i++) {

                for(j=index+1;j<p.size();j++){
                    if(p.get(j).arrivalTime<=totaltime&&p.get(j).terminated==false) {
                        towork.addFirst(p.get(j));
                        p.remove(j);

                    }
                }
                p.get(index).remainingTime--;
                totaltime++;
            }
            p.get(index).terminated=true;
            p.get(index).turnAroundTime=totaltime-p.get(index).arrivalTime;
            if(!towork.isEmpty()){
                for(int k=0;k<towork.size();k++){
                    totaltime+=towork.get(k).computationTime;
                    towork.get(k).turnAroundTime=totaltime-towork.get(k).arrivalTime;
                    towork.get(k).terminated=true;
                    p.addLast(towork.get(k));
                }
            }
            flag=true;
            for(Process pro:p){
                if(pro.terminated==false) {
                    flag = false;
                    break;
                }
            }
            if(flag==true)
                break;
            index++;
            totaltime=p.get(index).arrivalTime;
        }
        double avg = p.stream().mapToDouble(pro -> pro.getTurnAroundTime()).average().getAsDouble();
        return avg;
    }

    public double LCFSP() {
        for(Process p:processes) {
            p.remainingTime = p.computationTime;
            p.terminated=false;
        }
        LinkedList<Process> done = new LinkedList<>();
        List<Process> p=new LinkedList<>();
        for(int i=0;i< processes.size();i++){
           if(processes.get(i).computationTime>0)
               p.add(processes.get(i));
           else {
               processes.get(i).turnAroundTime=0;
               done.add(processes.get(i));
           }
       }
        LinkedList<Process> remain_process = new LinkedList<>();
        remain_process.addFirst(p.get(0));
        int i = 0;
        int j;
        int currenttime = p.get(0).arrivalTime;

        do {
            for (j = 0; j < remain_process.getFirst().remainingTime; j++) {
                if (i < p.size() - 1) {
                    if (p.get(i + 1).arrivalTime <= currenttime) {
//                        remain_process.getFirst().remainingTime = p.get(i).remainingTime;
                        remain_process.addFirst(p.get(i + 1));
                        i++;
                        break;
                    }
                }
                currenttime++;
                remain_process.getFirst().remainingTime--;

                if (remain_process.getFirst().remainingTime == 0) {
                    remain_process.getFirst().setCompilationTime(currenttime);
                    remain_process.getFirst().setTurnAroundTime(currenttime - remain_process.getFirst().arrivalTime);
                    done.add(remain_process.getFirst());
                    remain_process.removeFirst();
                    if (remain_process.isEmpty())
                        break;
                }
            }
            if (remain_process.isEmpty()) {
                if (i == p.size() - 1)
                    break;
                else {
                    i++;
                    remain_process.add(p.get(i));
                    currenttime = p.get(i).arrivalTime;
                }
            }



        } while (true);

        double avg = done.stream().mapToDouble(pro -> pro.getTurnAroundTime()).average().getAsDouble();
        return avg;


    }

    public double RR() {

        for(Process p:processes) {
            p.remainingTime = p.computationTime;
            p.terminated=false;
        }
        LinkedList<Process> done = new LinkedList<>();
        List<Process> p=new LinkedList<>();
        for(int i=0;i< processes.size();i++){
            if(processes.get(i).computationTime>0)
                p.add(processes.get(i));
            else {
                processes.get(i).turnAroundTime=0;
                done.add(processes.get(i));
            }
        }
        int i = 0;
        int totaltime = p.get(0).arrivalTime;
        int count = 0;
        int size = p.size();
        ;
        while (true) {

            for (int j = 0; j < 2; j++) {
                if (p.get(i % size).remainingTime == 1) {
                    totaltime++;
                    p.get(i % size).remainingTime--;
                    break;
                }
                p.get(i % size).remainingTime--;
                totaltime++;
            }
            if (p.get(i % size).remainingTime == 0) {
                p.get(i % size).terminated = true;
                p.get(i % size).compilationTime = totaltime;
                p.get(i % size).turnAroundTime = totaltime - p.get(i % size).arrivalTime;
                done.add(p.get(i % size));
            }
            while (count <= p.size()) {
                if (p.get((i + 1) % size).arrivalTime <= totaltime && p.get((i + 1) % size).terminated == false) {
                    i++;
                    count = 0;
                    break;
                } else {
                    i++;
                    count++;
                }
            }
            int j = 0;
            boolean flag = false;
            if (count > p.size()) {
                for (Process pro : p) {
                    if (pro.isTerminated() == true)
                        j++;
                }
                if (j >= p.size())
                    break;
                else {
                    j = 0;
                    while (true) {
                        for (j = 0; j < p.size(); j++) {
                            if (p.get(j).arrivalTime <= totaltime) {
                                flag = true;
                                i = j;
                            }
                        }
                        if (flag == true)
                            break;
                        else totaltime++;
                    }

                }

            }

        }
        double avg = done.stream().mapToDouble(pro -> pro.getTurnAroundTime()).average().getAsDouble();
        return avg;

    }

    public double SJF() {
        List<Process> remain = new LinkedList<>();
        for(Process p:processes) {
            p.remainingTime = p.computationTime;
            p.terminated=false;
        }
        LinkedList<Process> done = new LinkedList<>();
        List<Process> p=new LinkedList<>();
        for(int i=0;i< processes.size();i++){
            if(processes.get(i).computationTime>0)
                p.add(processes.get(i));
            else {
                processes.get(i).turnAroundTime=0;
                done.add(processes.get(i));
            }
        }
        int i = 0;
        int j = 0;
        int totaltime = p.get(0).arrivalTime;
        int min;
        int counter = 0;
        min = p.get(j).remainingTime;



        while (true) {
            for (i = 0; i < p.size(); i++) {
                if (p.get(i).remainingTime <= min && p.get(i).arrivalTime <= totaltime && p.get(i).terminated == false) {
                    j = i;
                    min = p.get(i).remainingTime;
                }
            }
            if (p.get(j).terminated == false) {
                p.get(j).remainingTime--;
                min = p.get(j).remainingTime;
            }
            totaltime++;

            if (p.get(j).remainingTime == 0&&p.get(j).terminated==false) {
                p.get(j).turnAroundTime=totaltime-p.get(j).arrivalTime;
                p.get(j).terminated = true;
                done.add(p.get(j));
                min=534;
            }
            for (Process pro : p) {
                if (pro.terminated == false) {
                    counter=0;
                    break;
                }
                counter++;
            }
            if (counter == p.size())
                break;

        }

        double avg = done.stream().mapToDouble(pro -> pro.getTurnAroundTime()).average().getAsDouble();
        return avg;
    }

}