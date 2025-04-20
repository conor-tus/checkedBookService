package org.tus.checkedbooks.mapper;

import lombok.AllArgsConstructor;
import org.tus.checkedbooks.dto.CheckedBooksDto;
import org.tus.checkedbooks.entity.CheckedBooks;


@AllArgsConstructor
public class CheckedBooksMapper {


    public static CheckedBooksDto mapToCheckedBooksDto(CheckedBooks checkedBooks, CheckedBooksDto checkedBooksDto) {
        checkedBooksDto.setCheckedBookId(checkedBooks.getCheckedBookId());
        checkedBooksDto.setBookName(checkedBooks.getBook_name());
        checkedBooksDto.setLibraryUserId(checkedBooks.getLibraryUserId());
        checkedBooksDto.setCheckedStatus(checkedBooks.getCheckedStatus());
        checkedBooksDto.setCheckoutDate(checkedBooks.getCreatedAt());
        return checkedBooksDto;
    }

    public static CheckedBooks mapToCheckedBooks(CheckedBooksDto checkedBooksDto, CheckedBooks checkedBooks) {
        checkedBooks.setCheckedBookId(checkedBooksDto.getCheckedBookId());
        checkedBooks.setBook_name(checkedBooksDto.getBookName());
        checkedBooks.setLibraryUserId(checkedBooksDto.getLibraryUserId());
        checkedBooks.setCheckedStatus(checkedBooksDto.getCheckedStatus());
        return checkedBooks;
    }


}
