package jpabook.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public static BookForm createBookForm(Long id, String name, int price, int stockQuantity, String author, String isbn)
    {
        BookForm bookForm = new BookForm();
        bookForm.setId(id);
        bookForm.setName(name);
        bookForm.setAuthor(author);
        bookForm.setPrice(price);
        bookForm.setStockQuantity(stockQuantity);
        bookForm.setIsbn(isbn);

        return bookForm;
    }
}
