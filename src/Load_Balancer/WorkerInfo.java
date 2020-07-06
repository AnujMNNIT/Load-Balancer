
package Load_Balancer;
/**
 *
 * @author anuj
 */
public class WorkerInfo
{
    private int port;
    private String host;
    private int capacity;
    WorkerInfo(String host, int port,int capacity)
    {
        this.host = host;
        this.port = port;
        this.capacity=capacity;
    }
    String getHost()
    {
        return host;
    }
    int getPort()
    {
        return port;
    }
    int getCapacity()
    {
       
        return capacity;
    }
}
