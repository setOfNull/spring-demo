package kg.megalab.springdemo.controller;

import kg.megalab.springdemo.exceptions.EmptyListException;
import kg.megalab.springdemo.service.impl.StorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
@Slf4j
@Qualifier("storageService")
public class StorageController {

    private final StorageServiceImpl storageService;

    public StorageController( StorageServiceImpl storageService) {
        this.storageService = storageService;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStrings(){
        try{
            return new ResponseEntity<>(storageService.getList(), HttpStatus.OK);
        }catch (EmptyListException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addToList(@RequestParam String str){
         storageService.addToList(str);
         return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{str}")
    public ResponseEntity<Boolean> deleteFromList(@PathVariable String str){
        return new ResponseEntity<>(storageService.deleteStringFromList(str), HttpStatus.OK);
    }


    @GetMapping(value = "/get/{str}")
    public ResponseEntity<Boolean> isExist(@PathVariable String str){
        return new ResponseEntity<>(storageService.isExistInList(str), HttpStatus.OK) ;
    }
}
