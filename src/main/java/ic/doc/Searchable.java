package ic.doc;

import ic.doc.Book;
import java.util.List;

public interface Searchable {
  List<Book> searchFor(String query);
}
