package com.telus.core.errorhandling.resource;

public enum LinkType {
	SELF("self"), ERROR_INFO("errorinfo");

  String value;

  LinkType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
