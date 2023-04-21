package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author nemo = new Author();
        nemo.setFirstName("Nemo");
        nemo.setLastName("Bruke");

        Book javabook = new Book();
        javabook.setTitle("How to program");
        javabook.setIsbn("123456");

        Author nemoSaved = authorRepository.save(nemo);
        Book javabookSaved = bookRepository.save(javabook);

        Author tungpt = new Author();
        tungpt.setFirstName("Thanh Tung");
        tungpt.setLastName("Pham");

        Book cSharp = new Book();
        cSharp.setTitle("How to program");
        cSharp.setIsbn("123456");

        Author tungptSaved = authorRepository.save(tungpt);
        Book cSharpSaved = bookRepository.save(cSharp);

        nemoSaved.getBooks().add(javabookSaved);
        tungptSaved.getBooks().add(cSharpSaved);
        javabookSaved.getAuthors().add(nemoSaved);
        cSharpSaved.getAuthors().add(tungptSaved);

        Publisher kimdong = new Publisher();
        kimdong.setPublisherName("Kim Dong");
        kimdong.setAddress("Thu Thiem");
        kimdong.setCity("Ho Chi Minh");
        kimdong.setState("South");
        kimdong.setZip("8000");
        Publisher kimDongSaved = publisherRepository.save(kimdong);

        Publisher ldxh = new Publisher();
        ldxh.setPublisherName("Lao Dong Xa Hoi");
        ldxh.setAddress("Hoan Kiem");
        ldxh.setCity("Ha Noi");
        ldxh.setState("North");
        ldxh.setZip("4000");
        Publisher ldxhSaved = publisherRepository.save(ldxh);

        javabookSaved.setPublisher(kimDongSaved);
        cSharpSaved.setPublisher(ldxhSaved);

        authorRepository.save(nemoSaved);
        authorRepository.save(tungptSaved);
        bookRepository.save(javabookSaved);
        bookRepository.save(cSharpSaved);

        System.out.println("Bootstrap");
        System.out.println("Author count " + authorRepository.count());
        System.out.println("Book count " + bookRepository.count());





        System.out.println("Publisher count " + publisherRepository.count());
    }
}
