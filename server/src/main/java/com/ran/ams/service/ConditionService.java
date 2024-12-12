package com.ran.ams.service;

import com.ran.ams.entity.Condition;
import com.ran.ams.request.ConditionCreateRequest;
import com.ran.ams.request.ConditionUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

public interface ConditionService {
    Page<Condition> findAll(int offset, int limit);
    void create(ConditionCreateRequest request);
    Condition findById(int id);
    void update(int id, ConditionUpdateRequest request);
    void delete(int id);
}
