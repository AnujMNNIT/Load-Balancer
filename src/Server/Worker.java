
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
/**
 *
 * @author nikita
 */


public class Worker {
    public static void main(String args[]) {
        try {
             Connection conn=Database.getConnection();
            // Open socket for this worker.
            ServerSocket workerSocket = new ServerSocket(20006);
           // System.out.println("port"+port);
            while(true){
                // Accept connection from Load Balancer.
                Socket loadBalancerSocket = workerSocket.accept();
                // Start a new thread to service this request.
                
                System.out.println("New Thread Starting...........");
                //Thread workerTask = new Thread(new WorkerTask(loadBalancerSocket, conn));
                Thread workerTask = new Thread((Runnable) new WorkerTask2(loadBalancerSocket));
                workerTask.start();
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

