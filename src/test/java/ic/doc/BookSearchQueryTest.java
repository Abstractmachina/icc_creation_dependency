package ic.doc;

import static ic.doc.BookSearchQueryBuilder.query;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import ic.doc.catalogues.BritishLibraryCatalogue;
import java.util.List;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class BookSearchQueryTest {

  /*
  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    List<Book> books =
        query().withSurname("dickens").build().execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    List<Book> books =
        query().withFirstName("Jane").build().execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    List<Book> books =
        query().withTitle("Two Cities").build().execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books =
        query().beforePublicationYear(1700).build().execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books =
        query().afterPublicationYear(1950).build().execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books =
        query()
            .withSurname("dickens")
            .beforePublicationYear(1840)
            .build()
            .execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    List<Book> books =
        query()
            .withTitle("of")
            .afterPublicationYear(1800)
            .beforePublicationYear(2000)
            .build()
            .execute(BritishLibraryCatalogue.getInstance());
    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }


   */
  // keeping old tests above to pass coverage. not sure if there's another way
  // to pass coverage testing for BritishLibraryCatalogue
  // ============================================================================
  //                  MOCK OBJECT TESTING
  // ============================================================================

  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  private final Searchable catalogue = context.mock(Searchable.class);

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname2() {

    context.checking(
        new Expectations() {
          {
            exactly(1).of(catalogue).searchFor("LASTNAME='dickens' ");
          }
        });
    List<Book> books = query().withSurname("dickens").build().execute(catalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname2() {

    context.checking(
        new Expectations() {
          {
            exactly(1).of(catalogue).searchFor("FIRSTNAME='Jane' ");
          }
        });
    List<Book> books = query().withFirstName("Jane").build().execute(catalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle2() {

    context.checking(
        new Expectations() {
          {
            exactly(1).of(catalogue).searchFor("TITLECONTAINS(Two Cities) ");
          }
        });

    List<Book> books = query().withTitle("Two Cities").build().execute(catalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear2() {

    context.checking(
        new Expectations() {
          {
            exactly(1).of(catalogue).searchFor("PUBLISHEDBEFORE(1700) ");
          }
        });

    List<Book> books = query().beforePublicationYear(1700).build().execute(catalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear2() {

    context.checking(
        new Expectations() {
          {
            exactly(1).of(catalogue).searchFor("PUBLISHEDAFTER(1950) ");
          }
        });

    List<Book> books = query().afterPublicationYear(1950).build().execute(catalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters2() {

    context.checking(
        new Expectations() {
          {
            exactly(1).of(catalogue).searchFor("LASTNAME='dickens' PUBLISHEDBEFORE(1840) ");
          }
        });

    List<Book> books =
        query().withSurname("dickens").beforePublicationYear(1840).build().execute(catalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters2() {

    context.checking(
        new Expectations() {
          {
            exactly(1)
                .of(catalogue)
                .searchFor("TITLECONTAINS(of) PUBLISHEDAFTER(1800) PUBLISHEDBEFORE(2000) ");
          }
        });
    List<Book> books =
        query()
            .withTitle("of")
            .afterPublicationYear(1800)
            .beforePublicationYear(2000)
            .build()
            .execute(catalogue);
  }
}
