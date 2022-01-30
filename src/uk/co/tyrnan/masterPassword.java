package uk.co.tyrnan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class masterPassword {
    public static boolean passwordChecker() {
        Scanner sc = new Scanner(System.in);

        System.out.println("loading");

        try {
            File masterPasswordStore = new File("master-password.txt");
            if (masterPasswordStore.createNewFile()) {
                System.out.println("No Master Password file found.");
                System.out.println("Creating new Master Password file");
                System.out.print("Please input new Master Password: ");

                String input = sc.next();

                FileWriter fileWriter = new FileWriter("master-password.txt");
                fileWriter.write(input);
                fileWriter.close();
                System.out.println("Successfully created new Master Password.");

                System.out.println("Deleting all files.");

                // Delete all files

                return true;

            } else {
                FileInputStream inputStream = new FileInputStream("master-password.txt");
                String masterPassword = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                if (masterPassword.equals("") || masterPassword.equals(" ")) {
                    System.out.println("Master Password file exists but there is no data");
                    System.out.println("Please input new Master Password");
                    String newMasterPassword = sc.next();

                    FileWriter fileWriter = new FileWriter("master-password.txt");
                    fileWriter.write(newMasterPassword);
                    fileWriter.close();
                    System.out.println("Successfully created new Master Password.");

                } else {
                    System.out.print("Input Master Password: ");
                    for (int i = 0; i < 4; i++) {

                        String input = sc.next();

                        if (Objects.equals(input, masterPassword)) {
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
        }

        return false;
    }
}

//TODO
// - Create delete file function if master password file is not found.