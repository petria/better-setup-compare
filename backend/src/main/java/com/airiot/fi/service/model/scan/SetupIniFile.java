package com.airiot.fi.service.model.scan;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetupIniFile {

  private long id;

  private String carFolderName;

  private String trackFolderName;

  private String setupIniFileName;

  private String setupFullPath;


}
