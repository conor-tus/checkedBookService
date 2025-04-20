package org.tus.checkedbooks.service;



import org.tus.checkedbooks.dto.CheckedBooksDto;
import org.tus.checkedbooks.dto.LibraryUserDto;

import java.util.List;

public interface ICheckedBooksUserService {

    CheckedBooksDto checkoutLibraryBook(int libraryUserId, String bookName, LibraryUserDto libraryUserDto);

    List<CheckedBooksDto> fetchUsersCheckedOutBooks(int id);

    CheckedBooksDto fetchCheckedOutBook(int userid, int checkedBookId);

    boolean updateCheckedOutBook(int userid, int checkedBookId, CheckedBooksDto checkedBooksDto);

    boolean deleteCheckedOutBook(int userid, int checkedBookId);
}
