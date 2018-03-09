package service.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HtmlParserImpl implements HtmlParser{

    @Override
    public Optional<Attributes> getAttributesFromFileByTargetElementId(String sourceFile, String targetElementId) {

        Optional<Document> document = getDocument(sourceFile);
        if (!document.isPresent()) {
            return Optional.empty();
        }

        Element targetElement = document.get().getElementById(targetElementId);

        return Optional.of(targetElement.attributes());
    }

    @Override
    public Map<Element, Attributes> getAllAttributesFromFileByTag(String sourceFile, String tag) {
        Optional<Document> document = getDocument(sourceFile);
        if (!document.isPresent()) {
            return new HashMap<>();
        }
        return document.get()
                .getElementsByTag(tag)
                .stream()
                .collect(Collectors.toMap(Function.identity(), Element::attributes));
    }

    private Optional<Document> getDocument(String sourceFile) {
        File input = new File(sourceFile);
        Document document = null;
        try {
            document = Jsoup.parse(input, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(document);
    }
}
