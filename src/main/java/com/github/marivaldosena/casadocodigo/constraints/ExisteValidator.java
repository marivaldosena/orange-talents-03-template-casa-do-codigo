package com.github.marivaldosena.casadocodigo.constraints;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteValidator implements ConstraintValidator<Existe, String> {
    @PersistenceContext
    private EntityManager manager;

    private Class<?> entidade;
    private String campo;

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        if (valor == null) {
            return false;
        }

        String jpql = "SELECT 1 FROM " + entidade.getName() + " WHERE LOWER(" + campo + ") = :valor";
        Query query = manager.createQuery(jpql);
        query.setParameter("valor", valor.toLowerCase());
        List<?> listaDeResultados = query.getResultList();
        return !listaDeResultados.isEmpty();
    }

    @Override
    public void initialize(Existe constraint) {
        this.campo = constraint.campo();
        this.entidade = constraint.entidade();
    }
}
