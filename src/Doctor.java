import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Doctor extends Person {
    private int doctorId;
    private String birthday;
    private String specialization;
    private ArrayList<Date> availabilities;
    private HashMap<Date,ArrayList<Appointment>> allAppointments = new HashMap<>();

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public ArrayList<Date> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(ArrayList<Date> availabilities) {
        this.availabilities = availabilities;
    }

    public HashMap<Date, ArrayList<Appointment>> getAllAppointments() {
        return allAppointments;
    }

    public void setAllAppointments(HashMap<Date, ArrayList<Appointment>> allAppointments) {
        this.allAppointments = allAppointments;
    }

    public Doctor(int doctorId, String name, String birthday, String specialization, String contactNumber) {
        super(name,contactNumber);
        this.doctorId = doctorId;
        this.birthday = birthday;
        this.specialization = specialization;
        availabilities = new ArrayList<>();
    }

    public boolean isPhysician(){
        return this.specialization.endsWith("physician");
    }

    public void setAvailability(Date availableDate) {
        this.availabilities.add(availableDate);
    }

    public void setAppointment(Appointment appointment, Date date){
        ArrayList<Appointment> currentAppointments = this.allAppointments.get(date);
        if (currentAppointments == null){
            ArrayList<Appointment> tempArrayList = new ArrayList<>();
            tempArrayList.add(appointment);
            this.allAppointments.put(date,tempArrayList);
        }
        else {
            currentAppointments.add(appointment);
            this.allAppointments.put(date,currentAppointments);
        }
    }
}
