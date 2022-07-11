package com.indigitalxp.services.customer.business.impl;

import com.indigitalxp.services.customer.business.CustomerServices;
import com.indigitalxp.services.customer.business.IndicatorServices;
import com.indigitalxp.services.customer.exception.GlobalExceptionHandler;
import com.indigitalxp.services.customer.mapper.IndicatorMapper;
import com.indigitalxp.services.customer.model.dto.request.IndicatorParam;
import com.indigitalxp.services.customer.model.dto.response.IndicatorResponse;
import com.indigitalxp.services.customer.model.dto.response.MonthsOfYearResponse;
import com.indigitalxp.services.customer.model.entity.Customer;
import io.reactivex.rxjava3.core.Single;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndicatorServicesImpl implements IndicatorServices {

  @Autowired
  private CustomerServices customerServices;

  @Autowired
  private IndicatorMapper indicatorMapper;

  @Override
  public Single<IndicatorResponse> getIndicators(IndicatorParam indicatorParam) {
    if (indicatorParam.getLessOrMore() != null) {
      return this.getWithHighestOrFewestQuantity(indicatorParam.getLessOrMore());
    } else if (indicatorParam.getMonth() != null && indicatorParam.getYear() != null) {
      return this.getByMonthAndYear(indicatorParam);
    } else {
      return this.getBirthRateEachMonth();
    }
  }

  @Override
  public Single<IndicatorResponse> getByMonthAndYear(IndicatorParam indicatorParam) {
    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .appendPattern("MMMM yyyy")
        .toFormatter(Locale.US);
    String month = indicatorParam.getMonth();
    String year = indicatorParam.getYear();
    YearMonth yearMonth = YearMonth.parse(month + " " + year , formatter);
    return customerServices.getCustomers()
        .filter(customer -> yearMonth.getYear() == customer.getDateBirth().getYear() &&
            yearMonth.getMonth() == customer.getDateBirth().getMonth())
        .count()
        .map(aLong -> indicatorMapper.toResponse(aLong.toString(), "Mes/Año con mayor cantidad de clientes nacidos", null));
  }

  @Override
  public Single<IndicatorResponse> getWithHighestOrFewestQuantity(String query) {
    return customerServices.findAll()
        .collect(Collectors.toList())
        .flatMap(customers -> this.getWithQuantity(customers, query))
        .map(treemap -> indicatorMapper.toResponse(
            treemap.getValue().toString(),
            "Mes/Año con " + query + " cantidad de clientes nacidos",
            null));
  }



  @Override
  public Single<IndicatorResponse> getBirthRateEachMonth() {
    return customerServices.findAll()
        .collect(Collectors.toList())
        .map(customer -> {
          return indicatorMapper.toResponse(
              null,
              "Tasa de Natalidad de cada mes",
              this.getRateEachMonth(customer));
        });
  }

  private Single<Map.Entry<String, Integer>> getWithQuantity(List<Customer> customers,
                                                             String query) {
    TreeMap<String, Integer> treeMap = new TreeMap<>();

    customers.forEach(customer -> {
      String date = customer.getDateBirth().getMonth() + "/" + customer.getDateBirth().getYear();
      if (!treeMap.isEmpty()) {
        treeMap.merge(date, 1, Integer::sum);
      } else {
        treeMap.put(date, 1);
      }
    });
    Stream<Map.Entry<String, Integer>> entryStream = treeMap.entrySet().stream();
    Optional<Map.Entry<String, Integer>> maxOrMin;
    if (query.equals("mayor")) {
      maxOrMin = entryStream.max(Map.Entry.comparingByValue());
    } else {
      maxOrMin = entryStream.min(Map.Entry.comparingByValue());
    }
    return maxOrMin.map(Single::just)
        .orElseGet(() -> Single.error(GlobalExceptionHandler.notFound()));
  }

  private MonthsOfYearResponse getRateEachMonth(List<Customer> customers) {
    TreeMap<String, Integer> treeMap = new TreeMap<>();
    customers.forEach(customer -> {
      String date = customer.getDateBirth().getMonth().toString();
      if (!treeMap.isEmpty()) {
        treeMap.merge(date, 1, Integer::sum);
      } else {
        treeMap.put(date, 1);
      }
    });
    return this.setAmountByMonth(treeMap);
  }

  public MonthsOfYearResponse setAmountByMonth(TreeMap<String, Integer> treeMap) {
    MonthsOfYearResponse monthsOfYearResponse = new MonthsOfYearResponse();
    for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
      String amount = entry.getValue().toString();
      switch (entry.getKey().toLowerCase()) {
        case "january":
          monthsOfYearResponse.setJanuary(amount);
          break;
        case "february":
          monthsOfYearResponse.setFebruary(amount);
          break;
        case "march":
          monthsOfYearResponse.setMarch(amount);
          break;
        case "april":
          monthsOfYearResponse.setApril(amount);
          break;
        case "may":
          monthsOfYearResponse.setMay(amount);
          break;
        case "june":
          monthsOfYearResponse.setJune(amount);
          break;
        case "july":
          monthsOfYearResponse.setJuly(amount);
          break;
        case "august":
          monthsOfYearResponse.setAugust(amount);
          break;
        case "september":
          monthsOfYearResponse.setSeptember(amount);
          break;
        case "october":
          monthsOfYearResponse.setOctober(amount);
          break;
        case "november":
          monthsOfYearResponse.setNovember(amount);
          break;
        case "december":
          monthsOfYearResponse.setDecember(amount);
          break;
        default:
          break;
      }
    }
    return monthsOfYearResponse;
  }
}
