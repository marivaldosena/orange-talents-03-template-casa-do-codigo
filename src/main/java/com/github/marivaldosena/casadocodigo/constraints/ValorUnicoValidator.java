package com.github.marivaldosena.casadocodigo.constraints;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, String> {
    @PersistenceContext
    private EntityManager manager;

    private String campo;
    private Class<?> entidade;

    @Override
    public void initialize(ValorUnico valorUnico) {
        this.campo = valorUnico.campo();
        this.entidade = valorUnico.entidade();
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        if (valor == null) {
            return false;
        }

        String jpql = "SELECT 1 FROM " + entidade.getName() + " WHERE " + campo.toLowerCase() + " = LOWER(:valor)";
        Query query = manager.createQuery(jpql);
        query.setParameter("valor", valor);

        List<?> listaDeRegistros = query.getResultList();

        return listaDeRegistros.isEmpty();
    }
}
