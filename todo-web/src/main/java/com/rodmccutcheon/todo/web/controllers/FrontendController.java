package com.rodmccutcheon.todo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Function;

@Controller
public class FrontendController {

    private static final String PATH_VARIABLE_FILENAME = "{filename:.+[.].+}";
    private static final String PATH_VARIABLE_DIRECTORY = "{directory:(?:(?!error)[^.])*}";

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The requested page was unavailable")
    private static class ResourceNotFoundException extends RuntimeException {}

    private static final String[] STATIC_RESOURCE_DIRECTORIES = new String[] {"/frontend", "/public" };
    public static final String FRONTEND_HOME_PATH = "/frontend/portal.html";

    @Autowired
    @Qualifier("frontendResourceResolver")
    private Function<String, Resource> resourceResolver;

//    @RequestMapping(value = {
//            "/" + PATH_VARIABLE_FILENAME,
//            "/" + PATH_VARIABLE_DIRECTORY + "/" + PATH_VARIABLE_FILENAME,
//            "/" + PATH_VARIABLE_DIRECTORY + "/**/" + PATH_VARIABLE_FILENAME
//    }, method = RequestMethod.GET)
//    public ResponseEntity<InputStreamResource> getFile(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        return responseFromResource(getResourceFromStaticDirectories(request.getRequestURI()), webRequest, response);
//    }
//
//    @RequestMapping(value = {
//            "/",
//            "/" + PATH_VARIABLE_DIRECTORY,
//            "/**/" + PATH_VARIABLE_DIRECTORY
//    }, method = RequestMethod.GET)
//    public ResponseEntity<InputStreamResource> getDirectory(WebRequest webRequest, HttpServletResponse response) throws IOException, ResourceNotFoundException {
//        return responseFromResource(getResourceAtPath(FRONTEND_HOME_PATH), webRequest, response);
//    }
//
//    private ResponseEntity<InputStreamResource> responseFromResource(Resource resource, WebRequest webRequest, HttpServletResponse response) throws IOException {
//        String etag = etagForResouce(resource);
//        return etagResponseProvider.responseForKey(webRequest, response, etag, () -> responseContainingResource(resource));
//    }
//
//    private String etagForResouce(Resource resource) throws IOException {
//        String key = resource.getFilename();
//        String etag = etags.get(key);
//        if (etag == null) {
//            etag = DigestUtils.md5DigestAsHex(resource.getInputStream());
//            etags.put(key, etag);
//        }
//        return etag;
//    }
//
//    private ResponseEntity<InputStreamResource> responseContainingResource(Resource resource) {
//        try {
//            return ResponseEntity.ok(new InputStreamResource(resource.getInputStream()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private Resource getResourceFromStaticDirectories(String requestUri) throws ResourceNotFoundException {
//        for (String directory : STATIC_RESOURCE_DIRECTORIES) {
//            Resource resource = resourceResolver.apply(directory + "/" + requestUri);
//
//            if (resource != null && resource.exists() && resource.isReadable()) {
//                return resource;
//            }
//        }
//
//        throw new ResourceNotFoundException();
//    }
//
//    private Resource getResourceAtPath(String path) throws ResourceNotFoundException {
//        Resource resource = resourceResolver.apply(path);
//
//        if (resource != null && resource.exists() && resource.isReadable()) {
//            return resource;
//        }
//
//        throw new ResourceNotFoundException();
//    }

}
