package com.barber.demo.dao;

import com.barber.demo.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository<Rol, Integer> {
}