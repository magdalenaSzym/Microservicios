package com.ccsw.tutorial_customer.customer;

import com.ccsw.tutorial_customer.customer.model.Customer;
import com.ccsw.tutorial_customer.customer.model.CustomerDto;

import java.util.List;

public interface CustomerService {

    /**
     * Recupera una {@link Customer} a partir de su Id
     *
     * @param id PK de la entidad
     * @return {@link Customer}
     */
    Customer get(Long id);

    /**
     *Método que recupera todas las {@link Customer}
     *
     * @return {@link List} de {@link Customer}
     */
    List<Customer> findAll();

    /**
     * Método para gusrdar o actualizar una {@link Customer}
     *
     * @param id PK de la entidad
     * @param dto los datos de la entidad
     */
    void save(Long id, CustomerDto dto) throws Exception;

    /**
     * Método para borrar una {@link Customer}
     *
     * @param id PK de la entidad
     * @throws Exception exepción si al intentar borrar no se encuentra una {@link Customer}
     */
    void delete(Long id) throws Exception;
}
