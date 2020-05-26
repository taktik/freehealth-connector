package org.perf4j.helpers;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.perf4j.StopWatch;

public class StopWatchParser {
   public static final String DEFAULT_MATCH_PATTERN = "start\\[(\\d+)\\] time\\[(\\d+)\\] tag\\[(.*?)\\](?: message\\[(.*?)\\])?";
   private Pattern pattern;

   public StopWatchParser() {
      this("start\\[(\\d+)\\] time\\[(\\d+)\\] tag\\[(.*?)\\](?: message\\[(.*?)\\])?");
   }

   public StopWatchParser(String matchPattern) {
      this.pattern = Pattern.compile(matchPattern);
   }

   public Pattern getPattern() {
      return this.pattern;
   }

   public StopWatch parseStopWatch(String message) {
      MatchResult result = this.match(message);
      return result != null ? this.parseStopWatchFromLogMatch(result) : null;
   }

   public MatchResult match(String message) {
      Matcher matcher = this.getPattern().matcher(message);
      return matcher.find() ? matcher.toMatchResult() : null;
   }

   public StopWatch parseStopWatchFromLogMatch(MatchResult matchResult) {
      return new StopWatch(Long.parseLong(matchResult.group(1)), Long.parseLong(matchResult.group(2)), matchResult.group(3), matchResult.group(4));
   }

   public boolean isPotentiallyValid(String message) {
      return message.startsWith("start");
   }
}
