package authen;

import entity.Users;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import model.DAOUsers;

@WebListener
public class LoginListener implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                DAOUsers DU = new DAOUsers();
                List<Users> lockedUsers = DU.listAllLockedUsers();
                for (Users user : lockedUsers) {
                    DU.unlockUserIfTimeElapsed(user.getId());
                }
            }
        }, 0, 30, TimeUnit.SECONDS); // Chạy mỗi 30 giây
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }
}
