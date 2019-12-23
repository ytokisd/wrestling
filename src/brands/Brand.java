package brands;
import wrestlers.Diva;
import wrestlers.Superstar;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Brand {
    final static String OUTPUT = "src/output";
    final int MAX_MALES = 32;
    final int MAX_FEMALES = 18;

    private String bName;
    public Brand(String bName) {
        this.bName = bName;
    }
    public String getbName() {
        return bName;
    }
    List<Superstar> maleStars = new ArrayList<>();
    List<Diva> divaStars = new ArrayList<>();

    public static String getOutputPath() {
        return OUTPUT;
    }

    public static int getMaxMales() {
        return MAX_MALES;
    }

    public static String getOutputPath() {
        return OUTPUT;
    }

    public void save(String fileName) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true));
        pw.println("****************************************************************");
        pw.println(compiledDataMale());
        pw.println("");
        pw.println(compiledDataFemale());
        pw.close();
    }

    public String compiledDataMale() {
        StringBuilder superstars = new StringBuilder("Superstars drafted to the " + getbName() + ": ");
        for (Superstar superstar : maleStars) {
            superstars.append(superstar.getName()).append(", ");
        }
        return superstars.toString();
    }

    public String compiledDataFemale() {
        StringBuilder divas = new StringBuilder("Divas drafted to the " + getbName() + ": ");
        for (Diva diva : divaStars) {
            divas.append(diva.getName()).append(", ");
        }
        return divas.toString();
    }
}
