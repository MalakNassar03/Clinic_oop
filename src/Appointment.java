import java.time.LocalDate;

public class Appointment implements Schedulable{
    private Status status;
    private LocalDate date;

    //Aggregation ( both can exist independently even if teh appointment didnt exist )
    private Doctor doctor;
    private Patient patient;


    public Appointment(Doctor doctor, Patient patient, LocalDate date)
    {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }


    public Status getStatus() {
        return status;
    }

    public String Summary(){
        return "Appointment summary: \nDoctor: "+this.doctor.getName()+"\nPatient: "+this.patient.getName()+"\nDate: "+this.date+"\nStatus: "+this.getStatus();
    }

    @Override
    public void cancel() {
        status=Status.CANCELLED;

    }

    @Override
    public void schedule() {
        status=Status.SCHEDULED;

    }
    //overLoading
    public String cancel(Doctor doctor){
        status=Status.CANCELLED;
        return "the appointment marked as cancelled by "+doctor.getName();
    }

    public void confirm(){
        status=Status.CONFIRMED;
    }

}
