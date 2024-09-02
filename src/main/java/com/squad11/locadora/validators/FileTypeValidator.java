package com.squad11.locadora.validators;

import com.squad11.locadora.constraints.FileType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileTypeValidator implements ConstraintValidator<FileType, MultipartFile> {

    private List<String> allowedExtensions;


    @Override
    public void initialize(FileType constraintAnnotation) {
        this.allowedExtensions = List.of(constraintAnnotation.allowedExtensions());
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
       if(file == null || file.isEmpty()) {
           return true;
       }

       String originalFileName = file.getOriginalFilename();
       String extension = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase() : null;

//       if(!allowedExtensions.contains(extension)) {
//           throw new FileTypeValidationException("The file type is not accepted exception");
//       }

       return allowedExtensions.contains(extension);
    }
}
