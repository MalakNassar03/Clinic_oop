@FunctionalInterface
public interface AppointmentFilter<T> {
    boolean filterAppointments(T t);
}
