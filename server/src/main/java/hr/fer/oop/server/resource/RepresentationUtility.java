package hr.fer.oop.server.resource;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;
import java.util.Set;

public class RepresentationUtility {
    public static <T> T throwExceptionIfNull(T obj) {
        if (obj != null)
            return obj;
        else
            throw new ResourceNotFoundException("Resource not found");
    }

    public static <T> Set<T> throwExceptionIfSetNull(Set<T> obj) {
        if (obj != null)
            return obj;
        else
            throw new ResourceNotFoundException("Resources not found");
    }

    public static <T> List<T> throwExceptionIfListNull(List<T> obj) {
        if (obj != null)
            return obj;
        else
            throw new ResourceNotFoundException("Resources not found");
    }
}
