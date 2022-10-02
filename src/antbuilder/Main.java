package antbuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

  private static final Scanner inp = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    String[] basic_input;
    if (args.length == 0) {
      showUsage();
      basic_input = getInput();
    } else {
      String prt_desc;
      if (args.length == 1) {
        showUsage();
        System.out.print("Description : ");
        prt_desc = inp.nextLine();
        if (prt_desc.equals("")) prt_desc = "-";
      } else {
        prt_desc = args[1];
      }

      basic_input = new String[] { args[0].replaceAll("\\s+", ""), prt_desc };
    }

    System.out.println(Arrays.toString(basic_input));

    System.out.print("Create Main Class (Y/n) : ");
    String mk_main = inp.next();
    boolean cr_main = true;

    if (mk_main.equals("n") || mk_main.equals("N")) cr_main = false;

    Generator.outputFile(basic_input, cr_main);
    System.out.print("Initialize Ant Project? (y/N) : ");
    String r_init = inp.next();
    if (r_init.equals("y") || r_init.equals("Y")) Generator.runInit();
  }

  private static final String[] getInput() {
    String prt_name;
    while (true) {
      System.out.print("Project name : ");
      prt_name = inp.nextLine().replaceAll("\\s+", "");
      if (!prt_name.equals("")) break;
      System.out.println("Name must be entered!");
    }
    System.out.print("Description : ");
    String prt_desc = inp.nextLine();
    if (prt_desc.equals("")) prt_desc = "-";
    return new String[] { prt_name, prt_desc };
  }

  private static void showUsage() {
    System.out.println("Usage: ant-builder [PROJECT NAME] [DESCRIPTION]");
  }
}
