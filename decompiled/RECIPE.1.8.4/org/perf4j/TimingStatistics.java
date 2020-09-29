package org.perf4j;

import java.io.Serializable;

public class TimingStatistics implements Serializable, Cloneable {
   private double mean;
   private double runningQ;
   private long max;
   private long min;
   private int count;

   public TimingStatistics() {
   }

   public TimingStatistics(double mean, double standardDeviation, long max, long min, int count) {
      this.mean = mean;
      this.runningQ = Math.pow(standardDeviation, 2.0D) * (double)count;
      this.max = max;
      this.min = min;
      this.count = count;
   }

   public TimingStatistics addSampleTime(long elapsedTime) {
      ++this.count;
      double diffFromMean = (double)elapsedTime - this.mean;
      this.mean += diffFromMean / (double)this.count;
      this.runningQ += (double)(this.count - 1) * Math.pow(diffFromMean, 2.0D) / (double)this.count;
      if (this.count == 1) {
         this.min = elapsedTime;
         this.max = elapsedTime;
      } else {
         if (elapsedTime < this.min) {
            this.min = elapsedTime;
         }

         if (elapsedTime > this.max) {
            this.max = elapsedTime;
         }
      }

      return this;
   }

   public double getMean() {
      return this.mean;
   }

   public double getStandardDeviation() {
      return Math.sqrt(this.runningQ / (double)this.count);
   }

   public long getMax() {
      return this.max;
   }

   public long getMin() {
      return this.min;
   }

   public int getCount() {
      return this.count;
   }

   public String toString() {
      return "mean[" + this.getMean() + "] stddev[" + this.getStandardDeviation() + "] min[" + this.getMin() + "] max[" + this.getMax() + "] count[" + this.getCount() + "]";
   }

   public TimingStatistics clone() {
      try {
         return (TimingStatistics)super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new Error("Unexpected CloneNotSupportedException");
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof TimingStatistics)) {
         return false;
      } else {
         TimingStatistics that = (TimingStatistics)o;
         if (this.count != that.count) {
            return false;
         } else if (this.max != that.max) {
            return false;
         } else if (Double.compare(that.mean, this.mean) != 0) {
            return false;
         } else if (this.min != that.min) {
            return false;
         } else {
            return Double.compare(that.runningQ, this.runningQ) == 0;
         }
      }
   }

   public int hashCode() {
      long temp = this.mean != 0.0D ? Double.doubleToLongBits(this.mean) : 0L;
      int result = (int)(temp ^ temp >>> 32);
      temp = this.runningQ != 0.0D ? Double.doubleToLongBits(this.runningQ) : 0L;
      result = 31 * result + (int)(temp ^ temp >>> 32);
      result = 31 * result + (int)(this.max ^ this.max >>> 32);
      result = 31 * result + (int)(this.min ^ this.min >>> 32);
      result = 31 * result + this.count;
      return result;
   }
}
