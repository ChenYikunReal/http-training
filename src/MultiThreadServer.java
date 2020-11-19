import java.net.*;
import java.io.*;
import java.util.*;

class ServerThread implements Runnable {

    // 定义当前线程所处理的Socket
    Socket socket = null;

    // 该线程所处理的Socket所对应的输入流
    BufferedReader bufferedReader = null;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        // 初始化该Socket对应的输入流
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        try {
            String content = null;
            // 采用循环不断从Socket中读取客户端发送过来的数据
            while ((content = readFromClient()) != null) {
                // 遍历socketList中的每个Socket，将读到的内容向每个Socket发送一次
                for (Socket s : MultiThreadServer.socketList) {
                    PrintStream ps = new PrintStream(s.getOutputStream());
                    ps.println(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 定义读取客户端数据的方法
    private String readFromClient() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {   // 如果捕捉到异常，表明该Socket对应的客户端已经关闭
            // 删除该Socket
            MultiThreadServer.socketList.remove(socket);
        }
        return null;
    }
}

public class MultiThreadServer {

    // 定义保存所有Socket的ArrayList，并将其包装为线程安全的
    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(30000);
        while(true) {
            // 此行代码会阻塞，将一直等待别人的连接
            Socket s = socket.accept();
            socketList.add(s);
            // 每当客户端连接后启动一条ServerThread线程为该客户端服务
            new Thread(new ServerThread(s)).start();
        }
    }

}
