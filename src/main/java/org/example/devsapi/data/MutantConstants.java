package org.example.devsapi.data;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MutantConstants {
    private final String password = "apocalipse";
    private final double averageAliensDefeatedPercentage = .268;
    private final double averageDemonsDefeatedPercentage = .432;
    private final int minAliensDefeatedEligibility = 20;
}
