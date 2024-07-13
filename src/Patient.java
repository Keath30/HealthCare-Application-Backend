public class Patient extends Person{
    private String patientId;

    public Patient(String patientId, String name, String contactNumber) {
        super(name,contactNumber);
        this.patientId = patientId;

    }

    public String getPatientId(){
        return this.patientId;
    }

    public char getPatientType(){
        return this.patientId.charAt(0);
    }
}
