package ic.doc.catalogues;

import ic.doc.Book;
import java.util.List;

public interface Searchable {
  public List<Book> searchFor(String query);
}
