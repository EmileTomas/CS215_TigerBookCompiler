package Parse;

import Semant.Semant;

import java.awt.*;

public class Main {
    final static String FilePath="/home/emile/Documents/Github/TigerCompiler/TestCase/More/Bad/4.tig";

    public static void main(String argv[])  {
        Parse parser=new Parse(FilePath);
        Absyn.Exp absyn=parser.getAbsyn();

        Semant semant=new Semant(parser.errorMsg);
        System.out.print("\n\n\n");
        System.out.println("Semant Test:");
        semant.transProg(absyn);
    }

}


