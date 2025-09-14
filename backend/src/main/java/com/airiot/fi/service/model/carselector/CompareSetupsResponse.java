package com.airiot.fi.service.model.carselector;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CompareSetupsResponse {

  private List<CompareDifference> differences;

}
