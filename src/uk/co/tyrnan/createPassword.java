package uk.co.tyrnan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.stream.Stream;

public class createPassword {
    private final String[] sec;

    public createPassword(String[] sec) {
        this.sec = sec;
    }

    private boolean passwordNameCheck(String input) throws IOException {
        for (int i = 0; i < 15; i++) {
            try (Stream<String> all_lines = Files.lines(Paths.get("password-store.txt"))) {
                String lineCheck = all_lines.skip(i).findFirst().get();
                String[] rawName = lineCheck.split(":");

                if (lineCheck.split(":")[0].equals(hashing.hash(rawName[0]))) {
                    return false;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void createpassword() throws Exception {

        Scanner sc = new Scanner(System.in);

        File passwordStore = new File("password-store.txt");
        if (passwordStore.createNewFile()) {
            System.out.println("");
        }

        int lines = 0;

        String input = sc.nextLine();

        BufferedReader reader = new BufferedReader(new FileReader("password-store.txt"));
        while (reader.readLine() != null) {
            lines++;
        }

        if (passwordNameCheck(input)) {
            String[] commandSplit = this.sec[1].split(":");
            String name = commandSplit[0];
            String password = commandSplit[1];

            String hashedName = hashing.hash(name);
            String encryptedPassword = encrypting.encrypt(password);

            Main.filewriter(hashedName + ":" + encryptedPassword, "password-store.txt");

            System.out.println("Password added");
        } else {
            System.out.println("Already a password saved under this name.");
            String editCheck = ("\nEdit it (Y/N): ");
            if (editCheck.equalsIgnoreCase("y")) {
                // TODO edit function
            }
        }
    }
}

// TODO
//  - a lot
