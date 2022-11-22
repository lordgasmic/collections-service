package com.lordgasmic.collections.floss.contoller;

import com.lordgasmic.collections.floss.models.PatternResponse;
import com.lordgasmic.collections.floss.service.PatternService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
public class PatternController {

    private final PatternService patternService;

    public PatternController(final PatternService patternService) {
        this.patternService = patternService;
    }

    @GetMapping("/api/v1/patterns")
    public List<PatternResponse> getAllPatterns() throws SQLException {
        return patternService.getAllPatterns();
    }

}
