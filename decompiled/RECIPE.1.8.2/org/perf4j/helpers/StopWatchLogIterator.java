package org.perf4j.helpers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.perf4j.StopWatch;

public class StopWatchLogIterator implements Iterator<StopWatch> {
   private Scanner inputScanner;
   private StopWatchParser stopWatchParser;
   private StopWatch nextStopWatch = null;
   private Boolean hasNext = null;

   public StopWatchLogIterator(Readable log) {
      this.inputScanner = new Scanner(log);
      this.stopWatchParser = this.newStopWatchParser();
   }

   public boolean hasNext() {
      if (this.hasNext == null) {
         this.nextStopWatch = this.getNext();
         this.hasNext = this.nextStopWatch != null;
      }

      return this.hasNext;
   }

   public StopWatch next() {
      if (Boolean.FALSE.equals(this.hasNext)) {
         throw new NoSuchElementException();
      } else {
         if (this.nextStopWatch == null) {
            this.nextStopWatch = this.getNext();
            if (this.nextStopWatch == null) {
               this.hasNext = false;
               throw new NoSuchElementException();
            }
         }

         StopWatch retVal = this.nextStopWatch;
         this.hasNext = null;
         this.nextStopWatch = null;
         return retVal;
      }
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }

   protected StopWatchParser newStopWatchParser() {
      return new StopWatchParser();
   }

   private StopWatch getNext() {
      String line;
      while((line = this.inputScanner.findInLine(this.stopWatchParser.getPattern())) == null && this.inputScanner.hasNextLine()) {
         this.inputScanner.nextLine();
      }

      return line != null ? this.stopWatchParser.parseStopWatchFromLogMatch(this.inputScanner.match()) : null;
   }
}
