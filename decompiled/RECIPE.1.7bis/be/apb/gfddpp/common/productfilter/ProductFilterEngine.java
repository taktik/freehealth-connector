package be.apb.gfddpp.common.productfilter;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.status.StatusCode;
import be.apb.gfddpp.common.utils.CommonIOUtils;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.gfddpp.domain.RangeType;
import be.apb.gfddpp.productfilter.Medicine;
import be.apb.gfddpp.productfilter.Preparation;
import be.apb.gfddpp.productfilter.ProductFilter;
import be.apb.gfddpp.productfilter.ProductList;
import be.apb.gfddpp.productfilter.Range;
import be.apb.gfddpp.productfilter.RangesType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBElement;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class ProductFilterEngine {
   private static final Logger LOG = Logger.getLogger(ProductFilterEngine.class);
   private ListWrapper listWrapper;
   private String productFilterXmlPath;
   private String productFilterXmlFilename;
   private String productFilterResource;
   private JaxContextCentralizer jaxContextCentralizer = JaxContextCentralizer.getInstance();

   public ProductFilterEngine(String productFilterResource) throws GFDDPPException {
      this.productFilterResource = productFilterResource;
      this.reloadCache();
   }

   public ProductFilterEngine(String productFilterXmlPath, String productFilterXmlFilename) throws GFDDPPException {
      this.productFilterXmlPath = productFilterXmlPath;
      this.productFilterXmlFilename = productFilterXmlFilename;
      this.reloadCache();
   }

   public boolean isBlacklistedMedicine(String id) {
      return this.listWrapper.getBlackListMedicine().containsKey(String.valueOf(Integer.parseInt(id))) || this.isInRange(id, this.listWrapper.getMedicineRanges());
   }

   private boolean isInRange(String id, List<Range> listRange) {
      int i = Integer.parseInt(id);
      boolean inRange = false;

      Range range;
      for(Iterator it = listRange.iterator(); !inRange && it.hasNext(); inRange = i >= Integer.parseInt(range.getStart()) && i <= Integer.parseInt(range.getEnd())) {
         range = (Range)it.next();
      }

      return inRange;
   }

   public boolean isWhitelistedMedicine(String id) {
      return this.listWrapper.getWhiteListMedicine().containsKey(String.valueOf(Integer.parseInt(id)));
   }

   public boolean isBlacklistedPreparation(String id) {
      return this.listWrapper.getBlackListPreparation().containsKey(String.valueOf(Integer.parseInt(id))) || this.isInRange(id, this.listWrapper.getPreparationRanges());
   }

   public boolean isWhitelistedPreparation(String id) {
      return this.listWrapper.getWhiteListPreparation().containsKey(String.valueOf(Integer.parseInt(id)));
   }

   public void reloadCache() throws GFDDPPException {
      if (this.productFilterResource != null) {
         try {
            LOG.info("Getting productfilter configuration from resource: " + this.productFilterResource);
            this.initEngine(CommonIOUtils.getBytes(CommonIOUtils.getResourceAsStream(this.productFilterResource)));
         } catch (IOException var3) {
            throw new GFDDPPException(StatusCode.COMMON_ERROR_PRODUCTFILTER_EXCEPTION);
         }
      } else {
         try {
            LOG.info("Getting productfilter configuration from file: " + this.productFilterXmlPath + "/" + this.productFilterXmlFilename);
            this.initEngine(FileUtils.readFileToByteArray(new File(this.productFilterXmlPath + "/" + this.productFilterXmlFilename)));
         } catch (IOException var2) {
            LOG.error("", var2);
            throw new GFDDPPException(StatusCode.COMMON_ERROR_PRODUCTFILTER_EXCEPTION);
         }
      }

   }

   private JaxContextCentralizer getJaxContextCentralizer() {
      return this.jaxContextCentralizer;
   }

   private synchronized void initEngine(byte[] xml) throws GFDDPPException {
      this.getJaxContextCentralizer().addContext(ProductFilter.class);
      HashMap<String, Medicine> blackListMedicine = new HashMap();
      HashMap<String, Medicine> whiteListMedicine = new HashMap();
      HashMap<String, Preparation> blackListPreparation = new HashMap();
      HashMap<String, Preparation> whiteListPreparation = new HashMap();
      List<Range> preparationRanges = new ArrayList();
      ArrayList medicineRanges = new ArrayList();

      try {
         ProductFilter loadedPF = (ProductFilter)this.getJaxContextCentralizer().toObject(ProductFilter.class, xml);
         ProductList productList = loadedPF.getProductList();
         String precedence = loadedPF.getPrecedence();
         this.parseBlackList(productList.getBlackList(), blackListMedicine, blackListPreparation, medicineRanges, preparationRanges);
         this.parseWhiteList(productList.getWhiteList(), whiteListMedicine, whiteListPreparation);
         this.listWrapper = new ListWrapper(whiteListMedicine, blackListMedicine, medicineRanges, whiteListPreparation, blackListPreparation, preparationRanges, precedence);
      } catch (Exception var11) {
         LOG.error("", var11);
         throw new GFDDPPException(StatusCode.COMMON_ERROR_PRODUCTFILTER_EXCEPTION);
      }
   }

   public String getPrecedence() {
      return this.listWrapper.getPrecedence();
   }

   public void setPrecedence(String precedence) {
      synchronized(this.listWrapper) {
         this.listWrapper.setPrecedence(precedence);
      }
   }

   private void parseBlackList(ProductList.BlackList blackListFromDisk, HashMap<String, Medicine> blackListMedicine, HashMap<String, Preparation> blackListPreparation, List<Range> medicineRanges, List<Range> preparationRanges) {
      if (blackListFromDisk != null) {
         if (blackListFromDisk.getMedicines() != null) {
            this.parseListMedicine(blackListFromDisk.getMedicines().getMedicine(), blackListMedicine);
         }

         if (blackListFromDisk.getPreparations() != null) {
            this.parseListPreparation(blackListFromDisk.getPreparations().getPreparation(), blackListPreparation);
         }

         if (blackListFromDisk.getRanges() != null) {
            this.parseRanges(blackListFromDisk.getRanges().getAbstractRange(), medicineRanges, preparationRanges);
         }
      }

   }

   private void parseRanges(List<JAXBElement<? extends RangesType>> absRanges, List<Range> medicineRanges, List<Range> preparationRanges) {
      Iterator i$ = absRanges.iterator();

      while(i$.hasNext()) {
         JAXBElement<? extends RangesType> absRange = (JAXBElement)i$.next();
         RangesType rangesType = (RangesType)absRange.getValue();
         RangeType range = RangeType.valueOf(rangesType);
         switch(range) {
         case MEDICINE:
            this.parseRange(medicineRanges, rangesType.getRange());
            break;
         case PREPARATION:
            this.parseRange(preparationRanges, rangesType.getRange());
         }
      }

   }

   private void parseRange(List<Range> rangeList, List<Range> ranges) {
      Iterator i$ = ranges.iterator();

      while(i$.hasNext()) {
         Range rng = (Range)i$.next();
         rangeList.add(rng);
      }

   }

   private void parseWhiteList(ProductList.WhiteList whiteListFromDisk, HashMap<String, Medicine> whiteListMedicine, HashMap<String, Preparation> whiteListPreparation) {
      if (whiteListFromDisk != null) {
         if (whiteListFromDisk.getMedicines() != null) {
            this.parseListMedicine(whiteListFromDisk.getMedicines().getMedicine(), whiteListMedicine);
         }

         if (whiteListFromDisk.getPreparations() != null) {
            this.parseListPreparation(whiteListFromDisk.getPreparations().getPreparation(), whiteListPreparation);
         }
      }

   }

   private void parseListMedicine(List<Medicine> list, HashMap<String, Medicine> map) {
      Iterator i$ = list.iterator();

      while(i$.hasNext()) {
         Medicine obj = (Medicine)i$.next();
         map.put("" + Integer.parseInt(obj.getProductId()), obj);
      }

   }

   private void parseListPreparation(List<Preparation> list, HashMap<String, Preparation> map) {
      Iterator i$ = list.iterator();

      while(i$.hasNext()) {
         Preparation obj = (Preparation)i$.next();
         map.put("" + Integer.parseInt(obj.getProductId()), obj);
      }

   }
}
