package sun.misc.resources;

import java.util.ListResourceBundle;

public class Messages_ja extends ListResourceBundle {
   private static final Object[][] contents = new Object[][]{{"optpkg.versionerror", "エラー: JARファイル{0}で無効なバージョン形式が使用されています。サポートされるバージョン形式についてのドキュメントを参照してください。"}, {"optpkg.attributeerror", "エラー: 必要なJARマニフェスト属性{0}がJARファイル{1}に設定されていません。"}, {"optpkg.attributeserror", "エラー: 複数の必要なJARマニフェスト属性がJARファイル{0}に設定されていません。"}};

   public Object[][] getContents() {
      return contents;
   }
}
