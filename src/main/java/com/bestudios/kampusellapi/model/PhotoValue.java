package com.bestudios.kampusellapi.model;

import lombok.Data;

import java.util.List;

@Data
public class PhotoValue {
    List<String> texts;
    List<String> labels;
}
