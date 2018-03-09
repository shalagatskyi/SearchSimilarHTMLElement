package service;

import service.find.ElementFinder;
import service.find.ElementFinderImpl;

public class Main {

    public static void main(String[] args) {
        String sourceElementId =  args.length == 3 ? args[2] : "make-everything-ok-button";

        ElementFinder finder = new ElementFinderImpl();
        try {
            System.out.println(finder.findElementPathBasedOnLinkTag(args[0], sourceElementId, args[1]));
        } catch (Exception e) {
            System.out.println("Ooops, something went wrong, can't find anything for " + sourceElementId +
                    "\n Please try one more time, or use default parameters");
        }
    }

}
