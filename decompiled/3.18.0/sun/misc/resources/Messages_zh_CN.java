package sun.misc.resources;

import java.util.ListResourceBundle;

public class Messages_zh_CN extends ListResourceBundle {
   private static final Object[][] contents = new Object[][]{{"optpkg.versionerror", "错误: {0} JAR 文件中使用的版本格式无效。请检查文档以了解支持的版本格式。"}, {"optpkg.attributeerror", "错误: 必要的{0} JAR 清单属性未在{1} JAR 文件中设置。"}, {"optpkg.attributeserror", "错误: 某些必要的 JAR 清单属性未在{0} JAR 文件中设置。"}};

   public Object[][] getContents() {
      return contents;
   }
}
