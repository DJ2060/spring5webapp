package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository pubRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository pubRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.pubRepository = pubRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher pub = new Publisher("Books Are Us");
        pub.setAddressLine1("2/61 Bay Rd");
        pub.setPostcode("2060");
        pub.setState("NSW");
        pub.setSuburb("Waverton");
        pubRepository.save(pub);

        Author eric = new Author("Evans","Eric");
        Book ddd = new Book ("Domain Driven Design", "123123" );
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(pub);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Johnson", "Rod");
        Book noEJB = new Book("J2EE Development without EJB","321321");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(pub);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        pub.getBooks().add(ddd);
        pub.getBooks().add(noEJB);

        pubRepository.save(pub);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + pubRepository.count());
        System.out.println("Number of Books assigned to publisher: " + pub.getBooks().size());
    }
}
