package ru.otus.example;

import ru.otus.test_framework.MyTestFramework;

public class Main  {

    public static void main(String[] args) throws Exception {
        if (args.length < 2)
        {
            System.out.println("Wrong arguments of command line");
            System.exit(0);
        }

        MyTestFramework myTestFramework = new MyTestFramework();

        switch (args[0])
        {
            case "packages":
                for (int i = 1; i < args.length; ++i)
                {
                    myTestFramework.addPackage(args[i]);
                }
                break;

            case "classes":
                for (int i = 1; i < args.length; ++i)
                {
                    myTestFramework.addClass(args[i]);
                }
                break;

            default:
                System.out.println("Wrong arguments of command line");
                System.exit(0);
        }

        myTestFramework.run();
    }
}
