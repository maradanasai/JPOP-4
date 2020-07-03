package com.jpop4.domain.repository;

import com.jpop4.domain.common.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends CrudRepository<User, BigInteger> {
}
