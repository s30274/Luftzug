package pl.edu.pjatk.luftzug.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Country implements IHaveDictionaryName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String name;
    @OneToMany
    private List<Airport> airports = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchedules(List<Airport> schedules){
        this.airports = schedules;
    }

    public List<Airport> getSchedules(){
        return this.airports;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && Objects.equals(code, country.code) && Objects.equals(name, country.name) && Objects.equals(airports, country.airports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, airports);
    }
}
