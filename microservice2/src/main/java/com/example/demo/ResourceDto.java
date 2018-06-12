package com.example.demo;

import java.util.Objects;

public class ResourceDto {

    private long id;
    private String data;

    public ResourceDto() {
    }

    public ResourceDto(long id, String data) {
        this.id = id;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceDto that = (ResourceDto) o;
        return id == that.id &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, data);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResourceDto{");
        sb.append("id=").append(id);
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
