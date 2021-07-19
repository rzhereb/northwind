package com.northwind.northwindrestapi.controller;

import com.google.common.base.Strings;
import com.northwind.northwindrestapi.dto.Separator;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/date")
public class DateValidatorController {

  public static final String ERRORS_LIST = "errors";
  public static final String LATIN_ERROR_FORMAT = "Invalid %s - Latin letters";
  public static final String CYRILLIC_ERROR_FORMAT = "Invalid %s - Cyrillic letters";

  public static final String RESERVED_CHARS_ERROR_FORMAT = "Invalid %s - reserved characters";
  public static final String COMBINED_WITH_RESERVED_CHARS_ERROR_FORMAT = "Invalid %s - combined reserved characters with digits";
  public static final String[] RESERVED_CHARS = {"/", "%", "&", "*", "!", ".", ",", "^", "#", "@", "$", "?", "\\",
      "|", "[", "]", "(", ")", "{", "}", "_", "№", ";", ":", "'", "\""};
  public static final List<String> DATE_PARTS_NAMES = Arrays.asList("month", "day", "year");
  public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
  private static final String COMBINED_WITH_ALPHABETIC_CHARS_ERROR_FORMAT = "Invalid %s - combined alphabetic characters with digits";
  private static final String SPACE_IS_FOUND = "Invalid %s - Space is forbidden";

  @GetMapping
  public String page(Model model, HttpSession session) {
    Set<String> errors = (Set<String>) session.getAttribute(ERRORS_LIST);
    if (errors == null) {
      errors = new LinkedHashSet<>();
      session.setAttribute(ERRORS_LIST, errors);
    }
    model.addAttribute(ERRORS_LIST, errors);
    model.addAttribute("dateObject", new Request());
    return "index";
  }

  @PostMapping
  public String checkDate(Request request, HttpSession session) {
    final String date = request.getDate();
    Set<String> errorsInDate = validate(date);
    Set<String> errorsAttribute = (Set<String>) session.getAttribute(ERRORS_LIST);
    if (!errorsInDate.isEmpty()) {
      errorsAttribute.addAll(errorsInDate);
    }
    return "redirect:/date";
  }

  @DeleteMapping
  public String deleteErrors(HttpSession session) {
    session.removeAttribute(ERRORS_LIST);
    return "redirect:/date";
  }


  private Set<String> validate(String date) {
    Set<String> errors = new LinkedHashSet<>();
    if (Strings.isNullOrEmpty(date)) {
      errors.add("Empty value");
      return errors;
    }
    boolean isPatternWrong = checkPattern(date, errors);
    if (isPatternWrong) {
      return errors;
    }

    final String[] dateParts = date.split("-");
    if (dateParts.length != 3) {
      errors.add("Invalid data - wrong mask");
      return errors;
    }

    boolean isErrorFound = false;
    for (int i = 0; i < dateParts.length; i++) {
      if (dateParts[i].matches("[a-zA-Z]{2,5}")) {
        errors.add(String.format(LATIN_ERROR_FORMAT, DATE_PARTS_NAMES.get(i)));
        isErrorFound = true;
      }
      if (checkUTF8(dateParts[i])) {
        errors.add(String.format(CYRILLIC_ERROR_FORMAT, DATE_PARTS_NAMES.get(i)));
        isErrorFound = true;
      }
      if (dateParts[i].contains(" ")) {
        errors.add(String.format(SPACE_IS_FOUND, DATE_PARTS_NAMES.get(i)));
        isErrorFound = true;
      }
      if (containsAtLeastOneNumeric(dateParts[i])) {
        if (Arrays.stream(RESERVED_CHARS).anyMatch(dateParts[i]::contains)) {
          errors.add(String.format(COMBINED_WITH_RESERVED_CHARS_ERROR_FORMAT, DATE_PARTS_NAMES.get(i)));
          isErrorFound = true;
        }
        if (containsAtLeastOneAlphabetic(dateParts[i])) {
          errors.add(String.format(COMBINED_WITH_ALPHABETIC_CHARS_ERROR_FORMAT, DATE_PARTS_NAMES.get(i)));
          isErrorFound = true;
        }
      } else {
        if (Arrays.stream(RESERVED_CHARS).anyMatch(dateParts[i]::contains)) {
          errors.add(String.format(RESERVED_CHARS_ERROR_FORMAT, DATE_PARTS_NAMES.get(i)));
          isErrorFound = true;
        }
      }
    }

    if (!checkMonthRange(dateParts[0])) {
      errors.add("Invalid month - digits");
      isErrorFound = true;
    }
    if (!checkDayRange(dateParts[1])) {
      errors.add("Invalid day - digits");
      isErrorFound = true;
    }
    if (!checkYearRange(dateParts[2])) {
      errors.add("Invalid year - digits");
    }

    if (isErrorFound) {
      return errors;
    }

    LocalDate localDate = LocalDate.parse(date, DATE_TIME_FORMATTER);
    final Month month = localDate.getMonth();

    boolean checkedFebruary = false;
    if (Month.FEBRUARY.equals(month)) {
      final int dayOfMonth = Integer.parseInt(date.split("-")[1]);
      if (dayOfMonth > 28 && !localDate.isLeapYear()) {
        errors.add("For non-leap year maximum day in February is 28");
        checkedFebruary = true;
      } else if (localDate.isLeapYear() && dayOfMonth > 29) {
        errors.add("For leap year maximum day in February is 29");
        checkedFebruary = true;
      }
    }
    if (Month.JANUARY.equals(month)) {
      errors.add("Min month");
    } else if (Month.DECEMBER.equals(month)) {
      errors.add("Max month");
    } else {
      errors.add("Average month");
    }
    final int day = localDate.getDayOfMonth();
    if (day == 1) {
      errors.add("Min day");
    } else if (!checkedFebruary && (day == month.maxLength() || day == month.minLength())) {
      errors.add("Max day - " + day);
    } else {
      errors.add("Average day");
    }
    final int year = localDate.getYear();
    if (year == 1800) {
      errors.add("Min year");
    }
    if (year == 2050) {
      errors.add("Max year");
    }
    return errors;
  }

  private boolean containsAtLeastOneAlphabetic(String datePart) {
    final char[] chars = datePart.toCharArray();
    for (char aChar : chars) {
      if (Character.isAlphabetic(aChar)) {
        return true;
      }
    }
    return false;
  }

  private boolean containsAtLeastOneNumeric(String datePart) {
    final char[] chars = datePart.toCharArray();
    for (char aChar : chars) {
      if (Character.isDigit(aChar)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkPattern(String date, Set<String> errors) {
    if (date.length() == 8 && date.matches("[0-9]{8}")) {
      errors.add("Invalid data - wrong mask - only number is entered");
      return true;
    }
    if (date.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
      if (!date.matches(".{2}-.{2}-.{4}")) {
        char separator = date.charAt(2);
        if (separator != date.charAt(5)) {
          errors.add("Invalid data - wrong mask - different separators are used");
          return true;
        }
        final Separator sepEnum = Separator.findBySeparator(separator);
        if (sepEnum == null) {
          errors.add("Invalid data - wrong mask - number with " + separator + " is entered");
        } else {
          errors.add(sepEnum.generateErrorMessage());
        }
        return true;
      }
    }
    return false;
  }

  private boolean checkUTF8(String datePart) {
    for (int i = 0; i < datePart.length(); i++) {
      if (Character.UnicodeBlock.of(datePart.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkMonthRange(String month) {
    try {
      final int i = Integer.parseInt(month);
      return i > 0 && i < 13;
    } catch (NumberFormatException e) {
      return true;
    }
  }


  private boolean checkDayRange(String day) {
    try {
      final int i = Integer.parseInt(day);
      return i > 0 && i < 32;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  private boolean checkYearRange(String year) {
    try {
      final int i = Integer.parseInt(year);
      return i >= 1800 && i <= 2050;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  @Data
  static class Request {

    private String date;
  }

}
