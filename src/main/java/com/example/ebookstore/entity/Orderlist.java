package com.example.ebookstore.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Orderlist {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "nation")
    private String nation;
    @Basic
    @Column(name = "province")
    private String province;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderlist orderlist = (Orderlist) o;

        if (orderId != orderlist.orderId) return false;
        if (!Objects.equals(userName, orderlist.userName)) return false;
        if (!Objects.equals(phone, orderlist.phone)) return false;
        if (!Objects.equals(nation, orderlist.nation)) return false;
        if (!Objects.equals(province, orderlist.province)) return false;
        if (!Objects.equals(address, orderlist.address)) return false;
        return Objects.equals(totalPrice, orderlist.totalPrice);
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (nation != null ? nation.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        return result;
    }
}
