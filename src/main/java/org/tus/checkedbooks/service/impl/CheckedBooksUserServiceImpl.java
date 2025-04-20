package org.tus.checkedbooks.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tus.checkedbooks.dto.CheckedBooksDto;
import org.tus.checkedbooks.dto.LibraryUserDto;
import org.tus.checkedbooks.entity.CheckedBooks;
import org.tus.checkedbooks.entity.LibraryUser;
import org.tus.checkedbooks.exception.ResourceNotFoundException;
import org.tus.checkedbooks.feign.BookInterface;
import org.tus.checkedbooks.feign.LibraryUserInterface;
import org.tus.checkedbooks.mapper.CheckedBooksMapper;
import org.tus.checkedbooks.repository.CheckedBooksRepository;
import org.tus.checkedbooks.service.ICheckedBooksUserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.tus.checkedbooks.mapper.CheckedBooksMapper.mapToCheckedBooksDto;


@Service
@AllArgsConstructor
public class CheckedBooksUserServiceImpl implements ICheckedBooksUserService {


    private CheckedBooksRepository checkedBooksRepository;

    @Autowired
    BookInterface bookInterface;

    @Autowired
    LibraryUserInterface libraryUserInterface;


    @Override
    public CheckedBooksDto checkoutLibraryBook(int libraryUserId, String bookName, LibraryUserDto libraryUserDto) {


        CheckedBooks checkedBook = new CheckedBooks();
        checkedBook.setBook_name(bookName);
        checkedBook.setLibraryUserId(libraryUserId);
        checkedBook.setCheckedStatus("ON_TIME");
        checkedBooksRepository.save(checkedBook);
        return mapToCheckedBooksDto(checkedBook, new CheckedBooksDto());
    }

    @Override
    public List<CheckedBooksDto> fetchUsersCheckedOutBooks(int id) {

//        LibraryUser libraryUser = libraryUserRepository.findByLibraryUserId(id).orElseThrow(
//                () -> new ResourceNotFoundException("LibraryUser","libraryUserId",String.valueOf(id))
//        );

        List<CheckedBooks> usersCheckBooks = checkedBooksRepository.findByLibraryUserId(id);

        if(usersCheckBooks.isEmpty()) {
            throw new ResourceNotFoundException("LibraryUser","libraryUserId",String.valueOf(id));
        }
        else {
            return usersCheckBooks.stream()
                    .map(checkedBookRecord -> mapToCheckedBooksDto(checkedBookRecord, new CheckedBooksDto()))
                    .toList();
        }
    }

    @Override
    public CheckedBooksDto fetchCheckedOutBook(int userid, int checkedBookid) {
//        LibraryUser libraryUser = libraryUserRepository.findByLibraryUserId(userid).orElseThrow(
//                () -> new ResourceNotFoundException("LibraryUser","libraryUserId",String.valueOf(userid))
//        );

        CheckedBooks userCheckedBook = checkedBooksRepository.findByCheckedBookId(checkedBookid);

        if(userCheckedBook == null) {
            throw new ResourceNotFoundException("CheckedBook","checkedBookId",String.valueOf(checkedBookid));
        }
        else {
            return mapToCheckedBooksDto(userCheckedBook, new CheckedBooksDto());
        }
    }

    @Override
    public boolean updateCheckedOutBook(int userid, int checkedBookId, CheckedBooksDto checkedBooksDto) {
        boolean isUpdated = false;
        CheckedBooks userCheckedBook = checkedBooksRepository.findByCheckedBookId(checkedBookId);

        if(userCheckedBook == null) {
            throw new ResourceNotFoundException("CheckedBook", "checkedBookId", String.valueOf(checkedBookId));
        }
        else{
            CheckedBooksMapper.mapToCheckedBooks(checkedBooksDto, userCheckedBook);
            checkedBooksRepository.save(userCheckedBook);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCheckedOutBook(int userid, int checkedBookId) {
//        LibraryUser libraryUser = libraryUserRepository.findByLibraryUserId(userid).orElseThrow(
//                () -> new ResourceNotFoundException("LibraryUser","libraryUserId",String.valueOf(userid))
//        );

        CheckedBooks usersCheckBooks = checkedBooksRepository.findByCheckedBookId(checkedBookId);

        if(usersCheckBooks == null) {
            throw new ResourceNotFoundException("CheckedBook", "checkedBookId", String.valueOf(checkedBookId));
        }

        checkedBooksRepository.delete(usersCheckBooks);
        return true;
    }
}
