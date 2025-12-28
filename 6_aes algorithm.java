import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;
import java.util.Scanner;

public class AESUserInput {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        // Take user input
        System.out.print("Enter text to encrypt: ");
        String plaintext = scanner.nextLine();

        // Generate AES key (128-bit)
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        // Initialization Vector (16 bytes)
        byte[] iv = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Encrypt
        Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encryptedBytes = encryptCipher.doFinal(plaintext.getBytes());

        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt
        Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decryptedBytes = decryptCipher.doFinal(
                Base64.getDecoder().decode(encryptedText));

        String decryptedText = new String(decryptedBytes);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
