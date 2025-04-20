package org.tus.checkedbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tus.checkedbooks.constants.BooksConstants;
import org.tus.checkedbooks.dto.CheckedBooksDto;
import org.tus.checkedbooks.dto.LibraryUserDto;
import org.tus.checkedbooks.dto.ResponseDto;
import org.tus.checkedbooks.service.ICheckedBooksUserService;

import java.util.List;

@RestController
@RequestMapping(path="/api", produces= MediaType.APPLICATION_JSON_VALUE)
public class checkedBookController {

    private final ICheckedBooksUserService iCheckedBooksUserService;

    @Autowired
    public checkedBookController(ICheckedBooksUserService iCheckedBooksUserService) {
        this.iCheckedBooksUserService = iCheckedBooksUserService;
    }

    @PostMapping("/user/{userId}/checkout/{bookName}")
    public ResponseEntity<CheckedBooksDto> checkoutBook(@PathVariable int userId, @PathVariable String bookName, @RequestBody LibraryUserDto libraryUserDto) {
        CheckedBooksDto checkedBooksDto = iCheckedBooksUserService.checkoutLibraryBook(userId, bookName, libraryUserDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(checkedBooksDto);
    }

    @GetMapping("/user/{userid}/checkout/")
    public ResponseEntity<List<CheckedBooksDto>> getCheckedBooks(@PathVariable int userid) {
        List<CheckedBooksDto> usersBooks = iCheckedBooksUserService.fetchUsersCheckedOutBooks(userid);
        if (usersBooks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usersBooks);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(usersBooks);
        }
    }

    @GetMapping("/user/{userid}/checkout/{checkedBookid}")
    public ResponseEntity<CheckedBooksDto> getCheckedBook(@PathVariable int userid, @PathVariable int checkedBookid) {
        CheckedBooksDto checkedBook = iCheckedBooksUserService.fetchCheckedOutBook(userid,checkedBookid);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(checkedBook);
    }

    @PutMapping("/user/{userid}/checkout/{checkedBookid}")
    public ResponseEntity<ResponseDto> updateCheckoutBook(@PathVariable int userid,@PathVariable int checkedBookid,@RequestBody CheckedBooksDto checkedBooksDto) {
        boolean isUpdated = iCheckedBooksUserService.updateCheckedOutBook(userid,checkedBookid, checkedBooksDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(BooksConstants.STATUS_200, BooksConstants.CHECKED_BOOK_UPDATED_MESSAGE));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(BooksConstants.STATUS_500, BooksConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/user/{userid}/checkout/{checkedBookid}")
    public ResponseEntity<ResponseDto> deleteCheckoutBook(@PathVariable int userid, @PathVariable int checkedBookid) {
        boolean isUpdated = iCheckedBooksUserService.deleteCheckedOutBook(userid,checkedBookid);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(BooksConstants.STATUS_200, BooksConstants.CHECKED_BOOK_DELETED_MESSAGE));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(BooksConstants.STATUS_500, BooksConstants.MESSAGE_500));
        }
    }

}
