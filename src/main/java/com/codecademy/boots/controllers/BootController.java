package com.codecademy.boots.controllers;

import java.lang.Iterable;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;
import java.util.Optional;

import com.codecademy.boots.entities.Boot;
import com.codecademy.boots.repositories.BootRepository;
import com.codecademy.boots.enums.BootType;
import com.codecademy.boots.exceptions.QueryNotSupportedException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/boots")
public class BootController {

    private final BootRepository bootRepository;

    @Autowired
    public BootController(final BootRepository bootRepository) {
        this.bootRepository = bootRepository;
    }

    @GetMapping("/")
    public Iterable<Boot> getAllBoots() {
        Iterable<Boot> boot = this.bootRepository.findAll();
        return boot;
    }

    @GetMapping("/types")
    public List<BootType> getBootTypes() {
        return Arrays.asList(BootType.values());
    }

    @PostMapping("/")
    public Boot addBoot(@RequestBody Boot boot) {
        return this.bootRepository.save(boot);
    }

    @DeleteMapping("/{id}")
    public Boot deleteBoot(@PathVariable("id") Integer id) {
        Optional<Boot> bootDeleteOptional = this.bootRepository.findById(id);
        if (!bootDeleteOptional.isPresent()){
            return null;
        } else {
            Boot deleteBoot = bootDeleteOptional.get();
            this.bootRepository.delete(deleteBoot);
            return deleteBoot;
        }
    }

    @PutMapping("/{id}/quantity/increment")
    public Boot incrementQuantity(@PathVariable("id") Integer id) {
        Optional<Boot> bootIncreaseOptional = this.bootRepository.findById(id);
        if (!bootIncreaseOptional.isPresent()){
            return null;
        } else {
            Boot increaseBoot = bootIncreaseOptional.get();
            increaseBoot.setQuantity(increaseBoot.getQuantity()+1);
            this.bootRepository.save(increaseBoot);
            return increaseBoot;
        }
    }

    @PutMapping("/{id}/quantity/decrement")
    public Boot decrementQuantity(@PathVariable("id") Integer id) {
        Optional<Boot> bootDecreaseOptional = this.bootRepository.findById(id);
        if (!bootDecreaseOptional.isPresent()){
            return null;
        } else {
            Boot decreaseBoot = bootDecreaseOptional.get();
            decreaseBoot.setQuantity(decreaseBoot.getQuantity()-1);
            this.bootRepository.save(decreaseBoot);
            return decreaseBoot;
        }
    }

    @GetMapping("/search")
    public List<Boot> searchBoots(@RequestParam(required = false) String material,
                                  @RequestParam(required = false) BootType type, @RequestParam(required = false) Float size,
                                  @RequestParam(required = false, name = "quantity") Integer minQuantity) throws QueryNotSupportedException {
        if (Objects.nonNull(material)) {
            if (Objects.nonNull(type) && Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a material, type, size, and minimum
                // quantity
                return this.bootRepository.findAllByMaterialAndTypeAndSizeAndQuantityGreaterThan(material, type, size, minQuantity);
            } else if (Objects.nonNull(type) && Objects.nonNull(size)) {
                // call the repository method that accepts a material, size, and type
                return this.bootRepository.findAllByMaterialAndTypeAndSize(material, type, size);
            } else if (Objects.nonNull(type) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a material, a type, and a minimum
                // quantity
                return this.bootRepository.findAllByMaterialAndTypeAndQuantityGreaterThan(material, type, minQuantity);
            } else if (Objects.nonNull(type)) {
                // call the repository method that accepts a material and a type
                return this.bootRepository.findAllByMaterialAndType(material, type);
            } else {
                // call the repository method that accepts only a material
                return this.bootRepository.findAllByMaterial(material);
            }
        } else if (Objects.nonNull(type)) {
            if (Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a type, size, and minimum quantity
                return this.bootRepository.findAllByTypeAndSizeAndQuantityGreaterThan(type, size, minQuantity);
            } else if (Objects.nonNull(size)) {
                // call the repository method that accepts a type and a size
                return this.bootRepository.findAllByTypeAndSize(type, size);
            } else if (Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a type and a minimum quantity
                return this.bootRepository.findAllByTypeAndQuantityGreaterThan(type, minQuantity);
            } else {
                // call the repository method that accept only a type
                return this.bootRepository.findAllByType(type);
            }
        } else if (Objects.nonNull(size)) {
            if (Objects.nonNull(minQuantity)) {
                // call the repository method that accepts a size and a minimum quantity
                return this.bootRepository.findAllBySizeAndQuantityGreaterThan(size, minQuantity);
            } else {
                // call the repository method that accepts only a size
                return this.bootRepository.findAllBySize(size);
            }
        } else if (Objects.nonNull(minQuantity)) {
            // call the repository method that accepts only a minimum quantity
            return this.bootRepository.findAllByQuantityGreaterThan(minQuantity);
        } else {
            throw new QueryNotSupportedException("This query is not supported! Try a different combination of search parameters.");
        }
    }
}