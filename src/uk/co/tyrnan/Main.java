package uk.co.tyrnan;

public class Main {

    public static void main(String[] args) {
        if (!masterPassword.passwordChecker()) {
            System.out.println("Password check failed");
            System.exit(0);
        }

        System.out.println("YOUR IN");
    }
}