
package Load_Balancer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author anuj
 */
class WorkerLoads {
    private ArrayList<Integer> workerLoads = new ArrayList<>();
    ArrayList<WorkerInfo> workers = new ArrayList<>();
    BufferedReader workerFile;
    WorkerLoads(int workers_size) throws FileNotFoundException, IOException {
        this.workerFile = new BufferedReader(new FileReader(new File("src/Dependency/worker_list.txt")));
        // Initialize loads of all workers to 0
        for (int i = 0; i < workers_size; i++)
            workerLoads.add(0);
           // Populate worker list from worker_list.txt.
        while (workerFile.read() != -1) {
            String[] info = workerFile.readLine().split(",");
            workers.add(new WorkerInfo(info[0], Integer.valueOf(info[1]) ,Integer.valueOf(info[2])));
        }
    }

    int getLoad(int index){
        return workerLoads.get(index);
    }
    double getHealth(int index)
    {
        return 1-(double)workerLoads.get(index)/workers.get(index).getCapacity();
    }

    // Find worker with minimum load.
    synchronized int getMinLoadServer() {
        int minLoad = workerLoads.get(0), min_ind = 0;
        for (int i = 1; i < workerLoads.size(); i++) {
            int thisLoad = workerLoads.get(i);
            if (thisLoad < minLoad) {
                minLoad = thisLoad;
                min_ind = i;
            }
        }
        return min_ind;
    }
    synchronized int getMaxHealthServer() {
        //System.out.println(workers.get(0).getCapacity());
        double maxhealth = 1-(double)workerLoads.get(0)/workers.get(0).getCapacity();
        int min_ind = 0;
        for (int i = 1; i < workerLoads.size(); i++) {
            double thisHealth = 1-(double)workerLoads.get(i)/workers.get(i).getCapacity();
            if (thisHealth>maxhealth) {
                maxhealth= thisHealth;
                min_ind = i;
            }
        }
        return min_ind;
    }

    synchronized void incrementLoad(int index){
        workerLoads.set(index, workerLoads.get(index) + 1);
    }

    synchronized void decrementLoad(int index){
        workerLoads.set(index, workerLoads.get(index) - 1);
    }

}