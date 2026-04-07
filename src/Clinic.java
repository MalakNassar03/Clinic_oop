import java.util.*;
import java.util.stream.Collectors;

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
        AppointmentFilter<Appointment> getfilter= (n)-> appointments.contains(n)  && n.getDoctor().equals(doctor) && !n.getStatus().equals(Status.CANCELLED) ;

            //only the doctor of that appointment can cancel,
            if (getfilter.filterAppointments(appointment)){
                System.out.println("appointment cancelled");
                appointment.cancel();
                //overloading method
                System.out.println(appointment.cancel(appointment.getDoctor()));
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

    //Task 1
    public ArrayList<Appointment> getApppointments(AppointmentFilter<Appointment> filter){
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (filter.filterAppointments(a)) {
                result.add(a);
            }
        }

        return result;

    }
    //Task 2
    public List<String> getConfirmedAppointments(){
        //method refrence used is static reference
        return appointments.stream().filter(n->n.getStatus().equals(Status.CONFIRMED)).sorted(Comparator.comparing(n->n.getPatient().getName())).map(Appointment::Summary).collect(Collectors.toList());
    }

    public Map<String, Long> getDoctorCount(){
        return appointments.stream().collect(Collectors.groupingBy(
                n->n.getDoctor().getName(),
                Collectors.counting()
        ));
    }

    public boolean getDoctorsInClinic(){
        return appointments.stream().filter(n->n.getStatus().equals(Status.SCHEDULED)).allMatch(n->doctors.contains(n.getDoctor()));
    }

    public String getPatientsWithCancelledAppointments(){
        return appointments.stream().filter(n->n.getStatus().equals(Status.CANCELLED)).map(n->n.getPatient().getName()).distinct().collect(Collectors.joining(", "));
    }




//    Task 3:

    public Optional<Doctor> getDoctorByName(String doctorName){
       return  doctors.stream().filter(n->n.getName().equals(doctorName)).findFirst();// find first returns and optional


    }

    public Optional<Patient> getPatientByID(long patientID){
        return patients.stream().filter(n->n.getId()==patientID).findFirst();


    }

    public Optional<Appointment> getPendingAppointment(Doctor doctor){
        return appointments.stream().filter(n->n.getDoctor().equals(doctor) && n.getStatus().equals(Status.SCHEDULED)).sorted(Comparator.comparing(Appointment::getDate)).findFirst();
    }


}
