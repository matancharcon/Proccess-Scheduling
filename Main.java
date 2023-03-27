//מתן שרקון 209542448
//ליעד מזרחי 318518677
package hafala3.src;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = 1;
        while (n < 6) {
            List <Process> processes=new ArrayList<>();
            BufferedReader bf = new BufferedReader(new FileReader("input" + n + ".txt"));
            int size =  Integer.parseInt(bf.readLine());

            String [][] s =new String [size][2];
            for (int i = 0; i < size ; i++) {
                s[i]=bf.readLine().split(",");
                processes.add(new Process(Integer.parseInt(s[i][0]),Integer.parseInt(s[i][1])));
            }

            List<Process> sortedbyArrivalTime=
                    processes.stream().sorted(Comparator.comparing(Process::getArrivalTime)).collect(Collectors.toList());


            System.out.println("File number " + n + " output");
            functions f1 =new functions(sortedbyArrivalTime);
            System.out.println("FCFS : " + f1.FCFS());
            functions f2 =new functions(sortedbyArrivalTime);
            System.out.println("LCFSNP : " + f2.LCFSNP());
            functions f3 =new functions(sortedbyArrivalTime);
            System.out.println("LCFSP : " + f3.LCFSP());
            functions f4 =new functions(sortedbyArrivalTime);
            System.out.println("RR : " + f4.RR());
            functions f5 =new functions(sortedbyArrivalTime);
            System.out.println("SJF : " + f5.SJF());


            n++;
        }
    System.out.println("done");
    }

}