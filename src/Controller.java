import java.util.*;

public class Controller {
    public static ArrayList<Doctor> allDoctors = new ArrayList<>();
    public static ArrayList<Patient> patientList = new ArrayList<>();
    public static int NUMBER_OF_SLOTS = 5;

    public static void add_doctor(Scanner scanner) {

        //get details of doctor
        System.out.println("Enter your Name: ");
        String name = scanner.next();
        System.out.println("Enter your birthday: ");
        String birthday = scanner.next();
        System.out.println("Enter your Specialization: ");
        String specialization = scanner.next();
        System.out.println("Enter your Contact: ");
        String contact = scanner.next();

        //generate a random number for doctor ID
        Random random = new Random();

        //create new doctor object
        Doctor tempDoctor = new Doctor(random.nextInt((10000) + 1), name, birthday, specialization, contact);
        allDoctors.add(tempDoctor);
    }

    public static void add_doctor_availability(Scanner scanner) {
        Doctor selectedDoctor = null;

        System.out.println("Enter doctor ID to add availability: ");
        checkInputType(scanner);
        int docID = scanner.nextInt();

        //loop through the arraylist to fetch the doctor
        for (Doctor doctor : Controller.allDoctors) {
            if (doctor.getDoctorId() == docID) {
                selectedDoctor = doctor;
            }

        }
        //If doctor is not found, exit the method
        if (selectedDoctor == null) {
            System.out.println("\nInvalid ID\n");
            return;
        }

        //If doctor exists, get the date
        System.out.println("Enter the Day you want to add availability: ");
        int day = scanner.nextInt();

        System.out.println("Enter the Month you want to add availability: ");
        int month = scanner.nextInt();

        System.out.println("Enter the Year you want to add availability: ");
        int year = scanner.nextInt();

        Date bookingDate = new Date(year, month, day);
        selectedDoctor.setAvailability(bookingDate);
    }

    public static void viewDoctors() {
        if (allDoctors.isEmpty()) {
            System.out.println("No Doctors Available\n");
            return;
        }
        for (Doctor doc : Controller.allDoctors) {
            System.out.println(doc.getName() + " has a specialization of " + doc.getSpecialization() + "\nId: " + doc.getDoctorId() + "\nAvailabilities: " + doc.getAvailabilities() + "\n");
        }
    }

    public static void add_patient(Scanner scanner) {

        //get details of patients
        System.out.println("Enter your name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.println("Enter your ID: ");
        String patientID = scanner.next();

        System.out.println("Enter your contact number: ");
        String contactNum = scanner.next();

        Patient patient = new Patient(patientID, name, contactNum);
        patientList.add(patient);
        System.out.println("Registration Successful\n");

    }

    public static void makeAppointment(Scanner scanner) {
        System.out.println("Enter 'G' for General Appointment or Enter 'R' for Referral Appointment");
        String appointmentType = scanner.next().toUpperCase();

        //Validating the input
        if (!(appointmentType.charAt(0) == 'G' || appointmentType.charAt(0) == 'R')) {
            System.out.println("Invalid Input\n");
            return;
        }

        System.out.println("Enter the Doctor ID: ");
        int doctorID = scanner.nextInt();

        System.out.println("Enter the Day: ");
        int day = scanner.nextInt();

        System.out.println("Enter the Month: ");
        int month = scanner.nextInt();

        System.out.println("Enter the Year: ");
        int year = scanner.nextInt();

        System.out.println("Enter any notes you want to add(Enter 'no' if there's none): ");
        String notes = scanner.next();

        System.out.println("Enter the Patient ID: ");
        String patientID = scanner.next();

        Patient selectedPatient = getPatient(patientID);
        Doctor selectedDoctor = getDoctor(doctorID);

        //Validating user inputs for doctor ID and patient ID
        if (selectedPatient == null || selectedDoctor == null) {
            System.out.println("Invalid Doctor/Patient ID\n");
            return;
        }

        Date appointmentDate = new Date(year, month, day);

        //check if doctor is available on given date
        boolean doctorAvailable = checkAvailability(selectedDoctor, appointmentDate);
        Appointment appointment;

        if (doctorAvailable) {
            String appointmentTime = getTimeForBooking(selectedDoctor, appointmentDate);

            if (appointmentTime != null) {
                if (appointmentType.charAt(0) == 'R') {
                    System.out.println("Enter the Doctor ID of Referred Doctor: ");
                    int referredDocID = scanner.nextInt();

                    Doctor referedDoctor = getDoctor(referredDocID);
                    if (referedDoctor == null) {
                        System.out.println("Invalid ID");
                        return;
                    }

                    System.out.println("Enter referral doctor notes: ");
                    String referralDoctorNotes = scanner.next();

                    appointment = new ReferralAppointments(selectedDoctor, selectedPatient, appointmentDate, "", referedDoctor, notes, referralDoctorNotes);

                } else
                    appointment = new GeneralAppointments(selectedDoctor, selectedPatient, appointmentDate, "", notes);

                selectedDoctor.setAppointment(appointment, appointmentDate);

                for (Map.Entry<Date, ArrayList<Appointment>> app : selectedDoctor.getAllAppointments().entrySet()) {
                    System.out.println(appointmentDate + " - " + app.getValue().toString());

                }
            } else {
                System.out.println("All slots are filled");
            }

        } else {
            System.out.println("Doctor is not available on " + appointmentDate + "\n");
        }
    }

    public static Patient getPatient(String patientID) {
        for (Patient patient : patientList) {
            if (patient.getPatientId().equals(patientID)) {
                return patient;
            }
        }
        return null;
    }

    public static Doctor getDoctor(int doctorID) {
        for (Doctor doctor : allDoctors) {
            if (doctor.getDoctorId() == doctorID) {
                return doctor;
            }
        }
        return null;
    }

    //check the availability
    private static boolean checkAvailability(Doctor selectedDoctor, Date bookingDate) {
        for (Date day : selectedDoctor.getAvailabilities()) {
            if (day.equals(bookingDate)) {
                return true;
            }
        }
        return false;
    }

    //check for Number of slots
    private static String getTimeForBooking(Doctor selectedDoctor, Date bookingDate) {
        for (Map.Entry<Date, ArrayList<Appointment>> appointment : selectedDoctor.getAllAppointments().entrySet()) {
            if (appointment.getKey().equals(bookingDate)) {
                int numberOfSlots = appointment.getValue().size();
                if (numberOfSlots > NUMBER_OF_SLOTS - 1) {
                    return null;
                }
                System.out.println("Appointment Confirmed");

                //adding an hour per each slot
                int time = 17 + appointment.getValue().size();
                return time + " : 00";
            }
        }
        return "17 : 00";
    }

    public static void viewBookings(Scanner scanner) {
        System.out.println("Enter the Doctor ID: ");
        checkInputType(scanner);
        int doctorID = scanner.nextInt();

        //Validating the user input for doctor ID
        Doctor selectedDoctor = getDoctor(doctorID);
        if (selectedDoctor == null) {
            System.out.println("Invalid ID\n");
            return;
        }

        System.out.println("Bookings for doctor with ID " + doctorID + " -> \n");

        for (Map.Entry<Date, ArrayList<Appointment>> appointment : selectedDoctor.getAllAppointments().entrySet()) {
            System.out.println(appointment.getKey() + " : " + appointment.getValue() + "\n");
        }
    }


    //Validating the input type
    public static void checkInputType(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid input: ");
            scanner.next();
        }
    }
}

