package org.taktik.freehealth.middleware.drugs.dao.h2impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class Unzip {

  public static final void copyInputStream(InputStream in, OutputStream out)
  throws IOException
  {
    byte[] buffer = new byte[1024];
    int len;

    while((len = in.read(buffer)) >= 0)
      out.write(buffer, 0, len);

    in.close();
    out.close();
  }

  public static final void unzip(File f,File destination) throws IOException {
    Enumeration<? extends ZipEntry> entries;
    ZipFile zipFile;

      zipFile = new ZipFile(f);

      entries = zipFile.entries();

      while(entries.hasMoreElements()) {
        ZipEntry entry = (ZipEntry)entries.nextElement();

        if(entry.isDirectory()) {
          (new File(destination,entry.getName())).mkdir();
          continue;
        }
        copyInputStream(zipFile.getInputStream(entry),
           new BufferedOutputStream(new FileOutputStream(new File(destination,entry.getName()))));
      }

      zipFile.close();
  }

}


