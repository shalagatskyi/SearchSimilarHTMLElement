package service.analyze;

import org.jsoup.nodes.Attributes;

public interface AttributeAnalyzer {
    int analyze(Attributes reference, Attributes sample);
}
