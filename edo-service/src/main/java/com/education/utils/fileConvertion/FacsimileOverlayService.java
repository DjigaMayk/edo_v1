package com.education.utils.fileConvertion;


import java.util.Map;

public interface FacsimileOverlayService {
    byte[] overlay(Map<String, Object> fileMap, Map<String, Object> facsimileMap);
}
