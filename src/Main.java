import brands.*;
import wrestlers.Diva;
import wrestlers.Superstar;
import wrestlers.Wrestler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    final static String MALE_LIST_PATH = "src/Superstars";
    final static String FEMALE_LIST_PATH = "src/Divas";


    static Raw raw = new Raw("Monday Night RAW");
    static SmackDown smackDown = new SmackDown("brands.SmackDown LIVE");
    static NXT nxt = new NXT("brands.NXT");
    static TwoOFive twoO5 = new TwoOFive("205 LIVE");
    static List<Superstar> malesList = new ArrayList<>();
    static List<Diva> femalesList = new ArrayList<>();

    public static void main (String[] args) throws IOException {

        BufferedReader maleReader = new BufferedReader(new FileReader(MALE_LIST_PATH));
        BufferedReader femaleReader = new BufferedReader(new FileReader(FEMALE_LIST_PATH));
        objectCreator(maleReader, "wrestlers.Superstar");
        objectCreator(femaleReader, "wrestlers.Diva");

        for (int i =0; i < 2; i++) {
            for (Superstar superstar : malesList) {
                wrestlerBrandProcessor(superstar);
            }

            for (Diva diva : femalesList) {
                wrestlerBrandProcessor(diva);
            }
        }

        raw.save(Brand.getOutputPath());
        smackDown.save(Brand.getOutputPath());
        nxt.save(Brand.getOutputPath());
        twoO5.save(Brand.getOutputPath());
    }

    public static void objectCreator(BufferedReader reader, String string) throws IOException {
        String line;
        while((line = reader.readLine()) != null) {
            if ("wrestlers.Superstar".equals(string)){
                malesList.add(new Superstar(line));
            }
            else if ("wrestlers.Diva".equals(string)){
                femalesList.add(new Diva(line));
            }
        }
    }
        public static void wrestlerBrandProcessor(Wrestler wrestler) {
            int decider = (int) getRandomNumber();
            brandDecider(decider, wrestler);
        }

        public static void brandDecider(int decider, Wrestler wrestler) {
            if(decider < 25) {
                genderChecker(raw, wrestler);
            }
            else if(decider > 24 && decider < 50) {
                genderChecker(smackDown, wrestler);
            }
            else if(decider > 49 && decider < 75) {
                genderChecker(nxt, wrestler);
            }
            else {
                genderChecker(twoO5, wrestler);
            }
        }

        public static void genderChecker(Brand brand, Wrestler wrestler) {
            if (wrestler instanceof Superstar) {
                brandAvailability(brand, (Superstar) wrestler);
            }
            else if (wrestler instanceof Diva) {
                brandAvailability(brand, (Diva) wrestler);
            }
        }

        public static void brandAvailability(Brand brand, Superstar superstar) {
            if (brand.maleStars.size() <= brand.MAX_MALES && !brand.maleStars.contains(superstar)) {
                completeAssignment(brand, superstar);
            }
            else {
                wrestlerBrandProcessor(superstar);
            }
        }

        public static void brandAvailability(Brand brand, Diva diva) {
            if (brand.divaStars.size() <= brand.MAX_FEMALES && !brand.divaStars.contains(diva)) {
                completeAssignment(brand, diva);
            }
            else {
                wrestlerBrandProcessor(diva);
            }
        }
        public static void completeAssignment(Brand brand, Wrestler wrestler) {
            if (wrestler instanceof Superstar) {
                brand.maleStars.add((Superstar)wrestler);
            }
            else {
                brand.divaStars.add((Diva) wrestler);
            }

        }

    public static double getRandomNumber(){
        return Math.random() * 100;
    }

}
