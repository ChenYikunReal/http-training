import java.io.*;
import java.net.*;

class ClientThread implements Runnable {

    // 该线程负责处理的Socket
    Socket socket;

    // 该线程所处理的Socket所对应的输入流
    BufferedReader bufferedReader = null;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        try {
            String content = null;
            // 不断读取Socket输入流中的内容，并将这些内容打印输出
            while ((content = bufferedReader.readLine()) != null) {
                System.out.println(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

public class MultiThreadClient {

    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1" , 30000);
        // 客户端启动ClientThread线程不断读取来自服务器的数据
        new Thread(new ClientThread(s)).start();
        // 获取该Socket对应的输出流
        PrintStream ps = new PrintStream(s.getOutputStream());
        String line = null;
        // 不断读取键盘输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((line = br.readLine()) != null) {
            // 将用户的键盘输入内容写入Socket对应的输出流
            ps.println(line);
        }
    }

}

