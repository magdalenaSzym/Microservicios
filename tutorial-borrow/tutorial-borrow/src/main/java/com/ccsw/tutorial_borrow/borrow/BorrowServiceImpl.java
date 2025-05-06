package com.ccsw.tutorial_borrow.borrow;

import com.ccsw.tutorial_borrow.borrow.model.Borrow;
import com.ccsw.tutorial_borrow.borrow.model.BorrowDto;
import com.ccsw.tutorial_borrow.borrow.model.BorrowSearchDto;
import com.ccsw.tutorial_borrow.common.criteria.SearchCriteria;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    @Override
    public List<Borrow> findAll() {

        return (List<Borrow>) this.borrowRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, BorrowDto dto) throws Exception {

        isBorrowPeriodValid(dto.getStartDate(), dto.getFinishDate());
        hasCustomerReachedBorrowLimit(dto.getCustomer().getId(), dto.getStartDate(), dto.getFinishDate());
        hasGameAlreadyBorrowedInPeriod(dto.getGame().getId(), dto.getStartDate(), dto.getFinishDate());

        Borrow borrow;
        if (id == null) {
            borrow = new Borrow();

        } else {
            borrow = this.borrowRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, borrow, "id", "game", "customer");
        borrow.setIdGame(dto.getGame().getId());
        borrow.setIdCustomer(dto.getCustomer().getId());

        this.borrowRepository.save(borrow);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.borrowRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.borrowRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Borrow> findPage(Long gameId, Long customerId, LocalDate date, BorrowSearchDto dto) {
        // System.out.println("Filtros recibidos " + gameId + customerId + date);

        BorrowSpecification idGameSpec = new BorrowSpecification(new SearchCriteria("game.id", ":", gameId));
        BorrowSpecification idCustomerSpec = new BorrowSpecification(new SearchCriteria("customer.id", ":", customerId));
        BorrowSpecification dateStartSpec = new BorrowSpecification(new SearchCriteria("startDate", "lessThanOrEqualsTo", date));
        BorrowSpecification dateFinishSpec = new BorrowSpecification(new SearchCriteria("finishDate", "greaterThanOrEqualTo", date));

        Specification<Borrow> spec = Specification.where(idGameSpec).and(idCustomerSpec).and(dateStartSpec).and(dateFinishSpec);

        return this.borrowRepository.findAll(spec, dto.getPageable().getPageable());
    }

    public void hasCustomerReachedBorrowLimit(Long customerId, LocalDate startDate, LocalDate finishDate) throws Exception {
        long MAX_GAME_BORROW = 2;
        Specification<Borrow> customerBorrowOnDate = new BorrowSpecification(new SearchCriteria("customer.id", ":", customerId)).and(new BorrowSpecification(new SearchCriteria("dateStart", "lessThanOrEqualsTo", finishDate)))
                .and(new BorrowSpecification(new SearchCriteria("dateFinish", "greaterThanOrEqualTo", startDate)));

        long countGame = this.borrowRepository.count(customerBorrowOnDate);
        if (countGame >= MAX_GAME_BORROW) {
            throw new Exception("The customer exceeds the allowed number of games.");
           
        }
    }

    public void hasGameAlreadyBorrowedInPeriod(Long gameId, LocalDate startDate, LocalDate finishDate) throws Exception {
        Specification<Borrow> customerBorrowOnDate = new BorrowSpecification(new SearchCriteria("game.id", ":", gameId)).and(new BorrowSpecification(new SearchCriteria("startDate", "lessThanOrEqualsTo", finishDate)))
                .and(new BorrowSpecification(new SearchCriteria("finishDate", "greaterThanOrEqualTo", startDate)));

        long isBorrowed = this.borrowRepository.count(customerBorrowOnDate);

        if (isBorrowed >= 1) {
            throw new Exception("This game is already borrowed.");

        }
    }

    private void isBorrowPeriodValid(LocalDate startDate, LocalDate finishDate) throws Exception {
        long maxDays = 14;

        if (startDate.isAfter(finishDate)) {
            throw new Exception("Invalid date: The end date cannot be earlier than the start date");
        }
        long daysBetween = startDate.until(finishDate, ChronoUnit.DAYS);
        if (daysBetween > maxDays) {
            throw new Exception("The maximum borrowing period is 14 days");
        }
    }
}
