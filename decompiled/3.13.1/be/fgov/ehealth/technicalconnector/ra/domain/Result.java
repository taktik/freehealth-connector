package be.fgov.ehealth.technicalconnector.ra.domain;

import be.fgov.ehealth.technicalconnector.ra.enumaration.Status;
import be.fgov.ehealth.technicalconnector.ra.exceptions.RaException;
import org.joda.time.DateTime;

public class Result<T> {
   private Status status;
   private DateTime time;
   private String msg;
   private Throwable cause;
   private T result;

   public Result(String msg, Throwable cause) {
      this.status = Status.ERROR;
      this.msg = msg;
      this.cause = cause;
   }

   public Result(DateTime time) {
      this.status = Status.PENDING;
      this.time = time;
   }

   public Result(T result) {
      this.status = Status.OK;
      this.result = result;
   }

   public boolean hasStatusError() {
      return !this.status.equals(Status.OK);
   }

   public Status getStatus() {
      return this.status;
   }

   public DateTime getTime() {
      return this.time;
   }

   public T getResult() throws RaException {
      if (this.hasStatusError()) {
         throw new RaException(this.msg, this.cause);
      } else {
         return this.result;
      }
   }

   public Throwable getCause() {
      return this.cause;
   }
}
