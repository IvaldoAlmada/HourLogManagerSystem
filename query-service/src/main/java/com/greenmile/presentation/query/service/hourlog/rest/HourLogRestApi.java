package com.greenmile.presentation.query.service.hourlog.rest;

import com.greenmile.presentation.query.service.hourlog.entity.HourLogEntity;
import com.greenmile.presentation.query.service.hourlog.entity.WorkedTimeEntity;
import com.greenmile.presentation.query.service.hourlog.repository.HourLogReadOnlyRepository;
import com.greenmile.presentation.query.service.hourlog.service.HourLogReadOnlyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/hour-logs")
public class HourLogRestApi {

    private final HourLogReadOnlyService service;

    public HourLogRestApi(HourLogReadOnlyService service) {
        this.service = service;
    }

    @GetMapping
    public CompletableFuture<Page<HourLogEntity>> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping(value = "worked-time")
    public CompletableFuture<WorkedTimeEntity> findByUserAndDateRange(@RequestParam(value="user-id", required = false) String userId,
                                                             @RequestParam(value="start-date", required = false) String startDate,
                                                             @RequestParam(value="end-date", required = false) String endDate) {
        try {
            return service.findByUserAndDateRange(userId, startDate, endDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @ExceptionHandler({
            ParseException.class,
            AssertionError.class,
            IllegalArgumentException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handle(HttpServletRequest request, Throwable e) {
        String badRequestContent = String.format("Request api call {} is bad request", request.getRequestURL());
        return ResponseEntity.badRequest().body(badRequestContent);
    }

    @ExceptionHandler(HourLogEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound(){}

    private class HourLogEntityNotFoundException extends RuntimeException {
        public HourLogEntityNotFoundException(String id) {
            super(String.format("Hour log %s not found", id));
        }
    }
}
