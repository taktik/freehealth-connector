package be.apb.gfddpp.common.persistance;

import be.apb.gfddpp.common.persistance.criteria.Criteria;
import java.util.List;
import javax.persistence.EntityManager;

public interface Manager {
   List get(EntityManager var1, Class var2, String var3, String var4, int var5, int var6, List<Criteria> var7);

   List get(EntityManager var1, Class var2, String var3, String var4, int var5, int var6, List<Criteria> var7, boolean var8);

   int getCount(EntityManager var1, Class var2, List<Criteria> var3);

   int getCount(EntityManager var1, Class var2, List<Criteria> var3, boolean var4);
}
