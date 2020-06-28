package com.jpop4.repository;

import com.jpop4.repository.entity.Book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BookRepository extends CrudRepository<Book, BigInteger> {
}
