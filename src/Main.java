import java.util.Scanner;

public class Main {
    static boolean option = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("""
                    If you are a hospital administrator please press 1
                    If you are a patient please press 2
                    Press 3 to exit""");
            Controller.checkInputType(scanner);
            int user_input1 = scanner.nextInt();
            menu(scanner, user_input1);

        } while (option);
    }

    public static void menu(Scanner scanner, int user_input) {

        switch (user_input) {
            case 1:
                doctor_menu(scanner);
                break;
            case 2:
                patient_menu(scanner);
                break;
            case 3:
                option = false;
                break;
            default:
                System.out.println("Invalid Option Selected\n");
        }

    }

    public static void doctor_menu(Scanner scanner) {
        System.out.println("""
                Press 1 to add a doctor
                press 2 to add a doctor availability
                press 3 to exit""");

        Controller.checkInputType(scanner); //Check whether the input is an integer
        int user_input2 = scanner.nextInt();

        while (user_input2 != 3) {

            if (user_input2 == 1) {
                System.out.println("add a doctor\n");
                Controller.add_doctor(scanner);
            } else if (user_input2 == 2) {
                Controller.add_doctor_availability(scanner);
            }
            else {
                System.out.println("Invalid Option Selected\n");
            }
            System.out.println("""
                    Press 1 to add a doctor
                    press 2 to add a doctor availability
                    press 3 to exit""");
            user_input2 = scanner.nextInt();
        }
    }

    public static void patient_menu(Scanner scanner) {

        String message = """
                Press 1 to view doctors
                press 2 to book an appointment
                press 3 to view a selected doctor's bookings
                press 4 to register patient
                press 5 to exit""";

        System.out.println(message);

        Controller.checkInputType(scanner); //Check whether the input is an Integer

        int user_input2 = scanner.nextInt();

        while (user_input2 != 5) {
            if (user_input2 == 1) {
                Controller.viewDoctors();
                System.out.println(message);
                user_input2 = scanner.nextInt();
            }

            else if (user_input2 == 2) {
                Controller.makeAppointment(scanner);
                System.out.println(message);
                user_input2 = scanner.nextInt();
            }

            else if (user_input2 == 3) {
                Controller.viewBookings(scanner);
                System.out.println(message);
                user_input2 = scanner.nextInt();

            }

            else if (user_input2 == 4) {
                Controller.add_patient(scanner);
                System.out.println(message);
                user_input2 = scanner.nextInt();

            }

            else
                System.out.println("Invalid Option Selected\n");
        }
    }


}
