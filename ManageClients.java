import java.net.Socket;
import java.util.HashMap;

public class ManageClients {
    // 管理客户端，用HashMap存储用户IP和线程的键值对
    public static HashMap<String, ServerThread> clients = new HashMap<String, ServerThread>();
    // 用于用户主键和IP之间的键值对
    public static HashMap<Integer, String> ips = new HashMap<Integer, String>();

    // 用HashMap存储用户标号和线程的键值对
    public static HashMap<Integer, Socket> ids = new HashMap<Integer, Socket>();

    public static void addClients(String senderip, ServerThread st) {
        clients.put(senderip, st);
    }

    public static void addIps(Integer i, String ip) {
        ips.put(i, ip);
    }

    public static void addIds(Integer i, Socket s) {
        ids.put(i, s);
    }

}