package sun.misc.resources;

import java.util.ListResourceBundle;

public class Messages_ko extends ListResourceBundle {
   private static final Object[][] contents = new Object[][]{{"optpkg.versionerror", "오류: {0} JAR 파일에 부적합한 버전 형식이 사용되었습니다. 설명서에서 지원되는 버전 형식을 확인하십시오."}, {"optpkg.attributeerror", "오류: 필요한 {0} JAR manifest 속성이 {1} JAR 파일에 설정되어 있지 않습니다."}, {"optpkg.attributeserror", "오류: 필요한 일부 JAR manifest 속성이 {0} JAR 파일에 설정되어 있지 않습니다."}};

   public Object[][] getContents() {
      return contents;
   }
}
