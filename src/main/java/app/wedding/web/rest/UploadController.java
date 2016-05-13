package app.wedding.web.rest;

import app.wedding.security.AuthoritiesConstants;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") final MultipartFile file, @RequestParam("uploader") String uploader)
        throws FileNotFoundException {
        ResponseEntity<?> responseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);
        LOG.info("Upload started... " + file.getOriginalFilename());

        final File curDir = new File(".");
        final String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

        BufferedOutputStream stream = null;

        if (!file.isEmpty()) {
            try {
                Date dt = new Date();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(dt);
                File path = new File(curDir.getCanonicalPath() + File.separator + "uploads" + File.separator + date + File.separator + uploader);
                LOG.info(path + File.separator + file.getOriginalFilename());

                if (!path.exists()) {
                    path.mkdirs();
                }

                stream = new BufferedOutputStream(
                    new FileOutputStream(path + File.separator + file.getOriginalFilename())
                );
                FileCopyUtils.copy(file.getInputStream(), stream);
                LOG.info("Upload finished");
            } catch (Exception e) {
                LOG.warn(e.getMessage());
            }
        }

        return responseEntity;
    }
}

