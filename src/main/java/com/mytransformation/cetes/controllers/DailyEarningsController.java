package com.mytransformation.cetes.controllers;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.mytransformation.cetes.config.App;
import com.mytransformation.cetes.models.DailyEarnings;
import com.mytransformation.cetes.repositories.DailyEarningsRepository;

@RestController
@RequestMapping(App.API_PATH + "/v1/earnings")
public class DailyEarningsController {

    @Autowired
    private DailyEarningsRepository dailyEarningsRepository;

    @GetMapping
    public ResponseEntity<List<DailyEarnings>> getAll(
        @RequestHeader("user-id") String userId,
        @RequestParam(value = "from", required = true) LocalDateTime from,
        @RequestParam(value = "to", required = true) LocalDateTime to
    ) {
        List<DailyEarnings> earnings = dailyEarningsRepository.findMeByInterval(userId, from, to);
        return new ResponseEntity<List<DailyEarnings>>(earnings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyEarnings> getOne(@RequestHeader("user-id") String userId, @PathVariable("id") String id) {
        Optional<DailyEarnings> optional = dailyEarningsRepository.findMe(userId, id);

        if (!optional.isPresent()) {
            return new ResponseEntity<DailyEarnings>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<DailyEarnings>(optional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DailyEarnings> create(@RequestBody DailyEarnings dailyEarnings) {
        DailyEarnings newDailyEarnings = dailyEarningsRepository.insert(dailyEarnings);
        return new ResponseEntity<DailyEarnings>(newDailyEarnings, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOne(@RequestHeader("user-id") String userId, @PathVariable("id") String id) {
        Optional<DailyEarnings> optional = dailyEarningsRepository.findMe(userId, id);

        if (!optional.isPresent()) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }

        dailyEarningsRepository.delete(optional.get());
        
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
    
}
