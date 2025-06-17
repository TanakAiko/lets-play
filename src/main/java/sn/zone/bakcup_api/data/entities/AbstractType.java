package sn.zone.bakcup_api.data.entities;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public abstract class AbstractType {
    @Id
    private String id;
    private String name;

    public AbstractType(String name) {
        this.name = name;
    }
}
