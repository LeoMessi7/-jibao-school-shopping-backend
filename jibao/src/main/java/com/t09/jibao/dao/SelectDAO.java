package com.t09.jibao.dao;

import com.t09.jibao.domain.Feedback;
import com.t09.jibao.domain.Select;
import com.t09.jibao.domain.SelectPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectDAO extends JpaRepository<Select, SelectPK> {
}
