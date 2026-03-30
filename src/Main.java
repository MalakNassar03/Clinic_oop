import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Clinic clinic=new Clinic();
        //Doctor Object
        Doctor doctor1=new Doctor("adam",36,Gender.MALE,5);
        //Patient Object
        Patient patient=new Patient("jack",25,Gender.MALE);
        Patient patient2=new Patient("bob",25,Gender.MALE);

        patient.addMedicalHistory("Diabetes");
        LocalDate dot=LocalDate.now();
        LocalDate dot2 = LocalDate.now().plusDays(2);
        Appointment appointment=new Appointment(doctor1,patient,dot);
        Appointment appointment2=new Appointment(doctor1,patient2,dot2);


        System.out.println(doctor1.introduce());
        System.out.println(patient.introduce());

        //clinic
        clinic.addDoctor(doctor1);
        clinic.addPatient(patient);
        clinic.addAppointment(appointment);
        clinic.addAppointment(appointment2);
        clinic.confirmAppointment(appointment2,patient2);



        clinic.printAllAppointments();


        clinic.cancelAppointment(appointment,doctor1);
        System.out.print("************************************");
        // the canceled appointment is removed from the list
        clinic.printAllAppointments();
        // the appointment is kept for the doctor so it shows it has been canceled
        doctor1.printAllAppointments();






    }
}