import controller.MainController;
import domain.booleanGenerator.RandomBooleanGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(InputView.getInstance(), OutputView.getInstance(),
                new RandomBooleanGenerator());
        mainController.start();
    }
}
