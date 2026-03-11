//Bibliotecas:
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AES256Vault {
    private static final String CHAVE = "sua_chave_com_exato_32_caractere"; //32 caracteres

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("||| Cyber Vault AES-256 |||");
        System.out.println("1. Criptografar | 2. Descriptografar");

        int escolha = scanner.nextInt();
        scanner.nextLine(); //Limpa buffer

        System.out.print("Texto: ");
        String text = scanner.nextLine();

        if (escolha == 1) {
            System.out.println("Resultado: " + encrypt(text, CHAVE));
        } else {
            System.out.println("Resultado: " + decrypt(text, CHAVE));
        }
    }

    public static String encrypt(String strToEncrypt, String secret) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }

    public static String decrypt(String strToDecrypt, String secret) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
}
