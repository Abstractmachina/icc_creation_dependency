package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import ic.doc.catalogues.Searchable;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;

import ic.doc.catalogues.BritishLibraryCatalogue;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;

import static ic.doc.BookSearchQueryBuilder.*;

public class BookSearchQueryTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  private final Searchable catalogue = context.mock(Searchable.class);

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    BookSearchQuery q = query().withSurname("dickens").build();
    List<Book> books = q.execute(BritishLibraryCatalogue.getInstance());
    //List<Book> books = query().withSurname("dickens").build(BritishLibraryCatalogue.getInstance());

//    context.checking(
//        new Expectations(){{
//          exactly(1).of(catalogue).searchFor(with(stringContaining("dickens")));
//        }}
//    );
//    List<Book> books = query().withSurname("dickens").build(catalogue);
    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {


    BookSearchQuery q = query().withFirstName("Jane").build();
    List<Book> books = q.execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    BookSearchQuery q = query().withTitle("Two Cities").build();
    List<Book> books = q.execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    BookSearchQuery q = query().beforePublicationYear(1700).build();
    List<Book> books = q.execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    BookSearchQuery q = query().afterPublicationYear(1950).build();
    List<Book> books = q.execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    BookSearchQuery q = query().withSurname("dickens").beforePublicationYear(1840).build();
    List<Book> books = q.execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    BookSearchQuery q = query().withTitle("of").afterPublicationYear(1800).beforePublicationYear(2000).build();
    List<Book> books = q.execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
