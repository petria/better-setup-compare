package com.airiot.fi.service.model.carselector;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SetupForCarSelection {

    private long id;

    private String setupIniFileName;

}
