package com.airiot.fi.service.acd;

import com.airiot.fi.reader.SetupFilesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.airiot.fi.config.StaticConfig.AC_INSTALL_CONTENT_DIRECTORY;

public class AcdFileService {

  private static final Logger log = LoggerFactory.getLogger(AcdFileService.class);


  private final SetupFilesReader reader;

  public AcdFileService(SetupFilesReader reader) {
    this.reader = reader;
  }

  public void extractAllAcdFiles() throws IOException {

    List<File> folders = reader.scanForFolders(AC_INSTALL_CONTENT_DIRECTORY + File.separator + "cars");
    log.debug("Found {} car folders", folders.size());
    for (File folder : folders) {
      String absolutePath = folder.getAbsolutePath() + File.separator + "data.acd";
      log.debug("Handle acd file: {}", absolutePath);
      AcdFile.handleAcdFile(absolutePath);
    }

  }

  public List<String> readAcdFile(String path) throws IOException {
    return Files.readAllLines(Paths.get(path));
  }

}
