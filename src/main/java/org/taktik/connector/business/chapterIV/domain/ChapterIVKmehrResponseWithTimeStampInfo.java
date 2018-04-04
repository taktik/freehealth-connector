package org.taktik.connector.business.chapterIV.domain;

import org.taktik.connector.business.chapterIV.builders.impl.ResponseBuilderImpl;
import org.taktik.connector.technical.utils.MarshallerHelper;
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrresponse;
import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.lang.ArrayUtils;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ChapterIVKmehrResponseWithTimeStampInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   private static final Logger LOG = LoggerFactory.getLogger(ResponseBuilderImpl.class);
   private byte[] timeStampBytes;
   private byte[] kmehrResponseBytes;

   /** @deprecated */
   @Deprecated
   public ChapterIVKmehrResponseWithTimeStampInfo(Kmehrresponse kmehrresponse, TimeStampResponse timeStampResponse, byte[] timeStampBytes, byte[] kmehrResponseBytes) {
      this(timeStampBytes, kmehrResponseBytes);
   }

   public ChapterIVKmehrResponseWithTimeStampInfo(byte[] timeStampBytes, byte[] kmehrResponseBytes) {
      this.timeStampBytes = ArrayUtils.clone(timeStampBytes);
      this.kmehrResponseBytes = ArrayUtils.clone(kmehrResponseBytes);
   }

   public Kmehrresponse getKmehrresponse() {
      MarshallerHelper<Kmehrresponse, Kmehrresponse> helper = new MarshallerHelper(Kmehrresponse.class, Kmehrresponse.class);
      return (Kmehrresponse)helper.toObject(this.kmehrResponseBytes);
   }

   public TimeStampResponse getTimeStampResponse() {
      try {
         return new TimeStampResponse(Arrays.clone(this.timeStampBytes));
      } catch (TSPException var2) {
         LOG.error(var2.getClass().getSimpleName() + ":" + var2.getMessage(), var2);
      } catch (IOException var3) {
         LOG.error(var3.getClass().getSimpleName() + ":" + var3.getMessage(), var3);
      }

      return null;
   }

   public byte[] getTimeStampBytes() {
      return Arrays.clone(this.timeStampBytes);
   }

   public byte[] getKmehrResponseBytes() {
      return Arrays.clone(this.kmehrResponseBytes);
   }
}
