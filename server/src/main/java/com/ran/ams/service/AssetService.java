package com.ran.ams.service;

import com.ran.ams.entity.Asset;
import com.ran.ams.request.AssetCreateRequest;
import com.ran.ams.request.AssetUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

public interface AssetService {
    Page<Asset> findAll(int page, int size);
    void create(AssetCreateRequest request);
    Asset findById(int id);
    void update(int id, AssetUpdateRequest request);
    void delete(int id);
}
