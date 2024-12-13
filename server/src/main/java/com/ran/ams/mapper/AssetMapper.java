package com.ran.ams.mapper;

import com.ran.ams.dto.AssetDto;
import com.ran.ams.entity.Asset;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Component
@RequiredArgsConstructor
public class AssetMapper implements Function<Asset, AssetDto> {
    private final CategoryMapper categoryMapper;
    private final ConditionMapper conditionMapper;
    private final LocationMapper locationMapper;

    @Override
    public AssetDto apply(Asset asset) {
        return new AssetDto(
                asset.getId(),
                asset.getName(),
                asset.getCode(),
                asset.getPurchasePrice(),
                asset.getPurchaseDate(),
                asset.getValidityPeriod(),
                categoryMapper.apply(asset.getCategory()),
                conditionMapper.apply(asset.getCondition()),
                locationMapper.apply(asset.getLocation()),
                asset.getCreatedAt(),
                asset.getUpdatedAt()
        );
    }
}
