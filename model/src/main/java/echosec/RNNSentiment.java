package echosec;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Map;

/**
 * Created by Stephan Rayner on 2016-01-26.
 */
public class RNNSentiment {
    // private final Logger _logger = LoggerFactory.getLogger(RNNSentimentBolt.class);
    private StanfordCoreNLP pipeline;

    RNNSentiment(){
        Properties props = new Properties();
        props.setProperty("annotators","tokenize, ssplit , parse, sentiment");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public String compute(String text) {
        return this.findSentiment(text);
    }

    /**
     * This is the work horse this does the sentiment analysis
     * @param line
     * @return
     */
    public String findSentiment(String line) {
        int mainSentiment=0;

        if (line != null && line.length() >0) {
            int longest = 0;
            Annotation annotation = pipeline.process(line);
            for (CoreMap sentence :annotation.get(CoreAnnotations.SentencesAnnotation.class )) {
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }
            }
        }

        switch (mainSentiment) {
            case 0:
                return "Very Negative";
            case 1:
                return "Negative";
            case 2:
                return "Neutral";
            case 3:
                return "Positive";
            case 4:
                return "Very Positive";
            default:
                return "";
        }

    }

}
