import java.util.ArrayList;

public class Patient extends Person {
    private ArrayList<String> medicalHistories = new ArrayList<>();
    // a patient can have multiple appointments
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Patient(String name, int age, Gender gender) {
        super(name, age, gender);
    }



    void addMedicalHistory(String medicalHistory) {
        this.medicalHistories.add(medicalHistory);
    }
    public String getMedicalHistory() {
        String result="";
        for (String medicalHistory : medicalHistories) {
            result += medicalHistory + ", ";
        }
        return result;
    }

    void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    @Override
    public String introduce() {
        return "Hi my name is " +this.getName()+ " and this is my medical History "+this.getMedicalHistory();
    }


}
