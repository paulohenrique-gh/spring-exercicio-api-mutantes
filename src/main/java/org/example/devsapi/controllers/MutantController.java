package org.example.devsapi.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.devsapi.dtos.MutantDto;
import org.example.devsapi.models.Mutant;
import org.example.devsapi.services.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("mutants")
@RequiredArgsConstructor
public class MutantController {

    private final MutantService mutantService;

    @PostMapping
    public ResponseEntity<Object> addMutant(@RequestBody @Valid MutantDto mutantDto) {
        if (!mutantService.authenticate(mutantDto.password())) {
            return getUnauthorizedResponse();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(mutantService.addMutant(mutantDto));
    }

    @GetMapping
    public ResponseEntity<List<Mutant>> getMutants() {
        return ResponseEntity.status(HttpStatus.OK).body(mutantService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Mutant> getMutant(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(mutantService.findById(id));
    }

    @GetMapping("{id}/enemies-defeated-summary")
    public ResponseEntity<Map<String, Object>> getEnemiesDefeatedSummary(@PathVariable UUID id) {
        Mutant mutant = mutantService.findById(id);
        Map<String, Object> enemiesDefeatedSummary = new HashMap<>();
        enemiesDefeatedSummary.put("aliensDefeated", mutant.getAliensDefeated());
        enemiesDefeatedSummary.put("demonsDefeated", mutant.getDemonsDefeated());
        if (mutant.getIsEligibleForEspada()) {
            enemiesDefeatedSummary.put("specialMessage", "You have been summoned to join E.S.P.A.D.A");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enemiesDefeatedSummary);
    }

    @GetMapping("{id}/espada-eligibility")
    public ResponseEntity<Object> checkEspadaEligibility(@PathVariable UUID id) {
        Mutant mutant = mutantService.findById(id);
        Map<String, Boolean> responseMsg = new HashMap<>();
        responseMsg.put("eligibleForEspada", mutant.getIsEligibleForEspada());
        return ResponseEntity.status(HttpStatus.OK).body(responseMsg);
    }

    @PatchMapping("{id}/check-in")
    public ResponseEntity<Mutant> checkIn(@PathVariable UUID id) {
        Mutant mutant = mutantService.checkInById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mutant);
    }

    @GetMapping("/checked-in")
    public ResponseEntity<List<Mutant>> getCheckedInMutants() {
        return ResponseEntity.status(HttpStatus.OK).body(mutantService.findCheckedInMutants());
    }

    private ResponseEntity<Object> getUnauthorizedResponse() {
        Map<String, String> responseMsg = new HashMap<>();
        responseMsg.put("error", "Invalid password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMsg);
    }
}
