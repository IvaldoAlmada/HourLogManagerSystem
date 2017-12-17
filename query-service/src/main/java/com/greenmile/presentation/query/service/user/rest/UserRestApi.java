package com.greenmile.presentation.query.service.user.rest;

import com.greenmile.presentation.query.service.user.entity.UserEntity;
import com.greenmile.presentation.query.service.user.repository.UserReadOnlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
    @RequestMapping(value = "/users")
public class UserRestApi {

    @Autowired
    private UserReadOnlyRepository repository;


    public UserRestApi(UserReadOnlyRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public CompletableFuture<Page<UserEntity>> findAll(Pageable pageable) {
        try {
            return CompletableFuture.supplyAsync(() -> repository.findAll(pageable));
        } catch (AssertionError | IllegalArgumentException e) {
            throw e;
        }
    }

    @GetMapping(path = "/id")
    public CompletableFuture<UserEntity> findById(@RequestParam(value="id") String id) {
        try {
            Assert.hasLength(id, "Missing user id");
            return CompletableFuture.supplyAsync(() -> {
                UserEntity entity = repository.findOne(id);
                if (Optional.ofNullable(entity).isPresent()) {
                    return entity;
                } else {
                    throw new UserEntityNotFoundException(id);
                }
            });
        } catch (AssertionError | IllegalArgumentException e) {
            throw e;
        }
    }

    @ExceptionHandler({
            AssertionError.class,
            IllegalArgumentException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handle(HttpServletRequest request, Throwable e) {
        String badRequestContent = String.format("Request api call {} is bad request", request.getRequestURL());
        return ResponseEntity.badRequest().body(badRequestContent);
    }

    @ExceptionHandler(UserEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound(){}

    private class UserEntityNotFoundException extends RuntimeException {
        public UserEntityNotFoundException(String id) {
            super(String.format("User %s not found", id));
        }
    }
}
