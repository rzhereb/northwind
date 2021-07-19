package com.northwind.northwindrestapi.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Separator {

  COMA(",") {
    @Override
    public String generateErrorMessage() {
      return "Invalid data - wrong mask - number with coma is entered";
    }
  },
  SPACE(" ") {
    @Override
    public String generateErrorMessage() {
      return "Invalid data - wrong mask - number with space is entered";
    }
  },
  UNDERSCORE("_") {
    @Override
    public String generateErrorMessage() {
      return "Invalid data - wrong mask - number with underscore is entered";
    }
  },
  SLASH("/") {
    @Override
    public String generateErrorMessage() {
      return "Invalid data - wrong mask - number with slash is entered";
    }
  },
  DOT(".") {
    @Override
    public String generateErrorMessage() {
      return "Invalid data - wrong mask - number with dot is entered";
    }
  };


  private String separator;

  Separator(String separator) {
    this.separator = separator;
  }

  public abstract String generateErrorMessage();

  public static Separator findBySeparator(char separator) {
    final Optional<Separator> first = Arrays.stream(Separator.values())
        .filter(sepEnum -> sepEnum.getSeparator().equals(String.valueOf(separator)))
        .findFirst();
    return first.orElse(null);
  }
}
