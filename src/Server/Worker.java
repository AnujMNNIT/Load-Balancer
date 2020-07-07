
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author nikita
 */


public class Worker {
    public static void main(String args[]) {
        try {
             Connection conn=Database.getConnection();
            // Open socket for this worker.
            ServerSocket workerSocket = new ServerSocket(Integer.valueOf(args[0]));
            ExecutorService executor = Executors.newFixedThreadPool(15);//creating a pool of 15 threads  
            while(true){
                // Accept connection from Load Balancer.
                Socket loadBalancerSocket = workerSocket.accept();
                // Start a new thread to service this request.
                 
                System.out.println("New Thread Starting...........");
                Runnable workerTask = new WorkerTask2(loadBalancerSocket);
                //Runnable workerTask = new WorkerTask(loadBalancerSocket, conn);
                executor.execute(workerTask);//calling execute method of ExecutorService  
                
                //Thread workerTask = new Thread(new WorkerTask(loadBalancerSocket, conn));
                //Thread workerTask = new Thread((Runnable) new WorkerTask2(loadBalancerSocket));
                //workerTask.start();
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

