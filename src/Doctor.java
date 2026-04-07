import java.util.ArrayList;

public class Doctor extends Person {
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private double yearsOfExperience;

    public Doctor(long id,String name, int age, Gender gender,double yearsOfExperience) {
        super(id,name, age, gender);
        this.yearsOfExperience=yearsOfExperience;

    }

    public double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }



    void addAppointment(Appointment appointment) {
        appointments.add(appointment);
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


    @Override
    public String introduce() {
        return "Hi my name is " +this.getName()+ ", i have "+this.getYearsOfExperience()+" years of experience ";
    }
}
