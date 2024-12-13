package com.ran.ams.service.implement;

import com.ran.ams.entity.Asset;
import com.ran.ams.entity.Category;
import com.ran.ams.entity.Condition;
import com.ran.ams.entity.Location;
import com.ran.ams.repository.AssetRepository;
import com.ran.ams.request.AssetCreateRequest;
import com.ran.ams.request.AssetUpdateRequest;
import com.ran.ams.service.AssetService;
import com.ran.ams.service.CategoryService;
import com.ran.ams.service.ConditionService;
import com.ran.ams.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;
    private final CategoryService categoryService;
    private final ConditionService conditionService;
    private final LocationService locationService;

    @Override
    public Page<Asset> findAll(int page, int size) {
        return assetRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        );
    }

    @Override
    public void create(AssetCreateRequest request) {
        if (assetRepository.existsByCode(request.getCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Asset with code '" + request.getCode() + "' already exists");
        }

        Category category = categoryService.findById(request.getCategoryId());
        Condition condition = conditionService.findById(request.getConditionId());
        Location location = locationService.findById(request.getLocationId());

        Asset asset = new Asset();
        asset.setName(request.getName());
        asset.setCode(request.getCode());
        asset.setPurchasePrice(request.getPurchasePrice());
        asset.setPurchaseDate(request.getPurchaseDate());
        asset.setValidityPeriod(request.getValidityPeriod());
        asset.setCategory(category);
        asset.setCondition(condition);
        asset.setLocation(location);

        assetRepository.save(asset);
    }

    @Override
    public Asset findById(int id) {
        return assetRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Asset not found"));
    }

    @Override
    public void update(int id, AssetUpdateRequest request) {
        Category category = categoryService.findById(request.getCategoryId());
        Condition condition = conditionService.findById(request.getConditionId());
        Location location = locationService.findById(request.getLocationId());

        Asset asset = assetRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Asset not found"));

        if (!asset.getCode().equals(request.getCode())){
            if (assetRepository.existsByCode(request.getCode())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Asset with code '" + request.getCode() + "' already exists");
            }
        }

        asset.setName(request.getName());
        asset.setCode(request.getCode());
        asset.setPurchasePrice(request.getPurchasePrice());
        asset.setPurchaseDate(request.getPurchaseDate());
        asset.setValidityPeriod(request.getValidityPeriod());
        asset.setCategory(category);
        asset.setCondition(condition);
        asset.setLocation(location);

        assetRepository.save(asset);
    }

    @Override
    public void delete(int id) {
        if (!assetRepository.existsById(id)) {
            throw new RuntimeException("Asset not found");
        }

        assetRepository.deleteById(id);
    }
}
