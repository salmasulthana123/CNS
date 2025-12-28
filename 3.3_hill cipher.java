import java.io.*;
import java.util.*;

public class HillCipher {
    static float[][] decrypt = new float[3][1];
    static float[][] a = new float[3][3];
    static float[][] b = new float[3][3];
    static float[][] mes = new float[3][1];
    static float[][] res = new float[3][1];

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        getKeyMes();

        // Encrypting the message
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                res[i][j] = 0; // Initialize to 0
                for (int k = 0; k < 3; k++) {
                    res[i][j] += a[i][k] * mes[k][j];
                }
                res[i][j] %= 26; // Apply modulo 26
            }
        }

        System.out.print("\nEncrypted string is: ");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) (res[i][0] + 97));
        }

        // Finding the inverse matrix
        if (!inverse()) {
            System.out.println("The key matrix is not invertible. Exiting.");
            return;
        }

        // Decrypting the message
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                decrypt[i][j] = 0; // Initialize to 0
                for (int k = 0; k < 3; k++) {
                    decrypt[i][j] += b[i][k] * res[k][j];
                }
                decrypt[i][j] %= 26; // Apply modulo 26
                if (decrypt[i][j] < 0) decrypt[i][j] += 26; // Handle negative values
            }
        }

        System.out.print("\nDecrypted string is: ");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) (decrypt[i][0] + 97));
        }
        System.out.print("\n");
    }

    public static void getKeyMes() throws IOException {
        System.out.println("Enter 3x3 matrix for key (It should be invertible):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = sc.nextFloat();
            }
        }

        System.out.print("\nEnter a 3-letter string: ");
        String msg = br.readLine();
        for (int i = 0; i < 3; i++) {
            mes[i][0] = msg.charAt(i) - 97;
        }
    }

    public static boolean inverse() {
        float determinant = 0;
        for (int i = 0; i < 3; i++) {
            determinant += a[0][i] * (a[1][(i + 1) % 3] * a[2][(i + 2) % 3] - a[1][(i + 2) % 3] * a[2][(i + 1) % 3]);
        }

        determinant = determinant % 26;
        if (determinant < 0) determinant += 26; // Ensure positive determinant

        int detInverse = modularInverse((int) determinant, 26);
        if (detInverse == -1) return false; // Not invertible

        // Calculate the adjoint and inverse matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b[j][i] = ((a[(i + 1) % 3][(j + 1) % 3] * a[(i + 2) % 3][(j + 2) % 3]) -
                          (a[(i + 1) % 3][(j + 2) % 3] * a[(i + 2) % 3][(j + 1) % 3])) % 26;
                if (b[j][i] < 0) b[j][i] += 26;
                b[j][i] = (b[j][i] * detInverse) % 26;
                if (b[j][i] < 0) b[j][i] += 26; // Handle negative values
            }
        }

        System.out.println("\nInverse Matrix is:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.print("\n");
        }
        return true;
    }

    public static int modularInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1;
    }
}


