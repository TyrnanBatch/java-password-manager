package uk.co.tyrnan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Scanner;

public class masterPassword {
    public static boolean passwordChecker() {
        Scanner sc = new Scanner(System.in);

        try {
            File masterPasswordStore = new File("master-password.txt");
            if (masterPasswordStore.createNewFile()) {
                System.out.println("No Master Password file found.\nPlease input new Master Password: ");

                String input = sc.nextLine();

                Main.filewriter(input, "master-password.txt");
                System.out.println("Successfully created new Master Password.");

                // Delete all files

                return true;

            } else {
                FileInputStream inputStream = new FileInputStream("master-password.txt");
                String masterPassword = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                if (masterPassword.equals("") || masterPassword.equals(" ")) {
                    System.out.println("Master Password file exists but there is no data.\nPlease input new Master Password: ");
                    String newMasterPassword = sc.nextLine();

                    Main.filewriter(newMasterPassword, "master-password.txt");

                } else {
                    System.out.print("Input Master Password: ");
                    for (int i = 0; i < 4; i++) {

                        String input = sc.nextLine();

                        if (Objects.equals(hashing.hash(input), masterPassword)) {
                            return true;
                        }
                        System.out.print("Input Master Password you have " + (3 - i) + " attempts left: ");
                    }
                }
                System.out.println("Three incorrect attempts.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return false;
    }
}

//TODO
// - Create delete file function if master password file is not found.
