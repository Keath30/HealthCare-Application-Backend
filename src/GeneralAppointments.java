import java.util.Date;

public class GeneralAppointments extends Appointment{

    private String patientNotes;

    public GeneralAppointments(Doctor doctor, Patient patient, Date date, String patientNotes, String time){
        super(doctor,patient,date,time);
        this.patientNotes = patientNotes;
    }

    public String getPatientNotes() {
        return patientNotes;
    }

    public void setPatientNotes(String patientNotes) {
        this.patientNotes = patientNotes;
    }

}


