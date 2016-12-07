package Parse;

import Semant.Semant;

import java.awt.*;
import java.io.FileNotFoundException;

public class Main {

    static final String PREFIX = "/home/emile/Documents/Github/TigerCompiler/TestCase/More/Bad/";

    public static void main(String argv[]) {
        String FilePath;

        for (int i = 15; i < 16; ++i) {
            try {
                FilePath = PREFIX + i + ".tig";
                Parse parser = new Parse(FilePath);
                Absyn.Exp absyn = parser.getAbsyn();

                Semant semant = new Semant(parser.errorMsg);
                System.out.println("File " + i + " :");
                semant.transProg(absyn);
            } catch (Error e) {
            //do nothing
            }
        }
    }

}


