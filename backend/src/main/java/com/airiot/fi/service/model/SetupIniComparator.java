package com.airiot.fi.service.model;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetupIniComparator {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(SetupIniComparator.class);
  private final Map<String, String> configKeyMapping;

  public SetupIniComparator(Map<String, String> configKeyMapping) {
    this.configKeyMapping = configKeyMapping;
  }

  public Map<String, List<String>> compare(Map<String, String> base, Map<String, String> other) {

    Map<String, List<String>> differenceMap = new HashMap<>();
    for (String key : configKeyMapping.keySet()) {
      String valueBase = base.get(key);
      String valueOther = other.get(key);

      if (valueBase != null && valueOther != null) {
        if (valueBase.equals(valueOther)) {
          // same
        } else {
//                    log.debug("{} :: {} != {}", key, valueBase, valueOther);
          List<String> diff = new ArrayList<>();
          diff.add(valueBase);
          diff.add(valueOther);
          differenceMap.put(key, diff);
        }
      }
    }
    return differenceMap;
  }


}
