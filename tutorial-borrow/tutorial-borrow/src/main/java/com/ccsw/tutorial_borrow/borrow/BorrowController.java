package com.ccsw.tutorial_borrow.borrow;

import com.ccsw.tutorial_borrow.borrow.model.Borrow;
import com.ccsw.tutorial_borrow.borrow.model.BorrowDto;
import com.ccsw.tutorial_borrow.borrow.model.BorrowSearchDto;
import com.ccsw.tutorial_borrow.customer.CustomerClient;
import com.ccsw.tutorial_borrow.customer.model.CustomerDto;
import com.ccsw.tutorial_borrow.game.GameClient;
import com.ccsw.tutorial_borrow.game.model.GameDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Borrow", description = "API of Borrow")
@RequestMapping(value = "/borrow")
@RestController
@CrossOrigin(origins = "*")
public class BorrowController {

    @Autowired
    BorrowService borrowService;

    @Autowired
    GameClient gameClient;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar un listado paginado de {@link Borrow}
     *
     *
     * @return {@link Page} de {@link BorrowDto}
     */
    @Operation(summary = "Find Page", description = "Method that return a page of Borrows")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<BorrowDto> findPage(@RequestBody BorrowSearchDto dto, @RequestParam(value = "gameId", required = false) Long idGame, @RequestParam(value = "customerId", required = false) Long idCustomer,
            @RequestParam(value = "date", required = false) LocalDate date) {

        Page<Borrow> page = this.borrowService.findPage(idGame, idCustomer, date, dto);
        //llamar a otros microservicios
        List<GameDto> games = gameClient.findAll();
        List<CustomerDto> customers = customerClient.findAll();

        return new PageImpl<>(page.getContent().stream().map(borrow -> {
            BorrowDto borrowdto = new BorrowDto();

            borrowdto.setId(borrow.getId());
            borrowdto.setStartDate(borrow.getStartDate());
            borrowdto.setFinishDate(borrow.getFinishDate());
            borrowdto.setGame(games.stream().filter(game -> game.getId().equals(borrow.getIdGame())).findFirst().orElse(null));
            borrowdto.setCustomer(customers.stream().filter(customer -> customer.getId().equals(borrow.getIdCustomer())).findFirst().orElse(null));

            return borrowdto;

        }).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());

    }

    /**
     * Método para crear un  {@link Borrow}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save", description = "Method that saves a Borrow")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody BorrowDto dto) throws Exception {
        this.borrowService.save(id, dto);
    }

    /**
     * Método para eliminar un {@link Borrow}
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Author")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.borrowService.delete(id);

    }
}
