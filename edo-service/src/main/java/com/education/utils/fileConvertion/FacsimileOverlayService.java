package com.education.utils.fileConvertion;


import java.util.Map;

public interface FacsimileOverlayService {
    byte[] overlay(Map<String, Object> map, byte[] bytes);
}
