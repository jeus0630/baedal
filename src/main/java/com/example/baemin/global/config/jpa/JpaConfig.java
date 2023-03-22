package com.example.baemin.global.config.jpa;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaConfig {

    private final HttpServletRequest httpServletRequest;

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                String modifiedBy = httpServletRequest.getRequestURI();
                if (!StringUtils.hasText(modifiedBy)) {
                    modifiedBy = "unknown";
                }

                return Optional.of(modifiedBy);
            }
        };
    }
}
