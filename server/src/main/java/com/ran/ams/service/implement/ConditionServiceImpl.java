package com.ran.ams.service.implement;

import com.ran.ams.entity.Condition;
import com.ran.ams.repository.ConditionRepository;
import com.ran.ams.request.ConditionCreateRequest;
import com.ran.ams.request.ConditionUpdateRequest;
import com.ran.ams.service.ConditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Service
@RequiredArgsConstructor
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;

    @Override
    public Page<Condition> findAll(int offset, int limit) {
        return conditionRepository.findAll(PageRequest
                .of(offset, limit, Sort.by(Sort.Direction.DESC, "createdAt"))
        );
    }

    @Override
    public void create(ConditionCreateRequest request) {
        if (conditionRepository.existsByName(request.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Condition already exists");
        }

        Condition condition = new Condition();
        condition.setName(request.getName());

        conditionRepository.save(condition);
    }

    @Override
    public Condition findById(int id) {
        if (!conditionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Condition not found");
        }

        return conditionRepository.findById(id).orElse(null);
    }

    @Override
    public void update(int id, ConditionUpdateRequest request) {
        Condition condition = conditionRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Condition not found"));

        condition.setName(request.getName());
        conditionRepository.save(condition);
    }

    @Override
    public void delete(int id) {
        if (!conditionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Condition not found");
        }

        conditionRepository.deleteById(id);
    }
}
