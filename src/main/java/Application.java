import controller.MainController;
import domain.generator.RandomBooleanGenerator;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        MainController mainController = new MainController(InputView.getInstance(scanner), OutputView.getInstance(),
                new RandomBooleanGenerator());
        mainController.start();
    }
}
