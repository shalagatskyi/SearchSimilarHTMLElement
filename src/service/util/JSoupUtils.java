package service.util;

import org.jsoup.nodes.Element;

public class JSoupUtils {

    public static String getElementAbsolutePath(Element element) {
        if (element == null) {
            return "";
        }
        return getElementAbsolutePath(element.parent()) + " > " + element.nodeName();
    }
}
