package com.pse.summary.service;

import java.util.List;

import com.pse.summary.dto.SummaryItem;

public interface SummaryService {

    List<SummaryItem> getSummery(String[] codes);

}
