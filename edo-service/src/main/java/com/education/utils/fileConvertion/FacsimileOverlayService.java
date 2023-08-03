package com.education.utils.fileConvertion;


import java.util.Map;

public interface FacsimileOverlayService {
    Map<String, Object> overlay(Map<String, Object> map, byte[] bytes);
}
