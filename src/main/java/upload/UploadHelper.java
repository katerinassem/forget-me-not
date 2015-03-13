package upload;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.File;
import java.util.*;

/**
 * Created by Катерина on 09.03.2015.
 */
public class UploadHelper {

    private static Logger logger = Logger.getLogger(UploadHelper.class);

    private static UploadHelper instance = new UploadHelper();

    private static final long serialVersionUID = 1L;

    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "upload";

    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB


    private UploadHelper(){

        logger.info(" - [ ENTERING UploadHelper CONSTRUCTOR, NO PARAMETERS ]");
    }

    public static UploadHelper getInstance(){
        logger.info(" - [ ENTERING getInstance(), NO PARAMETERS ]");
        return instance;
    }

    public void prepareRequest(HttpServletRequest req) throws UploadException{

        logger.info(" - [ ENTERING METHOD analyzeRequest(HttpServletRequest req) ]");

        if(ServletFileUpload.isMultipartContent(req)) {
            upload(req);
        }

    }

    public void upload(HttpServletRequest request) throws UploadException {

        logger.info(" - [ ENTERING METHOD upload(HttpServletRequest request), PARAMETERS: HttpServletRequest request ]");
        if (!ServletFileUpload.isMultipartContent(request)) {
            logger.error("Form must have enctype=multipart/form-data");
            return;
        }

        //  Configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(MEMORY_THRESHOLD);

        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setFileSizeMax(MAX_FILE_SIZE);

        upload.setSizeMax(MAX_REQUEST_SIZE);

        //  This path is relative to application's directory
        String uploadPath = request.getServletContext().getContextPath()
                + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {

            //  Parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            HashMap<String, ArrayList<String>> attributes = new HashMap<String, ArrayList<String>>();
            FileItem fileItem = null;
            String contactId = null;
            DateTimeFormatter formatter = DateTimeFormat.forPattern("HH_mm_ss_dd_MM_YYYY_");
            String dateNowString = formatter.print(DateTime.now());

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        fileItem = item;
                    }
                    else {
                        if(attributes.containsKey(item.getFieldName())){
                            ((ArrayList<String>)attributes.get(item.getFieldName())).add(item.getString("UTF-8"));
                        }
                        else {
                            ArrayList<String> array = new ArrayList<String>();
                            array.add(item.getString("UTF-8"));
                            attributes.put(item.getFieldName(), array);
                        }
                        if(StringUtils.equalsIgnoreCase(item.getFieldName(), "id")){
                            contactId = item.getString("UTF-8");
                        }
                    }
                }
            }
            String fileName = null;

            if(fileItem != null && fileItem.getSize() > 0) {
                fileName = new File(fileItem.getName()).getName();

                if (StringUtils.isNotEmpty(fileName) && contactId != null) {

                    String contactPath = uploadPath + File.separator + contactId;
                    File uploadFileDir = new File(contactPath);
                    if (!uploadFileDir.exists()) {
                        uploadFileDir.mkdir();
                    }
                    fileName = dateNowString + fileName;
                    String filePath = contactPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    // saves the file on disk
                    fileItem.write(storeFile);
                    request.getSession().setAttribute("infoMessage",
                            "Успешно загружено!");
                }
                else {
                    throw new UploadException("Невозможно сохранить файл: fileName = " + String.valueOf(fileName) + ", contactId = " + String.valueOf(contactId));
                }
            }
            if(MapUtils.isNotEmpty(attributes)){
                for(Map.Entry<String, ArrayList<String>> entry : attributes.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue().toArray(new String[entry.getValue().size()]));
                }
            }
            if(fileName != null) {
                request.setAttribute("fileName", fileName);
            }

        } catch (Exception e) {
            throw new UploadException(e);
        }
    }
}
