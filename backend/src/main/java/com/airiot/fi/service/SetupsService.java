package com.airiot.fi.service;

import com.airiot.fi.model.ini.carselector.*;
import com.airiot.fi.model.ini.scan.*;
import com.airiot.fi.reader.SetupFilesReader;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.airiot.fi.config.StaticConfig.*;

@Service
public class SetupsService {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(SetupsService.class);

  private final SetupFilesReader reader;

  private Map<String, String> configKeyMapping;
  private Map<String, String> configKeyJsonMapping;


  private Map<String, SetupScanResults> resultsMap = new HashMap<>();

  private Map<String, Car> setupsMap = new HashMap<>();

  private long carIdCounter = 0;
  private long trackIdCounter = 0;


  private Map<Long, SetupIniFile> setupIdMap = new HashMap<>();
  private Map<Long, Car> carIdMap = new HashMap<>();

  private SetupIniFileScanStats stats = new SetupIniFileScanStats();

  public SetupsService(SetupFilesReader reader) throws IOException {
    this.reader = reader;
  }

  @PostConstruct
  public void doInitialScan() {
    try {
      this.stats = scanForSetupIniFiles(AC_CONFIG_KEYS_MAP_FILE, AC_SETUP_LOCAL_BASE_DIR);
      log.debug("Initial scan done: {}", stats);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public SetupIniFileScanStats getStats() {
    return stats;
  }

  public synchronized SetupIniFileScanStats scanForSetupIniFiles(String configKeysMapFile, String setupLocalBaseDir) throws IOException {

    SetupIniFileScanStats stats = new SetupIniFileScanStats();
    stats.setConfigKeyMapFile(configKeysMapFile);

    long start = System.currentTimeMillis();


    this.carIdCounter = 0;
    this.trackIdCounter = 0;


    configKeyMapping = reader.readConfigKeysMappingIniFile(AC_CONFIG_KEYS_MAP_FILE);
    configKeyJsonMapping = reader.readConfigKeysMappingJson(AC_CONFIG_KEYS_MAP_JSON_FILE);

    String scanDir = setupLocalBaseDir;
    stats.setScanDir(scanDir);

    log.debug("Scan dir   : {}", scanDir);

    List<File> carDirFiles = reader.scanForFolders(scanDir);

    Set<String> carDirNames = new HashSet<>();
    Set<String> trackDirNames = new HashSet<>();
    int uniqueSetupFiles = 0;

    for (File carFile : carDirFiles) {
      String carFileName = carFile.getName();

      Car carModel = this.setupsMap.get(carFileName);
      if (carModel == null) {
        this.carIdCounter++;
        carModel = new Car();
        carModel.setId(carIdCounter);
        carModel.setCarName(carFileName);
        carModel.setCarFolderName(carFileName);
        this.setupsMap.put(carFileName, carModel);
        this.carIdMap.put(carModel.getId(), carModel);
      }

      List<File> trackFiles = reader.scanForFolders(carFile.getAbsolutePath());
      for (File file : trackFiles) {
        String path = file.getAbsolutePath();
        String[] split = path.split("/");

        String car = split[split.length - 2];
        if (car.contains("bmw_z4_gt3")) {
          int foo = 0;
        }
        String track = split[split.length - 1];
        if (track.equals("generic")) {
          // skip generic dir
          continue;
        }
        carDirNames.add(car);
        trackDirNames.add(track);
        String key = String.format("%s__%s", car, track);

        String[] iniFiles = reader.scanForIniFiles(path);

        SetupScanResults setupScanResults
            = SetupScanResults.builder()
            .carFolder(car)
            .trackFolder(track)
            .iniFilesMap(new HashMap<>())
            .setupIdToIniFileMap(new HashMap<>())
            .build();
        carModel.setIniFileCount(iniFiles.length);

        for (String iniFile : iniFiles) {
          uniqueSetupFiles++;
          Track trackModel = carModel.getTracksWithSetup().get(track);
          if (trackModel == null) {
            this.trackIdCounter++;
            trackModel = new Track();
            trackModel.setId(this.trackIdCounter);
            trackModel.setTrackFolderName(track);
            trackModel.setTrackName(track);
            carModel.getTracksWithSetup().put(track, trackModel);
          }
          String pathToIni = path + "/" + iniFile;
          trackModel.getIniFilesMap().put(iniFile, pathToIni);

          setupScanResults.getIniFilesMap().put(iniFile, pathToIni);


          SetupIniFile setupIniFile = new SetupIniFile();
          setupIniFile.setSetupFullPath(pathToIni);
          setupIniFile.setId(uniqueSetupFiles);
          setupIniFile.setCarFolderName(carModel.getCarFolderName());
          setupIniFile.setTrackFolderName(trackModel.getTrackFolderName());
          setupIniFile.setSetupIniFileName(iniFile);

          setupScanResults.getSetupIdToIniFileMap().put(setupIniFile.getId(), setupIniFile);

          this.setupIdMap.put(setupIniFile.getId(), setupIniFile);

        }


        resultsMap.put(key, setupScanResults);

      }

    }
    long scanTime = System.currentTimeMillis() - start;

    log.debug("Setup INIs : {}", setupIdMap.size());
    log.debug("Car dirs   : {}", carDirNames.size());
    log.debug("Track dirs : {}", trackDirNames.size());
    log.debug("Scan time  : {} ms", scanTime);

    stats.setUniqueSetupFiles(setupIdMap.size());
    stats.setCarDirs(carDirNames.size());
    stats.setTrackDirs(trackDirNames.size());
    stats.setScanTime(scanTime);

    stats.setUniqueSetupFiles(uniqueSetupFiles);
    stats.setScanDone(true);

    return stats;
  }

  public Map<String, String> getConfigKeyMapping() {
    return configKeyMapping;
  }

  public Map<String, SetupScanResults> getResultsMap() {
    return resultsMap;
  }

  public Map<String, Car> getSetupsMap() {
    return setupsMap;
  }

  public List<Car> getCarList() {
    List<Car> carList = new ArrayList<>();
    for (Car car : this.setupsMap.values()) {
      if (car.getTracksWithSetup().size() > 0) {
        carList.add(car);
      }
    }
    carList.sort(Comparator.comparing(Car::getCarName));
    return carList;
  }

  public List<CarForSelection> getCarListForSelection() {
    List<Car> carList1 = getCarList();
    return carList1.stream().map(this::mapToCarForSelection).collect(Collectors.toList());
  }

  private CarForSelection mapToCarForSelection(Car car) {
    CarForSelection forSelection = null;
    if (car != null) {
      forSelection = CarForSelection.builder()
          .id(car.getId())
          .carTracksWithSetup(car.getTracksWithSetup().size())
          .carFolderName(car.getCarFolderName())
          .carName(car.getCarName())
          .build();
    }
    return forSelection;
  }


  public List<TrackForCarSelection> getTrackByFolderName(String carFolderName) {
    Car car = this.setupsMap.get(carFolderName);
    List<TrackForCarSelection> list = new ArrayList<>();
    if (car != null) {
      Stream<String> sorted = car.getTracksWithSetup().keySet().stream().sorted();
      for (String trackName : sorted.toList()) {
        Track track = car.getTracksWithSetup().get(trackName);

        TrackForCarSelection forCarSelection
            = TrackForCarSelection.builder()
            .id(track.getId())
            .trackFolderName(track.getTrackFolderName())
            .trackName(track.getTrackName())
            .iniFileCount(track.getIniFilesMap().size())
            .build();
        list.add(forCarSelection);
      }
    }

    return list;
  }

  public List<SetupForCarSelection> getSetupListForCarAndTrack(String carFolderName, String trackFolderName) {
    List<SetupForCarSelection> list = new ArrayList<>();

    String key = String.format("%s__%s", carFolderName, trackFolderName);

    SetupScanResults setupScanResults = this.resultsMap.get(key);
    if (setupScanResults != null) {
      Stream<Long> sorted = setupScanResults.getSetupIdToIniFileMap().keySet().stream().sorted();
      for (Long setupId : sorted.toList()) {
        SetupIniFile setupIniFile = setupScanResults.getSetupIdToIniFileMap().get(setupId);
        SetupForCarSelection forCarSelection = new SetupForCarSelection(setupIniFile.getId(), setupIniFile.getSetupIniFileName());
        forCarSelection.setCarFolderName(carFolderName);
        forCarSelection.setTrackFolderName(trackFolderName);
        list.add(forCarSelection);
      }
    }
    return list;
  }

  public CarForSelection getCarById(long id) {
    Car car = carIdMap.get(id);
    return mapToCarForSelection(car);
  }

  public String getSetupById(long setupId) {
    SetupIniFile setupIniFile = this.setupIdMap.get(setupId);
    StringBuilder sb = new StringBuilder();
    if (setupIniFile != null) {
      try {
        reader.readSetupFile(setupIniFile.getSetupFullPath()).forEach(line -> sb.append(line).append("\n"));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return sb.toString();
  }


  private Map<String, String> getSetupIniValues(long setupId) throws IOException {
/*    String[] split = selected.split(" / ");
    if (split.length != 3) {
      return new HashMap<>();
    }
    String key = String.format("%s__%s", split[0], split[1]);
    SetupScanResults setupScanResults = resultsMap.get(key);
    String iniBase = setupScanResults.getIniFilesMap().get(split[2]);
*/

    SetupIniFile setupIniFile = this.setupIdMap.get(setupId);
    log.debug("read ini values: {}", setupIniFile.getSetupIniFileName());
    return reader.parseValues(reader.readSetupFile(setupIniFile.getSetupFullPath()));
  }

  class IniValues {
    private long setupId;
    Map<String, String> iniValueMap = new HashMap<>();

  }


  public CompareSetupsResponse compareSetups(List<Long> setupIdList) throws IOException {

    List<CompareDifference> differenceList = new ArrayList<>();
    Map<String, String> baseValues = getSetupIniValues(setupIdList.getFirst());

    List<Map<String, String>> otherValuesList = new ArrayList<>();
    for (int i = 0; i < setupIdList.size(); i++) {
      if (i == 0) {
        continue; // skip 1st which is base values
      }
      Map<String, String> otherValues = getSetupIniValues(setupIdList.get(i));
      otherValuesList.add(otherValues);
    }

    if (baseValues != null && !otherValuesList.isEmpty()) {

      List<Map<String, List<String>>> differenceMapList = new ArrayList<>();
      for (Map<String, String> otherValues : otherValuesList) {
        SetupIniComparator comparator = new SetupIniComparator(configKeyMapping, configKeyJsonMapping);
        Map<String, List<String>> differenceMap = comparator.compare(baseValues, otherValues);
        differenceMapList.add(differenceMap);
      }


      Stream<String> sortedKeys = reader.getConfigKeyGroups().keySet().stream().sorted();
      for (String groupKey : sortedKeys.toList()) {

        Set<String> configKeyStrings = reader.getConfigKeyGroups().get(groupKey);
        for (String configKey : configKeyStrings) {
          String hasDiff = "";

          Map<String, List<String>> differenceMap = differenceMapList.get(0);
          List<String> difference = differenceMap.get(configKey);

          List<String> list = new ArrayList<>();
          list.add(groupKey);
          list.add(configKey);

          if (difference != null) {
            list.add(difference.get(0));
            list.add(difference.get(1));

            hasDiff += "1";

          } else {
            hasDiff += "0";
            list.add("-");
            list.add("-");
          }

          for (int i = 0; i < differenceMapList.size(); i++) {
            if (i == 0) {
              continue; // SKIP 1st already handled
            }
            differenceMap = differenceMapList.get(i);
            difference = differenceMap.get(configKey);
            if (difference != null) {
              list.add(difference.get(1));
              hasDiff += "1";
            } else {
              hasDiff += "0";
              list.add("-");
            }
          }
          if (hasDiff.indexOf("0") == -1) {
            differenceList.add(CompareDifference.builder().differences(list).build());
          }

        }
      }
    }

    return CompareSetupsResponse.builder().differences(differenceList).build();
  }

}
