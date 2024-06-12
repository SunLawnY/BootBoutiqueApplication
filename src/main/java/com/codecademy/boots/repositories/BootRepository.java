package com.codecademy.boots.repositories;
import com.codecademy.boots.entities.Boot;
import com.codecademy.boots.enums.BootType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BootRepository extends CrudRepository<Boot, Integer> {
    List<Boot> findAllBySize(Float size);
    List<Boot> findAllByMaterial(String material);
    List<Boot> findAllByType(BootType type);
    List<Boot> findAllByQuantityGreaterThan(Integer quantity);
    List<Boot> findAllBySizeAndQuantityGreaterThan(Float size, Integer quantity);
    List<Boot> findAllByTypeAndQuantityGreaterThan(BootType type, Integer quantity);
    List<Boot> findAllByTypeAndSize(BootType type, Float size);
    List<Boot> findAllByTypeAndSizeAndQuantityGreaterThan(BootType type, Float size, Integer quantity);
    List<Boot> findAllByMaterialAndType(String material, BootType type);
    List<Boot> findAllByMaterialAndQuantityGreaterThan(String material, Integer quantity);
    List<Boot> findAllByMaterialAndTypeAndSize(String material, BootType type, Float size);
    List<Boot> findAllByMaterialAndTypeAndQuantityGreaterThan(String material, BootType type, Integer quantity);
    List<Boot> findAllByMaterialAndTypeAndSizeAndQuantityGreaterThan(String material, BootType type, Float size, Integer quantity);
}
