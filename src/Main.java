import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //constructor reference
        Optional<Clinic> maybeClinic = Optional.empty();
        Clinic clinic = maybeClinic.orElseGet(Clinic::new);
        //Doctor Object
        Doctor doctor1=new Doctor(1,"adam",36,Gender.MALE,5);
        Doctor doctor2=new Doctor(2,"jack",36,Gender.MALE,5);
        //Patient Object
        Patient patient=new Patient(123,"jack",25,Gender.MALE);
        Patient patient2=new Patient(456,"bob",25,Gender.MALE);
        Patient patient3=new Patient(159,"anne",25,Gender.FEMALE);

        patient.addMedicalHistory("Diabetes");
        LocalDate dot=LocalDate.now();
        LocalDate dot2 = LocalDate.now().plusDays(2);
        LocalDate dot3=LocalDate.now().plusDays(3);
        LocalDate dot4=LocalDate.now().plusDays(5);
        LocalDate dot5=LocalDate.now().plusDays(1);
        Appointment appointment=new Appointment(doctor1,patient,dot);
        Appointment appointment2=new Appointment(doctor1,patient2,dot2);
        Appointment appointment3=new Appointment(doctor1,patient2,dot3);
        Appointment appointment4=new Appointment(doctor2,patient2,dot4);
        Appointment appointment5=new Appointment(doctor2,patient3,dot5);




        System.out.println(doctor1.introduce());
        System.out.println(patient.introduce());

        //clinic
        clinic.addDoctor(doctor1);
        clinic.addDoctor(doctor2);
        clinic.addPatient(patient);
        clinic.addAppointment(appointment);
        clinic.addAppointment(appointment5);
        clinic.addAppointment(appointment2);
        clinic.addAppointment(appointment3);
        clinic.addAppointment(appointment4);
        clinic.confirmAppointment(appointment2,patient2);
        clinic.confirmAppointment(appointment3,patient2);



        clinic.printAllAppointments();



        clinic.cancelAppointment(appointment,doctor1);
        clinic.cancelAppointment(appointment2,doctor1);
        clinic.cancelAppointment(appointment4,doctor2);

        System.out.print("************************************");
        // the canceled appointment is removed from the list
        clinic.printAllAppointments();
        // the appointment is kept for the doctor so it shows it has been canceled
        doctor1.printAllAppointments();

//      Task 1:
        System.out.println("***************TASK1*********************");

        System.out.println("*****************STATUS CONFIRMED*******************");
        ArrayList<Appointment> confirmed = clinic.getApppointments(n-> n.getStatus().equals(Status.CONFIRMED));
//instance method reference on a particular object
        confirmed.stream().map(Appointment::Summary).forEach(System.out::println);

        System.out.println("*******************BY DOCTOR********************");
        ArrayList<Appointment> byDoctor= clinic.getApppointments(n->n.getDoctor().equals(doctor1));
        byDoctor.stream().map(Appointment::Summary).forEach(System.out::println);


        System.out.println("******************BY PATIENT********************");
        ArrayList<Appointment> byPatient=clinic.getApppointments(n->n.getPatient().equals(patient2));
        for (Appointment a : byPatient) {
            System.out.println(a.Summary());
            System.out.println();
        }


//      TASK 2:
        System.out.println("*****************TASK2*******************");
        System.out.println("*****************SORTED BY NAME*********************");
        System.out.println(clinic.getConfirmedAppointments());
        System.out.println();

        System.out.println("*****************COUNT APPOINTMENT*********************");
        System.out.println(clinic.getDoctorCount());
        System.out.println();

        System.out.println("*****************DOCTORS FROM Clinic*********************");
        System.out.println(clinic.getDoctorsInClinic());
        System.out.println();


        System.out.println("*****************PATIENTS WITH CANCELLED APPOINTMENTS*********************");
        System.out.println(clinic.getPatientsWithCancelledAppointments());
        System.out.println();



//      TASK 3:
        System.out.println("*****************TASK3*******************");
        // CREATE A NEW DOCTOR WHEN NEEDED
        Doctor c=clinic.getDoctorByName("adam").orElseGet(() -> new Doctor(0, "Unknown", 0, Gender.FEMALE, 0));
        System.out.println(c.introduce());
        System.out.println();
       clinic.getPatientByID(123).ifPresentOrElse(
               p-> System.out.println(p.introduce()),
               //Static method reference
               Utils::patientNotFound

       );

        System.out.println();
        //Instance method reference on an arbitrary object of a type
        System.out.println(clinic.getPendingAppointment(doctor2).map(Appointment::Summary).orElse("no pending appointments"));








    }
}