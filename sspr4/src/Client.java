import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        try (Socket socket = new Socket("172.17.0.2", 5000);  // IP адрес сервера
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(matrix);
            double[][] result = (double[][]) in.readObject();

            System.out.println("\nProcessed Matrix:");
            for (double[] row : result) {
                for (double val : row) {
                    System.out.printf("%.2f ", val);
                }
                System.out.println();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
