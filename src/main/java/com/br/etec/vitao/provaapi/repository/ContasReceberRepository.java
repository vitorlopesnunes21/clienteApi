package com.br.etec.vitao.provaapi.repository;

import com.br.etec.vitao.provaapi.model.ContasReceber;
import com.br.etec.vitao.provaapi.repository.contasReceber.ContasReceberRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasReceberRepository extends JpaRepository<ContasReceber, Long>, ContasReceberRepositoryQuery {
}
