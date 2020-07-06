
package Client;

import Client.RequestClient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author abdul
 */
class RequestClient2 implements Runnable{
    private Socket loadBalancerSocket;
    RequestClient2(Socket loadBalancerSocket){
        this.loadBalancerSocket = loadBalancerSocket;
    }
    @Override
    public void run() {
       
        try {
            BufferedWriter lbWriter = new BufferedWriter(new OutputStreamWriter(loadBalancerSocket.getOutputStream(), StandardCharsets.UTF_8));
            BufferedReader lbReader = new BufferedReader(new InputStreamReader(loadBalancerSocket.getInputStream(), StandardCharsets.UTF_8));
           
            // Get a random Student ID in range [1, 7](The number of rows).
            int sid = new Random().nextInt(7) + 1;

            // Send to Load Balancer.
            lbWriter.write(sid + "\n");
            lbWriter.flush();

            // Get worker's response, sent via Load Balancer.
            String jsonString = lbReader.readLine();
            JSONObject json = new JSONObject(jsonString);
            String result = "Information received for Student with ID="+sid+":"+
                            "\nName: "+json.getString("name")+
                            "\nDate of Birth: "+json.getString("dob")+
                            "\nMajor of Study: "+json.getString("major")+
                            "\nEducation Level: "+json.getString("level")+
                            "\nYear of Study: "+json.getString("year");
            System.out.println(result+"\n\n");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException ex) {
            Logger.getLogger(RequestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
                
              
    }
}
