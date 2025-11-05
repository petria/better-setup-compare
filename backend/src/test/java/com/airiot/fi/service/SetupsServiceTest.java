package com.airiot.fi.service;

import com.airiot.fi.model.ini.carselector.CompareSetupsResponse;
import com.airiot.fi.reader.SetupFilesReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetupsServiceTest {

  @Test
  void compareSetups() throws IOException {


    SetupFilesReader reader = new  SetupFilesReader();
    SetupsService setupsService = new SetupsService(reader);
    setupsService.doInitialScan();

    List<Long> setupIdList = new ArrayList<>();
    setupIdList.add(100L);
    setupIdList.add(200L);

    CompareSetupsResponse compareSetupsResponse = setupsService.compareSetups(setupIdList);

    int foo = 0;
  }
}