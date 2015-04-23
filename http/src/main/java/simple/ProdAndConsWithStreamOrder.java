package simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * communication through sockets
 */
public class ProdAndConsWithStreamOrder {

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread client = new Thread(() -> {
            System.out.println("client wait ...");
            try (Socket socket = new Socket("localhost", 8888)) {
                try (OutputStream os = socket.getOutputStream();
                     InputStream is = socket.getInputStream();

                ) {
                    byte[] write = new byte[]{1, 2, 3};
                    os.write(write);
                    System.out.println("client wrote bytes = " + Arrays.toString(write));
                    byte[] read = new byte[3];
                    int count = is.read(read);
                    System.out.println("client read count = " + count);
                    System.out.println("client read bytes = " + Arrays.toString(read));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread server = new Thread(() -> {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8888);
                System.out.println("server wait ...");
                try (Socket socket = serverSocket.accept()) {
                    try (
                            InputStream is = socket.getInputStream();
                         OutputStream os = socket.getOutputStream();
                         ){
                        byte[] read = new byte[3];
                        int count = is.read(read);
                        System.out.println("server read count = " + count);
                        System.out.println("server read bytes = " + Arrays.toString(read));
                        byte[] write = new byte[]{2, 3, 4};
                        os.write(write);
                        System.out.println("server wrote bytes = " + Arrays.toString(write));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        server.start();
        client.start();


    }

}
