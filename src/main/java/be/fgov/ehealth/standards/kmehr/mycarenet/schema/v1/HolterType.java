package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "holterType",
   propOrder = {"fcavg", "fcmax", "fcmin", "fcavgd", "fcavgn", "rrmax", "rrmin", "qrstot", "brady", "pause", "_long", "esv", "dblv", "salvv", "bgv", "tgv", "tachy", "essv", "dblsv", "salvsv", "bgsv", "tgsv", "tachysv", "rrinst"}
)
public class HolterType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FCAVG"
   )
   protected String fcavg;
   @XmlElement(
      name = "FCMAX"
   )
   protected FCMAXType fcmax;
   @XmlElement(
      name = "FCMIN"
   )
   protected FCMINType fcmin;
   @XmlElement(
      name = "FCAVGD"
   )
   protected String fcavgd;
   @XmlElement(
      name = "FCAVGN"
   )
   protected String fcavgn;
   @XmlElement(
      name = "RRMAX"
   )
   protected RRMAXType rrmax;
   @XmlElement(
      name = "RRMIN"
   )
   protected RRMINType rrmin;
   @XmlElement(
      name = "QRSTOT"
   )
   protected String qrstot;
   @XmlElement(
      name = "BRADY"
   )
   protected String brady;
   @XmlElement(
      name = "PAUSE"
   )
   protected String pause;
   @XmlElement(
      name = "LONG"
   )
   protected String _long;
   @XmlElement(
      name = "ESV"
   )
   protected String esv;
   @XmlElement(
      name = "DBLV"
   )
   protected String dblv;
   @XmlElement(
      name = "SALVV"
   )
   protected String salvv;
   @XmlElement(
      name = "BGV"
   )
   protected String bgv;
   @XmlElement(
      name = "TGV"
   )
   protected String tgv;
   @XmlElement(
      name = "TACHY"
   )
   protected String tachy;
   @XmlElement(
      name = "ESSV"
   )
   protected String essv;
   @XmlElement(
      name = "DBLSV"
   )
   protected String dblsv;
   @XmlElement(
      name = "SALVSV"
   )
   protected String salvsv;
   @XmlElement(
      name = "BGSV"
   )
   protected String bgsv;
   @XmlElement(
      name = "TGSV"
   )
   protected String tgsv;
   @XmlElement(
      name = "TACHYSV"
   )
   protected String tachysv;
   @XmlElement(
      name = "RRINST"
   )
   protected String rrinst;

   public String getFCAVG() {
      return this.fcavg;
   }

   public void setFCAVG(String value) {
      this.fcavg = value;
   }

   public FCMAXType getFCMAX() {
      return this.fcmax;
   }

   public void setFCMAX(FCMAXType value) {
      this.fcmax = value;
   }

   public FCMINType getFCMIN() {
      return this.fcmin;
   }

   public void setFCMIN(FCMINType value) {
      this.fcmin = value;
   }

   public String getFCAVGD() {
      return this.fcavgd;
   }

   public void setFCAVGD(String value) {
      this.fcavgd = value;
   }

   public String getFCAVGN() {
      return this.fcavgn;
   }

   public void setFCAVGN(String value) {
      this.fcavgn = value;
   }

   public RRMAXType getRRMAX() {
      return this.rrmax;
   }

   public void setRRMAX(RRMAXType value) {
      this.rrmax = value;
   }

   public RRMINType getRRMIN() {
      return this.rrmin;
   }

   public void setRRMIN(RRMINType value) {
      this.rrmin = value;
   }

   public String getQRSTOT() {
      return this.qrstot;
   }

   public void setQRSTOT(String value) {
      this.qrstot = value;
   }

   public String getBRADY() {
      return this.brady;
   }

   public void setBRADY(String value) {
      this.brady = value;
   }

   public String getPAUSE() {
      return this.pause;
   }

   public void setPAUSE(String value) {
      this.pause = value;
   }

   public String getLONG() {
      return this._long;
   }

   public void setLONG(String value) {
      this._long = value;
   }

   public String getESV() {
      return this.esv;
   }

   public void setESV(String value) {
      this.esv = value;
   }

   public String getDBLV() {
      return this.dblv;
   }

   public void setDBLV(String value) {
      this.dblv = value;
   }

   public String getSALVV() {
      return this.salvv;
   }

   public void setSALVV(String value) {
      this.salvv = value;
   }

   public String getBGV() {
      return this.bgv;
   }

   public void setBGV(String value) {
      this.bgv = value;
   }

   public String getTGV() {
      return this.tgv;
   }

   public void setTGV(String value) {
      this.tgv = value;
   }

   public String getTACHY() {
      return this.tachy;
   }

   public void setTACHY(String value) {
      this.tachy = value;
   }

   public String getESSV() {
      return this.essv;
   }

   public void setESSV(String value) {
      this.essv = value;
   }

   public String getDBLSV() {
      return this.dblsv;
   }

   public void setDBLSV(String value) {
      this.dblsv = value;
   }

   public String getSALVSV() {
      return this.salvsv;
   }

   public void setSALVSV(String value) {
      this.salvsv = value;
   }

   public String getBGSV() {
      return this.bgsv;
   }

   public void setBGSV(String value) {
      this.bgsv = value;
   }

   public String getTGSV() {
      return this.tgsv;
   }

   public void setTGSV(String value) {
      this.tgsv = value;
   }

   public String getTACHYSV() {
      return this.tachysv;
   }

   public void setTACHYSV(String value) {
      this.tachysv = value;
   }

   public String getRRINST() {
      return this.rrinst;
   }

   public void setRRINST(String value) {
      this.rrinst = value;
   }
}
