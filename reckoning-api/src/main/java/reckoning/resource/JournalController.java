package reckoning.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reckoning.dto.JournalViewDto;

import java.util.List;

@Api
@RequestMapping("/journals")
public interface JournalController {

    /**
     * Method for getting account journals.
     *
     * @param accountId for getting journal
     * @return list of journals dtos
     */
    @GetMapping("/{accountId}")
    @ApiOperation("Метод получения всех операций по счету.")
    ResponseEntity<List<JournalViewDto>> getALlAccountsByUserId(Long accountId);
}
