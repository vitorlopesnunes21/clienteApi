package com.br.etec.vitao.provaapi.repository.contasReceber;

import com.br.etec.vitao.provaapi.repository.filter.ContasReceberFilter;
import com.br.etec.vitao.provaapi.repository.projections.ContasReceberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContasReceberRepositoryQuery {
    public Page<ContasReceberDTO> filter(ContasReceberFilter contasReceberFilter, Pageable pageable);
}

