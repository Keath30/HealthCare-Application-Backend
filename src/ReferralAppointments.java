import java.util.Date;

public class ReferralAppointments extends Appointment{

    private Doctor referralDoctor;
    private String notes;

    private String referralDoctorNotes;

    public ReferralAppointments(Doctor doctor, Patient patient, Date date, String time, Doctor referralDoctor,String notes, String referralDoctorNotes){
        super(doctor,patient,date,time);
        this.referralDoctor = referralDoctor;
        this.notes = notes;
    }

    public Doctor getReferralDoctor() {
        return referralDoctor;
    }

    public void setReferralDoctor(Doctor referralDoctor) {
        this.referralDoctor = referralDoctor;
    }

    public String getReferralDoctorNotes() {
        return referralDoctorNotes;
    }

    public void setReferralDoctorNotes(String referralDoctorNotes) {
        this.referralDoctorNotes = referralDoctorNotes;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
