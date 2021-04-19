package com.bank.reckoning.controller;

import com.bank.reckoning.dto.JournalViewDto;
import com.bank.reckoning.service.JournalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller for works with journals.
 */
@Controller
@RequestMapping("/journals")
@RequiredArgsConstructor
@CrossOrigin
@Api
public class JournalController {

    private final JournalService journalService;

    /**
     * Method for getting account journals.
     *
     * @param accountId for getting journal
     * @return list of journals dtos
     */
    @GetMapping("/{accountId}")
    @ApiOperation("Метод получения всех операций по счету.")
    public ResponseEntity<List<JournalViewDto>> getALlAccountsByUserId(@PathVariable Long accountId) {
        return ResponseEntity.ok(journalService.getAccountOperations(accountId));
    }
}
