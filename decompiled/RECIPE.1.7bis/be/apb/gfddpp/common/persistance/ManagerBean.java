package be.apb.gfddpp.common.persistance;

import be.apb.gfddpp.common.log.Logger;
import be.apb.gfddpp.common.persistance.criteria.Criteria;
import be.apb.gfddpp.common.persistance.criteria.CriteriaHelper;
import be.apb.gfddpp.common.persistance.criteria.DateCriteria;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class ManagerBean implements Manager {
   private static final Logger LOG = Logger.getLogger(ManagerBean.class);
   private static final String START_DATE = "_start";
   private static final String END_DATE = "_end";

   public List get(EntityManager entityManager, Class clazz, String sidx, String sord, int start, int limit, List<Criteria> masks) {
      return this.get(entityManager, clazz, sidx, sord, start, limit, masks, false);
   }

   public List get(EntityManager entityManager, Class clazz, String sidx, String sord, int start, int limit, List<Criteria> masks, boolean strictMasks) {
      String sql = "SELECT o FROM " + clazz.getSimpleName() + " o ";
      sql = sql + this.addMasks(masks, strictMasks);
      if (sidx != null && !"".equals(sidx)) {
         sql = sql + " ORDER BY o." + sidx + " " + sord;
      }

      Query q = entityManager.createQuery(sql);
      this.setParameterMasks(masks, q);
      q.setFirstResult(start);
      q.setMaxResults(limit);
      LOG.debug("SQL get:" + sql);
      return q.getResultList();
   }

   public int getCount(EntityManager entityManager, Class clazz, List<Criteria> masks) {
      return this.getCount(entityManager, clazz, masks, false);
   }

   public int getCount(EntityManager entityManager, Class clazz, List<Criteria> masks, boolean strictMasks) {
      String sql = "select count(o) from " + clazz.getSimpleName() + " o ";
      sql = sql + this.addMasks(masks, strictMasks);
      LOG.debug("SQL count:" + sql);
      Query q = entityManager.createQuery(sql);
      this.setParameterMasks(masks, q);
      return ((Long)q.getSingleResult()).intValue();
   }

   private String addMasks(List<Criteria> masks, boolean strictMasks) {
      String sql = " ";
      if (masks != null && !masks.isEmpty()) {
         sql = sql + " where 1=1 ";
         Iterator i$ = masks.iterator();

         while(true) {
            while(true) {
               Criteria crit;
               do {
                  if (!i$.hasNext()) {
                     return sql;
                  }

                  crit = (Criteria)i$.next();
               } while(crit.isEmpty());

               if (crit.isDate() && ((DateCriteria)crit).isValid()) {
                  DateCriteria dateCrit = (DateCriteria)crit;
                  if (dateCrit.isRange()) {
                     sql = sql + " AND o." + crit.getName() + " >= :" + crit.getName() + "_start" + " ";
                     sql = sql + " AND o." + crit.getName() + " <= :" + crit.getName() + "_end" + " ";
                  } else {
                     sql = sql + " AND o." + crit.getName() + " = :" + crit.getName() + "_start" + " ";
                  }
               } else if (crit.isEnum()) {
                  sql = sql + " AND o." + crit.getName() + " = :" + crit.getName() + " ";
               } else if (crit.isString()) {
                  sql = sql + " AND upper(o." + crit.getName() + ") like '" + ((String)crit.getValue()).toUpperCase();
                  sql = sql + (strictMasks ? "' " : "%' ");
               } else {
                  sql = sql + " AND o." + crit.getName() + " like '" + crit.getValue();
                  sql = sql + (strictMasks ? "' " : "%' ");
               }
            }
         }
      } else {
         return sql;
      }
   }

   private void setParameterMasks(List<Criteria> masks, Query query) {
      if (masks != null && !masks.isEmpty()) {
         Iterator i$ = CriteriaHelper.getDateCriterias(masks).iterator();

         while(i$.hasNext()) {
            DateCriteria dCrit = (DateCriteria)i$.next();
            query.setParameter(dCrit.getName() + "_start", dCrit.getStart());
            if (dCrit.isRange()) {
               query.setParameter(dCrit.getName() + "_end", dCrit.getEnd());
            }
         }

         i$ = CriteriaHelper.getEnumCriterias(masks).iterator();

         while(i$.hasNext()) {
            Criteria eCrit = (Criteria)i$.next();
            query.setParameter(eCrit.getName(), eCrit.getValue());
         }
      }

   }
}
