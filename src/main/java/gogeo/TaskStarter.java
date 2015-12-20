package gogeo;

import gogeo.impl.FileResultWriter;
import gogeo.impl.JsonCityToCsvConverter;
import gogeo.impl.UrlCityReader;

public class TaskStarter {
    public static final String HELP = "-help";

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args.length == 1) {
                if (HELP.equalsIgnoreCase(args[0])) {
                    printHelp();
                    System.exit(0);
                }
            }
            CityInformationProcessor cityInformationProcessor = new CityInformationProcessor(new UrlCityReader(), new JsonCityToCsvConverter(), new FileResultWriter());
            cityInformationProcessor.processCities(args);
        } else {
            printHelp();
        }
    }

    private static void printHelp() {
        System.out.println("Provide cities as parameters(space separated e.g. Berlin Moscow)");
    }
}
