package simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * communication through sockets
 * buffered transmitting
 */
public class ProdAndConsSlow {

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread client = new Thread(() -> {
            System.out.println("client wait ...");
            try (Socket socket = new Socket("localhost",8888)){
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                byte[] write =new byte[]{1,2,3};
                os.write(write);
                System.out.println("client wrote bytes = " + Arrays.toString(write));
                byte[] read =new byte[3];
                int count = is.read(read);
                System.out.println("client read count = " + count);
                System.out.println("client read bytes = " + Arrays.toString(read));
                for(int i = 0;;i++){
                    System.out.println("client write " + i + "kb");
                    os.write(new byte[1024]);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread server = new Thread(() -> {
            try {
                System.out.println("server wait ...");
                try (ServerSocket serverSocket = new ServerSocket(8888);
                        Socket socket = serverSocket.accept()){
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    byte[] read =new byte[3];
                    int count = is.read(read);
                    System.out.println("server read count = " + count);
                    System.out.println("server read bytes = " + Arrays.toString(read));
                    byte[] write =new byte[]{2,3,4};
                    os.write(write);
                    System.out.println("server wrote bytes = " + Arrays.toString(write));
                    System.out.println("Server slow");
                    int i = 0;
                    while (true){
                        read =new byte[1024];
                        count = is.read(read);
                        i++;
                        System.out.println("server read count = " + count);
                        System.out.println("server read "+i+"kb");
                        Thread.sleep(100);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        server.start();
        Thread.sleep(200);
        client.start();


    }

}
