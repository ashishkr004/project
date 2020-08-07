package com.project.lms.services;

import com.project.lms.entities.dto.IssuedBookDto;

import java.util.List;
import java.util.Set;

public interface BookIssuedService {

    List<IssuedBookDto> issueBooks(List<IssuedBookDto> issuedBookDtoList, Long employeeId);

    List<IssuedBookDto> searchRecords(Set<Long> ids, Set<Long> bookIds, Set<Long> studentIds,
                                      Set<Long> issuerIds, Set<Long> receiverIds);

    List<IssuedBookDto> returnBooks(List<IssuedBookDto> issuedBookDtos, Long employeeId);
}
