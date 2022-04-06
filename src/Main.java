import model.builder.MainServiceBuilder;
import service.MainService;
import util.Constants;

public class Main {

    public static void main(String[] args) {

        System.out.println("Creating Main Service...");
        MainService mainService =
                MainServiceBuilder.builder()
                        .withHost("127.0.0.1")
                        .withPort(Constants.PORT)
                        .build();
        System.out.println("Main Service Created!");

        System.out.println("Starting Main Service...");
        mainService.run();
        System.out.println("Main Service is RUNNING!");

        mainService.sendMessage();

    }

}
