import java.util.ArrayList;

public class Clinic {
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();

    // add doctors to the clinic
    void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }
    // add patients to teh clinic
    void addPatient(Patient patient){
        patients.add(patient);
    }


    // add appointment
    public void addAppointment(Appointment appointment){
        for (Appointment a : appointments){
            // if the appointment had teh same doctor and the same date
            if(a.getDate().equals(appointment.getDate())&& a.getDoctor().equals(appointment.getDoctor())){
                System.out.println("there is already a scheduled appointment on this date");
                return;
            }
        }
        appointments.add(appointment);
        //adding the appointments to the patient and doctor
        appointment.getDoctor().addAppointment(appointment);
        appointment.getPatient().addAppointment(appointment);
        appointment.schedule();

    }
    public void cancelAppointment(Appointment appointment,Doctor doctor){
        for (Appointment a : appointments){
            //only the doctor of that appointment can cancel,
            if(a.equals(appointment) && a.getDoctor().equals(doctor)&& !appointment.getStatus().equals(Status.CANCELLED)){
                appointments.remove(appointment);
                appointment.cancel();
                //overloading method
                System.out.println(appointment.cancel(appointment.getDoctor()));
            }
        }
    }

    public void confirmAppointment(Appointment appointment,Patient patient){
        for (Appointment a : appointments){
            //confirming the appointment
            if(a.equals(appointment) && a.getPatient().equals(patient)){
                appointment.confirm();
            }
        }
    }

    public void printAllAppointments(){
        for(Appointment a : appointments){
            System.out.print("\n");
            System.out.println("All Appointments: "+a.Summary());

        }
        if (appointments.isEmpty()){
            System.out.print("No Appointments");
        }
    }


}
