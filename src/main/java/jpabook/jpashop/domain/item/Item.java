package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //== business logic ==//
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        checkEnoughQuantity(quantity);
        this.stockQuantity -= quantity;
    }

    private void checkEnoughQuantity(int quantity) throws NotEnoughStockException {
        if(this.stockQuantity - quantity < 0){
            throw new NotEnoughStockException("need more stock");
        }
    }

    public void changeItem(int price, int stockQuantity, String name){
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
        this.setName(name);
    }

}
