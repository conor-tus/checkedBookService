package org.tus.checkedbooks.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.tus.checkedbooks.dto.LibraryUserDto;

@FeignClient("LIBRARY-USER-SERVICE")
public interface LibraryUserInterface {

    @GetMapping("api/user/{id}")
    public ResponseEntity<LibraryUserDto> getLibraryUserDetails(@PathVariable int id, @RequestParam(required = false) boolean isReversed);
}
