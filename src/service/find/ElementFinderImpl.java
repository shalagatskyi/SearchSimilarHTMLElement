package service.find;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import service.util.JSoupUtils;
import service.analyze.AttributeAnalyzer;
import service.analyze.AttributeAnalyzerImpl;
import service.parse.HtmlParser;
import service.parse.HtmlParserImpl;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ElementFinderImpl implements ElementFinder {
    private static final String LINK_TAG = "a";

    @Override
    public String findElementPathBasedOnLinkTag(String sourceFile, String sourceElementId, String targetFile) {
        HtmlParser parser = new HtmlParserImpl();
        Optional<Attributes> sourceFileAttributes = parser.getAttributesFromFileByTargetElementId(sourceFile, sourceElementId);

        if (!sourceFileAttributes.isPresent()) {
            return "Some problem with source file";
        }

        Map<Element, Attributes> targetFileAttributes = parser.getAllAttributesFromFileByTag(targetFile, LINK_TAG);

        AttributeAnalyzer analyzer = new AttributeAnalyzerImpl();

        //have to do this dirty business because equal paths are possible, so we can lost some elements if we were using path as a key
        Map<Element, Integer> targetFileAttributesScore = targetFileAttributes.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, el -> analyzer.analyze(sourceFileAttributes.get(), el.getValue())));

        Element targetElement = Collections.max(targetFileAttributesScore.entrySet(), Map.Entry.comparingByValue()).getKey();

        //check the scores for all the elements and check the text inside (easier to check the solution)
//        targetFileAttributesScore.forEach((key, value) -> System.out.println(JSoupUtils.getElementAbsolutePath(key) + " \n has ->>>>>" + value + "<<<<<- \n" + key.text()));

        return JSoupUtils.getElementAbsolutePath(targetElement);
    }
}
