package sn.zone.bakcup_api.configs;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import jakarta.annotation.PostConstruct;

@Component
public class RouteChecker {
    private final RequestMappingHandlerMapping handlerMapping;
    private Set<Pattern> validPathPatterns;

    @Autowired
    public RouteChecker(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @PostConstruct
    public void init() {
        validPathPatterns = handlerMapping.getHandlerMethods().keySet().stream()
                .flatMap(info -> info.getPatternValues().stream())
                .map(this::convertToRegex)
                .distinct()
                .map(Pattern::compile)
                .collect(Collectors.toSet());

        System.out.println("---------> Compiled path regex patterns:");
        validPathPatterns.forEach(p -> System.out.println("  " + p.pattern()));
    }

    private String convertToRegex(String path) {
        // Convert /api/users/{id} => /api/users/[^/]+
        return path.replaceAll("\\{[^/]+}", "[^/]+");
    }

    public boolean isValidPath(String path) {
        return validPathPatterns.stream().anyMatch(p -> p.matcher(path).matches());
    }
}
