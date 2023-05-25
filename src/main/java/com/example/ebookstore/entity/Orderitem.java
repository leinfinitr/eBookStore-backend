package com.example.ebookstore.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Orderitem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "book_id")
    private int bookId;
    @Basic
    @Column(name = "book_num")
    private int bookNum;
    @Basic
    @Column(name = "pay")
    private BigDecimal pay;
    @Basic
    @Column(name = "order_time")
    private Timestamp orderTime;
    @Basic
    @Column(name = "order_status")
    private Object orderStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Object getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Object orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderitem orderitem = (Orderitem) o;

        if (id != orderitem.id) return false;
        if (orderId != orderitem.orderId) return false;
        if (bookId != orderitem.bookId) return false;
        if (bookNum != orderitem.bookNum) return false;
        if (!Objects.equals(pay, orderitem.pay)) return false;
        if (!Objects.equals(orderTime, orderitem.orderTime)) return false;
        return Objects.equals(orderStatus, orderitem.orderStatus);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + bookId;
        result = 31 * result + bookNum;
        result = 31 * result + (pay != null ? pay.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }
}
