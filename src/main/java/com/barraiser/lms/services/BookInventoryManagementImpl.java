package com.barraiser.lms.services;

import com.barraiser.lms.entities.dto.BookDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BookInventoryManagementImpl implements BookInventoryManagement {

    @Override
    public List<BookDto> addBooks(List<BookDto> bookDtoList) {

        return null;
    }

    @Override
    public List<BookDto> searchBooks(Set<Long> ids, Set<String> titles, Set<String> subjects,
                              Set<String> authors, Set<Boolean> isIssued) {
        return null;
    }

    @Override
    public Boolean deleteBooks(Set<Long> ids) {
        return false;
    }

    private Set<Long> getIds(Set<Long> ids) {
        if(Objects.isNull(ids) || ids.isEmpty()){
            return Collections.singleton(-1000L);
        }
        return ids;
    }

    private Set<String> getTitles(Set<String> titles) {
        if(Objects.isNull(titles) || titles.isEmpty()){
            return Collections.singleton("null");
        }
        return titles;
    }

    private Set<String> getSubjects(Set<String> subjects) {
        if(Objects.isNull(subjects) || subjects.isEmpty()){
            return Collections.singleton("null");
        }
        return subjects;
    }

    private Set<String> getAuthors(Set<String> authors) {
        if(Objects.isNull(authors) || authors.isEmpty()){
            return Collections.singleton("null");
        }
        return authors;
    }

    private Set<Boolean> getIsIssued(Set<Boolean> isIssued) {
        if(Objects.isNull(isIssued) || isIssued.isEmpty()){
            return Collections.singleton(true);
        }
        return isIssued;
    }
}
