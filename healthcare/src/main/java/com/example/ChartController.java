package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/charts")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @Autowired
    private UserRepository userRepo;

    // ✅ Pie chart for logged-in user
    @GetMapping("/pie")
    public ResponseEntity<byte[]> getCategoryPieChart(Principal principal) {
        try {
            Optional<User> optionalUser = userRepo.findByUsername(principal.getName());
            if (optionalUser.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            User user = optionalUser.get();
            byte[] chart = chartService.generateCategoryPieChart(user.getId());

            if (chart == null || chart.length == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(chart, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace(); // Log actual error to console
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Bar chart for logged-in user
    @GetMapping("/bar")
    public ResponseEntity<byte[]> getMonthlyBarChart(Principal principal) {
        try {
            Optional<User> optionalUser = userRepo.findByUsername(principal.getName());
            if (optionalUser.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            User user = optionalUser.get();
            byte[] chart = chartService.generateMonthlyBarChart(user.getId());

            if (chart == null || chart.length == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(chart, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace(); // Log actual error to console
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
