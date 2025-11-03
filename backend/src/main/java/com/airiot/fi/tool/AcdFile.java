package com.airiot.fi.tool;

import org.slf4j.Logger;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class AcdFile {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(AcdFile.class);

  private static String makeRelative(String filePath, String referencePath) {
    if (filePath != null && !filePath.isEmpty() && referencePath != null && !referencePath.isEmpty()) {
      try {
        URI fileUri = new File(filePath).toURI();
        URI referenceUri = new File(referencePath).toURI();
        return referenceUri.relativize(fileUri).getPath().replace("/", File.separator);
      } catch (Exception e) {
        return "";
      }
    } else {
      return "";
    }
  }

  private static String getNumberedDirectory(String sBase, String sExt) {
    String newname = sBase + sExt;
    int i = 1;
    while (new File(newname).exists() && i < 50) {
      newname = sBase + "-" + i + sExt;
      i++;
    }
    if (i >= 50) {
      log.debug("stopped creating dirs...clean some bak files!!!");
      return "";
    }
    return newname;
  }

  private static String getNumberedFilename(String sBase, String sExt) {
    String newname = sBase + sExt;
    int i = 1;
    while (new File(newname).exists() && i < 50) {
      newname = sBase + "-" + i + sExt;
      i++;
    }
    if (i >= 50) {
      log.debug("stopped creating files...clean some bak files!!!");
      return "";
    }
    return newname;
  }

  private static String generate_key(String car_name) {
    int i = 0;
    int key1 = 0;
    while (i < car_name.length()) {
      key1 += car_name.charAt(i);
      i += 1;
    }
    key1 &= 0xff;
    i = 0;
    var key2 = 0;
    while (i < car_name.length() - 1) {
      key2 *= car_name.charAt(i);
      i += 1;
      key2 -= car_name.charAt(i);
      i += 1;
    }
    key2 &= 0xff;
    i = 1;
    var key3 = 0;
    while (i < car_name.length() - 3) {
      key3 *= car_name.charAt(i);
      i += 1;
      key3 = (int) (key3 / (car_name.charAt(i) + 0x1b));
      i -= 2;
      key3 += -0x1b - car_name.charAt(i);
      i += 4;
    }
    key3 &= 0xff;
    i = 1;
    var key4 = 0x1683;
    while (i < car_name.length()) {
      key4 -= car_name.charAt(i);
      i += 1;
    }
    key4 &= 0xff;
    i = 1;
    var key5 = 0x42;
    while (i < car_name.length() - 4) {
      var tmp = (car_name.charAt(i) + 0xf) * key5;
      i -= 1;
      key5 = (car_name.charAt(i) + 0xf) * tmp + 0x16;
      i += 5;
    }
    key5 &= 0xff;
    i = 0;
    var key6 = 0x65;
    while (i < car_name.length() - 2) {
      key6 -= car_name.charAt(i);
      i += 2;
    }
    key6 &= 0xff;
    i = 0;
    var key7 = 0xab;
    while (i < car_name.length() - 2) {
      key7 %= car_name.charAt(i);
      i += 2;
    }
    key7 &= 0xff;
    i = 0;
    var key8 = 0xab;
    while (i < car_name.length() - 1) {
      key8 = (int) (key8 / car_name.charAt(i)) + car_name.charAt(i + 1);
      i += 1;
    }
    key8 &= 0xff;
    return String.format("%d-%d-%d-%d-%d-%d-%d-%d", key1, key2, key3, key4, key5, key6, key7, key8);
  }

  private static boolean acdUnpack(String path, String carname) {
    String basedir = path.replace(File.separator + "data.acd", "");
    String key = generate_key(carname.toLowerCase());
    String acdfile = carname + File.separator + "data.acd ...";
    log.debug("Reading " + acdfile);
    log.debug("key: " + key);
    acdfile = basedir + File.separator + "data.acd";

    File dataDir = new File(basedir + File.separator + "data");
    if (!dataDir.exists()) {
      dataDir.mkdirs();
    } else {
      // backup
      String s = getNumberedDirectory(basedir + File.separator + "data", "");
      new File(basedir + File.separator + "data").renameTo(new File(s));
      dataDir.mkdirs();
    }

    try (FileInputStream stream = new FileInputStream(acdfile)) {
      if (stream.available() > 8) {
        try (DataInputStream reader = new DataInputStream(stream)) {
          int fnameLen = 0;
          long offset = 0;

          // Correctly read Little-Endian integer for header
          fnameLen = Integer.reverseBytes(reader.readInt());
          offset += 4;
          //check version
          if (fnameLen < 0) {
            // Correctly read Little-Endian integer for header
            fnameLen = Integer.reverseBytes(reader.readInt());
            offset += 4;
          } else {
            stream.getChannel().position(0);
            offset = 0;
          }

          var fall = 0;
          var falllen = 0;
          while (offset < stream.getChannel().size() && fnameLen > 0) {
            String fName = "";
            int fLen = 0;

            // Correctly read Little-Endian integer for filename length
            fnameLen = Integer.reverseBytes(reader.readInt());
            offset += 4;

            byte[] fNameBytes = new byte[fnameLen];
            reader.read(fNameBytes);
            // FIXED: Specify UTF-8 encoding for filename
            fName = new String(fNameBytes, StandardCharsets.UTF_8);
            offset += fnameLen;

            // Correctly read Little-Endian integer for file length
            fLen = Integer.reverseBytes(reader.readInt());
            offset += 4;

            byte[] unpacked_content = new byte[fLen];

            for (int i = 0; i < fLen; i++) {
              // FIXED: Apply Integer.reverseBytes() to read the Little-Endian encrypted value
              int encoded_val = Integer.reverseBytes(reader.readInt());

              // Decryption logic
              int code = (encoded_val & 0xFF) - (key.charAt(i % key.length()) & 0xFF);
              if (code < 0)
                code = 256 + code;
              unpacked_content[i] = (byte) code;
            }
            offset += fLen * 4;

            fall++;
            falllen += fLen;
            log.debug(String.format("%5d byte - %32s", fLen, fName));
            try (FileOutputStream outstream = new FileOutputStream(basedir + File.separator + "data" + File.separator + fName)) {
              outstream.write(unpacked_content, 0, fLen);
            }
            offset += 4;
          }
          log.debug("Unpacked " + fall + " files, " + falllen + " bytes");
        }
      } else {
        log.debug("  empty acd-file");
      }
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  private static boolean acdPack(String acdfilepath, String carname) {
    String basedir = acdfilepath.replace(File.separator + "data.acd", "");
    String key = generate_key(carname.toLowerCase());

    log.debug("Writing 'data.acd' ...");
    log.debug("key: " + key);

    File dataDir = new File(basedir + File.separator + "data");
    if (!dataDir.exists()) {
      log.debug("Path not found: " + basedir + File.separator + "data");
      return false;
    }
    File[] inputFiles = dataDir.listFiles();
    if (inputFiles == null || inputFiles.length == 0) {
      return false;
    }

    if (new File(acdfilepath).exists()) {   // backup existing data.acd !!!
      String s = getNumberedFilename(basedir + File.separator + "data", ".acd");
      new File(acdfilepath).renameTo(new File(s));
    }

    try (FileOutputStream stream = new FileOutputStream(acdfilepath);
         DataOutputStream writer = new DataOutputStream(stream)) {

      writer.writeInt(Integer.reverseBytes(-1111));
      writer.writeInt(Integer.reverseBytes(243591));
      long fall = 0;
      for (File f2 : inputFiles) {
        String f1 = f2.getName();
        log.debug(f1);

        writer.writeInt(Integer.reverseBytes(f1.length()));
        writer.writeBytes(f1);

        try (FileInputStream instream = new FileInputStream(f2)) {
          fall += instream.available();
          writer.writeInt(Integer.reverseBytes(instream.available()));
          byte[] unpacked_content = new byte[instream.available()];
          instream.read(unpacked_content);

          for (int i = 0; i < unpacked_content.length; i++) {
            int code = (unpacked_content[i] & 0xFF) + (key.charAt(i % key.length()) & 0xFF);
            if (code > 255)
              code = code - 256;
            writer.writeInt(Integer.reverseBytes(code));
          }
        }
      }
      log.debug("Packed " + inputFiles.length + " files, " + fall + " bytes -> " + carname + "/data.acd, " + stream.getChannel().size());
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public String getCarAcdDataFiles() {
    return "TODO!";
  }

  public static void main(String[] args) {
    String path = "/zstore/SteamLibrary/steamapps/common/assettocorsa/content/cars/ks_mclaren_650_gt3/data.acd";
    log.debug("ACD_file v0.9");


    File file = new File(path);
    String dirname = file.getAbsolutePath();

    if (path.toLowerCase().contains(File.separator + "data.acd")) {
      dirname = new File(dirname).getParentFile().getName();
      if (file.exists()) {
        acdUnpack(path, dirname);
      } else {
        log.debug("  File not found: " + path);
      }
    } else if (file.isDirectory()) {
      if (!path.endsWith(File.separator)) {
        path += File.separator;
      }
      if (new File(path + "car.ini").exists()) {
        dirname = new File(new File(path).getParent()).getName();
        path = new File(new File(path).getParent()).getParent() + File.separator;
      } else if (new File(path + "data" + File.separator + "car.ini").exists()) {
        dirname = new File(path).getName();
        path = new File(path).getParent() + File.separator;
      }
      acdPack(path + "data.acd", dirname);
    } else {
      log.debug("Invalid input file/folder: " + path + "\n");
      log.debug("  Usage: java -jar acd_file.jar [acd-file/car-folder]");
    }

  }
}