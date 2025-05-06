package com.ccsw.tutorial_borrow.borrow.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrow")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "game_id", nullable = false)
    private Long idGame;

    @Column(name = "customer_id", nullable = false)
    private Long idCustomer;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "finish_date", nullable = false)
    private LocalDate finishDate;

    /**
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return idGame
     */
    public Long getIdGame() {
        return this.idGame;
    }

    /**
     * @param idGame new value of {@link #getIdGame}.
     */
    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    /**
     *
     * @return idCustomer
     */
    public Long getIdCustomer() {
        return this.idCustomer;
    }

    /**
     * @param idCustomer new value of {@link #getIdCustomer}.
     */
    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    /**
     *
     * @return startDate
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * @param startDate new value of {@link #getStartDate}.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return finishDate
     */
    public LocalDate getFinishDate() {
        return this.finishDate;
    }

    /**
     * @param finishDate new value of {@link #getFinishDate}.
     */
    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
