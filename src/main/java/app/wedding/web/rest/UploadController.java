package app.wedding.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.FileNotFoundException;

/**
 * Created by ezolcho on 5/6/2016.
 */
@RestController
@RequestMapping("/api")
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    /**
     * File upload handler method for file containing TTCN type schema.
     *
     * @param file uploaded file
     * @return response
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") final MultipartFile file, @RequestParam final String filetype)
        throws FileNotFoundException {
        ResponseEntity<?> responseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);
        LOG.info("[PHOENIX] Schema file upload started...");

        return responseEntity;
    }
}

