package org.tus.checkedbooks.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tus.checkedbooks.dto.BookDto;

import java.util.List;

@FeignClient("BOOK-SERVICE")
public interface BookInterface {
    @GetMapping("api/book")
    public ResponseEntity<List<BookDto>> getAllBooks();

    @GetMapping("api/book/{bookName}")
    public ResponseEntity<BookDto> getBook(@PathVariable String bookName);

}
