package com.show.pojo;

import java.util.Objects;

public class Stock {
    private Integer id;
    private String username;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock order = (Stock) o;
        return Objects.equals(id, order.id) && Objects.equals(username, order.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    public Stock() {
    }

    public Stock(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
