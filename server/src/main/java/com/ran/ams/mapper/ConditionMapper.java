package com.ran.ams.mapper;

import com.ran.ams.dto.ConditionDto;
import com.ran.ams.entity.Condition;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Component
public class ConditionMapper implements Function<Condition, ConditionDto> {
    @Override
    public ConditionDto apply(Condition condition) {
        return new ConditionDto(
                condition.getId(),
                condition.getName(),
                condition.getCreatedAt(),
                condition.getUpdatedAt()
        );
    }
}
