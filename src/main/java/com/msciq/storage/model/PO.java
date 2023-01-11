package com.msciq.storage.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PO {
    Map<String, List> action;
    Map<String, ControlData> controlData;

}
