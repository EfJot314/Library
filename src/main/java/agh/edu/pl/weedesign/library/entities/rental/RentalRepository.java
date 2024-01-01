package agh.edu.pl.weedesign.library.entities.rental;

import agh.edu.pl.weedesign.library.entities.reader.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> findRentalsByReader(Reader reader);

}
