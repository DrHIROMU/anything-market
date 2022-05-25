package drhiromu.anythingmarketmainservice.file.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileService {
    private final Path filePath = Paths.get("uploads/files/");
    private final Path imagePath = Paths.get("uploads/images/");

    private static final List<String> imageExtentions = Arrays.asList("png", "jpg", "jpeg", "gif");

    public void init(Path path) throws Exception{
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw e;
        }
    }

    public InputStream downloadFile(String fileId){
        InputStream targetStream = null;
        try{
            String fileName = "test.txt";
            Resource resource = load(fileName);
            targetStream = resource.getInputStream();
        }catch (Exception ex){
            log.error("Error in downloadFile()", ex);
        }
        return targetStream;
    }
    
    public void save(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if(imageExtentions.contains(extension)){
            saveImage(file);
        }else{
            saveFile(file);
        }
    }

    public void saveFile(MultipartFile file) {
        try {
            if(!Files.exists(filePath)){
                init(filePath);
            }
            Files.copy(file.getInputStream(), this.filePath.resolve(file.getOriginalFilename()));
        } catch (FileAlreadyExistsException fileAlreadyExistsException){
            log.error("Error in saveFile()", fileAlreadyExistsException);
            throw new RuntimeException("Error: file already exists.");
        } catch (Exception e) {
            log.error("Error in saveFile()", e);
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public void saveImage(MultipartFile file) {
        try {
            if(!Files.exists(imagePath)){
                init(imagePath);
            }
            Files.copy(file.getInputStream(), this.imagePath.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            log.error("Error", e);
            throw new RuntimeException("Could not store the image. Error: " + e.getMessage());
        }
    }
    
    public Resource load(String filename) {
        try {
            Path file = filePath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(filePath.toFile());
    }
    
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.filePath, 1).filter(path -> !path.equals(this.filePath)).map(this.filePath::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
