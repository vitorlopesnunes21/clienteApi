package com.br.etec.vitao.provaapi.repository;

import com.br.etec.vitao.provaapi.model.Cliente;
import com.br.etec.vitao.provaapi.repository.cliente.ClienteRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery {
}
