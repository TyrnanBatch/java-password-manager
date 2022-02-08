package uk.co.tyrnan;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Scanner;
import java.util.stream.Stream;

public class createPassword {
    private final String[] sec;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public createPassword(String[] sec) {
        this.sec = sec;

        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (Exception ignored) {
        }
    }

    public String encrypt(String message) throws Exception {
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedMessage) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
        return new String(decryptedMessage, StandardCharsets.UTF_8);
    }

    public static boolean passwordNameCheck(String input) throws IOException {
        for (int i = 0; i < 15; i++) {
            try (Stream<String> all_lines = Files.lines(Paths.get("password-store.txt"))) {
                String lineCheck = all_lines.skip(i).findFirst().get();
                String[] passwordName = lineCheck.split(":");

                if (passwordName[0].equals(input.split(":")[0])) {
                    System.out.println("You already have a password saved under that name.");
                    return false;
                }
            }
        }
    }

    public void createpassword() throws IOException {

        Scanner sc = new Scanner(System.in);

        File passwordStore = new File("password-store.txt");
        if (passwordStore.createNewFile()) {
            System.out.println("");
        }

        String password = this.sec[1];

        int lines = 0;

        String input = sc.nextLine();

        BufferedReader reader = new BufferedReader(new FileReader("password-store.txt"));
        while (reader.readLine() != null) {
            lines++;
        }

        if (passwordNameCheck(input)) {
            // tbc
        }

    }
}

// TODO
//  - a lot