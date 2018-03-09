package service.analyze;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AttributeAnalyzerImpl implements AttributeAnalyzer {
    //can play around with these coefficients in order to get better result, currently it's just logical values and it works
    private static final int WEIGHT_COEFFICIENT_FOR_KEY = 2;
    private static final int WEIGHT_COEFFICIENT_FOR_VALUE = 1;

    @Override
    public int analyze(Attributes reference, Attributes sample) {
        AtomicInteger score = new AtomicInteger();

        if (Objects.equals(reference, sample)) {
            return Integer.MAX_VALUE;
        }

        List<Attribute> attributes = reference.asList();

        attributes.forEach(refAttribute -> {
            if (sample.hasKey(refAttribute.getKey())) {
                score.addAndGet(WEIGHT_COEFFICIENT_FOR_KEY);
                Set<String> tokensRef = getStringTokensSplitByWhiteSpaces(refAttribute.getValue());
                Set<String> tokensSample = getStringTokensSplitByWhiteSpaces(sample.get(refAttribute.getKey()));
                tokensRef.retainAll(tokensSample);
                score.addAndGet(WEIGHT_COEFFICIENT_FOR_VALUE * tokensRef.size());
            }
        });

        return score.get();
    }

    private HashSet<String> getStringTokensSplitByWhiteSpaces(String refAttribute) {
        return new HashSet<>(Arrays.asList(refAttribute.split("\\s+")));
    }

}
