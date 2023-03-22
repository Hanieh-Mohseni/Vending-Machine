import com.sg.vendingMachine.controller.ClassVendingMachineController;
import com.sg.vendingMachine.dao.ClassVendingMachineAuditDao;
import com.sg.vendingMachine.dao.ClassVendingMachineAuditDaoImpl;
import com.sg.vendingMachine.dao.ClassVendingMachineDao;
import com.sg.vendingMachine.dao.ClassVendingMachineDaoFileImpl;
import com.sg.vendingMachine.service.ClassVendingMachineServiceLayer;
import com.sg.vendingMachine.service.ClassVendingMachineServiceLayerImpl;
import com.sg.vendingMachine.ui.ClassVendingMachineView;
import com.sg.vendingMachine.ui.UserIO;
import com.sg.vendingMachine.ui.UserIOConsoleImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext appConfig =
                new AnnotationConfigApplicationContext();

        appConfig.scan("com.sg.vendingMachine");
        appConfig.refresh();

        ClassVendingMachineController controller =
                appConfig.getBean("classVendingMachineController",ClassVendingMachineController.class);

        controller.run();



    }
}
