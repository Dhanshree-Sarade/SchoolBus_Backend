package com.ezio.Bus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezio.Bus.Entity.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    Parent findByEmailAndPassword(String email, String password);

}
