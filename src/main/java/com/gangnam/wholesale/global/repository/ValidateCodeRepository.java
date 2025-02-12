package com.gangnam.wholesale.global.repository;

import com.gangnam.wholesale.global.entity.ValidateCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidateCodeRepository extends CrudRepository<ValidateCode, String> {
    Optional<ValidateCode> findByValidateCode(String validateCode);
}