package com.br.etec.vitao.provaapi.repository.contasReceber;

import com.br.etec.vitao.provaapi.model.ContasReceber;
import com.br.etec.vitao.provaapi.repository.filter.ContasReceberFilter;
import com.br.etec.vitao.provaapi.repository.projections.ContasReceberDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ContasReceberRepositoryImpl implements ContasReceberRepositoryQuery {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ContasReceberDTO> filter(ContasReceberFilter contasReceberFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ContasReceberDTO> criteria = builder.createQuery(ContasReceberDTO.class);
        Root<ContasReceber> root = criteria.from(ContasReceber.class);

        criteria.select(
                builder.construct(ContasReceberDTO.class,
                        root.get("id"),
                        root.get("cliente").get("nomecliente"),
                        root.get("dataconta")
                )
        );

        Predicate[] predicates = createRestrictions(contasReceberFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("cliente").get("nomecliente")));

        TypedQuery<ContasReceberDTO> query = manager.createQuery(criteria);
        adicionarRestricaoDePaginacao(query, pageable);

        return new PageImpl<ContasReceberDTO>(query.getResultList(), pageable, getTotal(contasReceberFilter));
    }

    private Predicate[] createRestrictions(ContasReceberFilter contasReceberFilter, CriteriaBuilder builder, Root<ContasReceber> root) {
        List<Predicate> predicates = new ArrayList<>();

        Path<String> clienteNome = root.get("cliente").get("nomecliente");
        String nomeCliente = contasReceberFilter.getNomecliente();

        if (!StringUtils.isEmpty(nomeCliente)) {
            predicates.add(builder.like(
                            builder.lower(clienteNome),
                            "%" + nomeCliente.toLowerCase() + '%'
                    )
            );
        }

        if (contasReceberFilter.getDataconta() != null) {
            predicates.add(builder.equal(
                            root.get("dataconta"),
                            contasReceberFilter.getDataconta()
                    )
            );
        }

        return predicates.toArray((new Predicate[predicates.size()]));
    }

    private void adicionarRestricaoDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRegistersPerPage = pageable.getPageSize();
        int firstPageRegister = currentPage * totalRegistersPerPage;

        query.setFirstResult(firstPageRegister);
        query.setMaxResults(totalRegistersPerPage);
    }

    private Long getTotal(ContasReceberFilter contasReceberFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<ContasReceber> root = criteria.from(ContasReceber.class);

        Predicate[] predicates = createRestrictions(contasReceberFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("cliente").get("nomecliente")));

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }
}
