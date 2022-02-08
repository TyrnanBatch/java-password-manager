package uk.co.tyrnan;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void filewriter(String input, String location) throws IOException {
        FileWriter fileWriter = new FileWriter(location);
        fileWriter.write(input);
        fileWriter.close();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Scanner sc = new Scanner(System.in);

        if (!masterPassword.passwordChecker()) {
            System.out.println("Password check failed");
            System.exit(0);
        }

        boolean running = true;

        while (running) {

            System.out.print("Command: ");

            String command = sc.nextLine();
            String[] sec = command.split(" ");

            if (Objects.equals(sec[0], "add")) {
                createPassword cp = new createPassword(sec);
                cp.createpassword();
            }
        }
    }
}
