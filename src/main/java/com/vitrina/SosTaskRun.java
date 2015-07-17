package com.vitrina;

/**
 *
 * @author dn200978lak
 * @version 2.0
 */
public class SosTaskRun {

    public static void main(String[] args) {

        System.out.print("Passed config params ..... ");
        if (1 == args.length) {
            System.out.println(args.length);

            /**
             * Builad URLs
             *
             * http://10.1.99.58/predmine/projects/debt/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786
             * http://10.1.99.58/predmine/projects/biplane-web/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786
             * http://10.1.99.58/predmine/projects/irbis/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786
             */
            String[] params = new String[3];
//            params[0] = "http://" + args[0] + "/predmine/projects/debt/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786";        // (IP-xml) debt
//            params[1] = "http://" + args[0] + "/predmine/projects/biplane-web/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786"; // (IP-xml) biplane-web
//            params[2] = "http://" + args[0] + "/predmine/projects/irbis/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786";       // (IP-xml) irbis
            params[0] = "http://localhost/issues.xml";

            /**
             *  Exec..
             */
            new AlgorithmVitrinaPredmain(params[0], "debt");
//            new AlgorithmVitrinaPredmain(params[1], "biplane_web", "add");
//            new AlgorithmVitrinaPredmain(params[2], "irbis", "add");

            return;
        } else {
            System.out.println("ERROR");
            return;
        }
    }
}
