import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is running...");

            try (Socket socket = serverSocket.accept();
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                int[][] matrix = (int[][]) in.readObject();
                int max = Integer.MIN_VALUE;
                for (int[] row : matrix) {
                    for (int val : row) {
                        max = Math.max(max, val);
                    }
                }

                double[][] result = new double[matrix.length][matrix[0].length];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        result[i][j] = (double) matrix[i][j] / max;
                    }
                }

                out.writeObject(result);
                System.out.println("Matrix processed and sent back.");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
