
package miniproject;

import java.net.Socket;

/**
 *
 * @author anuj
 */
public class Client {

    public static void main(String[] args) {
        try {
            while (true){
           
                // Open connection to Load Balancer.
                Socket loadBalancerSocket = new Socket("localhost", 12345);
                // Start a new thread to send request.
                Thread requestClient = new Thread(new RequestClient(loadBalancerSocket));
                //Thread requestClient = new Thread(new RequestClient2(loadBalancerSocket));
                requestClient.start();
                // To clearly observe print statements on Client, Load Balancer and Workers:
                //Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
