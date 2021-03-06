package ic.doc;

public class BookSearchQueryBuilder {

  private String name;
  private String surname;
  private String title;
  private Integer afterYear;
  private Integer beforeYear;

  private BookSearchQueryBuilder() {}

  public static BookSearchQueryBuilder query() {
    return new BookSearchQueryBuilder();
  }

  public BookSearchQuery build() {
    return new BookSearchQuery(name, surname, title, afterYear, beforeYear);
  }

  public BookSearchQueryBuilder withFirstName(String firstName) {
    this.name = firstName;
    return this;
  }

  public BookSearchQueryBuilder withSurname(String surname) {
    this.surname = surname;
    return this;
  }

  public BookSearchQueryBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public BookSearchQueryBuilder beforePublicationYear(Integer year) {
    this.beforeYear = year;
    return this;
  }

  public BookSearchQueryBuilder afterPublicationYear(Integer year) {
    this.afterYear = year;
    return this;
  }
}
