package org.tus.checkedbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tus.checkedbooks.entity.CheckedBooks;

import java.util.List;

@Repository
public interface CheckedBooksRepository extends JpaRepository<CheckedBooks, Long> {

    List<CheckedBooks> findByLibraryUserId(int libraryUserId);

    CheckedBooks findByCheckedBookId(int checkedBookId);
}
