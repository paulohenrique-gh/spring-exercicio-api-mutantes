package org.example.devsapi.services;

import lombok.RequiredArgsConstructor;
import org.example.devsapi.data.MutantConstants;
import org.example.devsapi.dtos.MutantDto;
import org.example.devsapi.exceptions.InvalidPasswordException;
import org.example.devsapi.exceptions.MutantNotFoundException;
import org.example.devsapi.models.Mutant;
import org.example.devsapi.repositories.MutantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MutantService {

    private final MutantRepository mutantRepository;
    private final MutantConstants mutantConstants;

    public void authenticate(String password) {
        if (!password.equals(mutantConstants.getPassword())) {
            throw new InvalidPasswordException();
        }
    }

    public Mutant addMutant(MutantDto mutantDto) {
        Mutant mutant = new Mutant() ;
        mutant.setAliensDefeated(getTotalAliensFromTotalEnemies(mutantDto.enemiesDefeated()));
        mutant.setDemonsDefeated(getTotalDemonsFromTotalEnemies(mutantDto.enemiesDefeated()));
        mutant.setIsEligibleForEspada(mutant.getAliensDefeated() > mutantConstants.getMinAliensDefeatedEligibility());
        BeanUtils.copyProperties(mutantDto, mutant, "password", "enemiesDefeated");
        return mutantRepository.save(mutant);
    }

    public List<Mutant> findAll() {
        return mutantRepository.findAll();
    }

    public Mutant findById(UUID id) {
        return mutantRepository.findById(id).orElseThrow(() -> new MutantNotFoundException(id));
    }

    public Mutant checkInById(UUID id) {
        Mutant mutant = mutantRepository.findById(id).orElseThrow(() -> new MutantNotFoundException(id));
        if (!mutant.getIsCheckedIn()) {
            mutant.setIsCheckedIn(true);
            return mutantRepository.save(mutant);
        }
        return mutant;
    }

    public Mutant checkOutById(UUID id) {
        Mutant mutant = mutantRepository.findById(id).orElseThrow(() -> new MutantNotFoundException(id));
        if (mutant.getIsCheckedIn()) {
            mutant.setIsCheckedIn(false);
            return mutantRepository.save(mutant);
        }
        return mutant;
    }

    public List<Mutant> findCheckedInMutants() {
        return mutantRepository.findMutantByIsCheckedInIsTrue();
    }

    public Integer getCheckedInCount() {
        return mutantRepository.countMutantsByIsCheckedInIsTrue();
    }

    private Integer getTotalAliensFromTotalEnemies(Integer totalEnemiesDefeated) {
        return Math.toIntExact(Math.round(totalEnemiesDefeated * mutantConstants.getAverageAliensDefeatedPercentage()));
    }

    private Integer getTotalDemonsFromTotalEnemies(Integer totalEnemiesDefeated) {
        return Math.toIntExact(Math.round(totalEnemiesDefeated * mutantConstants.getAverageDemonsDefeatedPercentage()));
    }
}
