package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionDto {
    private UUID id;
    private Date created;
    private Date updated;
    private UUID entryId;
    private Map<String, Object> period;
    private HashMap<String, Object> data;
    private String type;
}
