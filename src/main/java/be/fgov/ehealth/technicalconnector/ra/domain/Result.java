package be.fgov.ehealth.technicalconnector.ra.domain;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.technicalconnector.ra.enumaration.Status;
import be.fgov.ehealth.technicalconnector.ra.exceptions.RaException;
import org.joda.time.DateTime;

public class Result<T> {
   private Status status;
   private DateTime time;
   private String msg;
   private Throwable cause;
   private StatusResponseType statusResponseType;
   private T result;

   public Result(String msg, Throwable cause) {
      this.status = Status.ERROR;
      this.msg = msg;
      this.cause = cause;
   }

   public Result(String msg, StatusResponseType errorStatus) {
      this.status = Status.ERROR;
      this.msg = msg;
      this.statusResponseType = errorStatus;
   }

   public Result(DateTime time) {
      this.status = Status.PENDING;
      this.time = time;
   }

   public Result(T result) {
      this.status = Status.OK;
      this.result = result;
   }

   public Result(T result, StatusResponseType statusResponseType) {
      this.status = Status.OK;
      this.result = result;
      this.statusResponseType = statusResponseType;
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
         throw new RaException(this.msg, this.cause, this.statusResponseType);
      } else {
         return this.result;
      }
   }

   public Throwable getCause() {
      return this.cause;
   }
}
