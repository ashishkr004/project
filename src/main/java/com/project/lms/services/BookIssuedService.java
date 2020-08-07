package com.project.lms.services;

import com.bounce.utils.dbmodels.information_schema.domains.TimeStamp;
import com.project.lms.entities.dao.Book;
import com.project.lms.entities.dao.Employee;
import com.project.lms.entities.dao.IssuedBook;
import com.project.lms.entities.dao.Student;
import com.project.lms.entities.dto.IssuedBookDto;

import java.util.List;
import java.util.Set;

public interface BookIssuedService {

    List<IssuedBookDto> issueBooks(List<IssuedBookDto> issuedBookDtoList, Long employeeId);

    List<IssuedBookDto> searchRecords(Set<Long> ids, Set<Long> bookIds, Set<Long> studentIds,
                                      Set<Long> issuerIds, Set<Long> receiverIds);

    List<IssuedBookDto> returnBooks(Set<IssuedBookDto> issuedBookDtos, Long employeeId);
}
