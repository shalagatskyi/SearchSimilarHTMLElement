package service.parse;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

import java.util.Map;
import java.util.Optional;

public interface HtmlParser {

    Optional<Attributes> getAttributesFromFileByTargetElementId(String sourceFile, String targetElementId);

    Map<Element, Attributes> getAllAttributesFromFileByTag(String sourceFile, String tag);

}
