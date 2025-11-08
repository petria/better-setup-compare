package com.airiot.fi.service;

import com.airiot.fi.model.difference.CompareSetupsResponse;
import com.airiot.fi.model.ini.carselector.CompareSetupsResponseOld;
import com.airiot.fi.reader.SetupFilesReader;
import com.airiot.fi.service.acd.AcdFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.stream.Collectors;

import static com.airiot.fi.config.StaticConfig.AC_INSTALL_CONTENT_DIRECTORY;

class SetupsServiceTest {

  private static final Logger log = LoggerFactory.getLogger(SetupsServiceTest.class);


  @Test
  void compareSetups() throws IOException {


    SetupFilesReader reader = new  SetupFilesReader();
    SetupsService setupsService = new SetupsService(reader);
    setupsService.doInitialScan();

    List<Long> setupIdList = new ArrayList<>();
    setupIdList.add(3270L);
    setupIdList.add(3271L);

    CompareSetupsResponse compareSetupsResponseOld = setupsService.compareSetups(setupIdList);
    int foo = 0;

  }

//  @Test
  public void doSetupSectionMappings() throws IOException {

    SetupFilesReader reader = new  SetupFilesReader();
    AcdFileService acdFileService = new AcdFileService(reader);

    acdFileService.extractAllAcdFiles();
//    Map<String, String>  configKeyMapping = reader.readConfigKeysMappingIniFile(AC_CONFIG_KEYS_MAP_FILE);

  }

  @Test
  public void testReadAcdFile() throws IOException {
    SetupFilesReader reader = new  SetupFilesReader();
    AcdFileService acdFileService = new AcdFileService(reader);

    List<File> folders = reader.scanForFolders(AC_INSTALL_CONTENT_DIRECTORY + File.separator + "cars");
//    log.debug("Found {} car folders", folders.size());

    String section = "NONE";
    Map<String, Set<String>> sectionToSettingMap = new TreeMap<>();
    sectionToSettingMap.put(section, new TreeSet<>());

    Map<String, List<String>> map2 = new HashMap<>();
    map2.put(section, new ArrayList<>());

    for (File folder : folders) {
      String setupPath = folder.getAbsolutePath() + File.separator + "data" + File.separator + "setup.ini";
      try  {
        List<String> lines = acdFileService.readAcdFile(setupPath);
        log.debug("{} -> {} lines}", setupPath, lines.size());
        for (int i = 0; i < lines.size(); i++) {
          String line = lines.get(i);
          if (line.startsWith(";")) {
            if (lines.get(i + 1 ).equals("/////////////////////////////////////////////////////")) {
              section = line.substring(1);
//              log.debug("section: {}", section);
              Set<String> names = sectionToSettingMap.get(section);
              if (names == null) {
                names = new TreeSet<>();
                sectionToSettingMap.put(section, names);
              }
            }

          } else {
            if (line.startsWith("[") && line.contains("]")) {
              int idx1 = line.indexOf('[');
              int idx2 = line.indexOf(']');
              String name = line.substring(idx1 + 1, idx2);
              Set<String> names = sectionToSettingMap.get(section);
//              log.debug("name: {}", name);
              names.add(name);

              List<String> sections = map2.get(name);
              if (sections == null) {
                sections = new ArrayList<>();
                map2.put(name, sections);
              }
              sections.add(section);

            }
          }
        }
        section = "NONE";
      } catch (NoSuchFileException e) {
        log.error("No ACD data dir not found: {}", setupPath);
      }
    }
//    String json1 = mapper.writeValueAsString(sectionToSettingMap);
//    String json2 = mapper.writeValueAsString(map2);

//    log.debug("ACD_file json1: {}", json1);
//    log.debug("ACD_file json2: {}", json2);
    Map<String, String> mostMap = new TreeMap<>();
    for (String key : map2.keySet()) {
      List<String> strings = map2.get(key);

      Map<String, Long> counts = countUniqueStrings(strings);
      String most = findMostFrequent(counts);

      log.debug("{}: {}", key, most);

      mostMap.put(key, most);

    }

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(mostMap);
    log.debug("json: {}",  json);

  }

  public static String findMostFrequent(Map<String, Long> map) {
    return map.entrySet()
        .stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElse(null); // or throw, depending on your needs
  }

  public static Map<String, Long> countUniqueStrings(List<String> list) {
    return list.stream()
        .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
  }
}