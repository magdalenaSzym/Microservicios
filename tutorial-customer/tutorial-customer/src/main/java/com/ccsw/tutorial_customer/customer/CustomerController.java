package com.ccsw.tutorial_customer.customer;

import com.ccsw.tutorial_customer.customer.model.Customer;
import com.ccsw.tutorial_customer.customer.model.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Customer", description = "API of Customers")
@RequestMapping(value = "/customer")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ModelMapper mapper;

    /**
     *Método para recuperar todos los {@link Customer}
     *
     * @return {@link List} de {@link CustomerDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Customers")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CustomerDto> findAll() {

        List<Customer> customers = this.customerService.findAll();

        return customers.stream().map(e -> mapper.map(e, CustomerDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para crear o actualizar un {@link Customer}
     *
     * @param id de la entidad
     * @param dto datos del la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Customer")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CustomerDto dto) throws Exception {

        this.customerService.save(id, dto);
    }

    @Operation(summary = "Delete", description = "Method that deletes a Customer")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) throws Exception {

        this.customerService.delete(id);
    }
}
