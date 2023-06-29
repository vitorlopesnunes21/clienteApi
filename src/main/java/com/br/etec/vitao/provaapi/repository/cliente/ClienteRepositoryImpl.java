package com.br.etec.vitao.provaapi.repository.cliente;

import com.br.etec.vitao.provaapi.model.Cliente;
import com.br.etec.vitao.provaapi.repository.filter.ClienteFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Cliente> filter(ClienteFilter clienteFilter, Pageable pageable){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
        Root<Cliente> root = criteria.from(Cliente.class);

        Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomecliente")));

        TypedQuery<Cliente> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, getTotal(clienteFilter));
    }

    private Long getTotal(ClienteFilter clienteFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Cliente> root = criteria.from(Cliente.class);

        Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomecliente")));

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable){
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPaginas = pageable.getPageSize();
        int primeiroRegistrosDaPagina = paginaAtual * totalRegistrosPorPaginas;

        query.setFirstResult(primeiroRegistrosDaPagina);
        query.setMaxResults(totalRegistrosPorPaginas);
    }

    private Predicate[] criarRestricoes(ClienteFilter clienteFilter, CriteriaBuilder builder, Root<Cliente> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(clienteFilter.getNomecliente())){
            predicates.add(
                    builder.like(
                            builder.lower(root.get("nomecliente")),
                            "%" + clienteFilter.getNomecliente().toLowerCase() + "%"
                    )
            );
        }

        return predicates.toArray(new Predicate[predicates.size()]);

    }
}
