package com.springer.cards.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Admin");
    }

}
