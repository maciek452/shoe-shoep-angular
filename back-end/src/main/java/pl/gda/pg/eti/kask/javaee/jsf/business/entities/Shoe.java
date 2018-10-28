package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shoe implements Serializable {

    private Integer id;

    private String model;

    private int size;

    private List<ShoesCollection> shoesCollections = new ArrayList<>();

    public void removeCollection(ShoesCollection shoesCollection){
        List<ShoesCollection> shoesCollectionList = new ArrayList<>();
        for (ShoesCollection shoesCollection1 : shoesCollections) {
            if(!shoesCollection1.equals(shoesCollection)){
                shoesCollectionList.add(shoesCollection1);
            }
        }
        this.shoesCollections = shoesCollectionList;
    }

}
