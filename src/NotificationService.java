import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface NotificationService <T> {
    CompletableFuture<String> sendNotification(T t,String message);
}
