package kg.megalab.springdemo.service.impl;

import kg.megalab.springdemo.exceptions.EmptyListException;
import kg.megalab.springdemo.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Qualifier("storageService")
public class StorageServiceImpl implements StorageService {
    private List<String> list = new ArrayList<>();

    public List<String> getList(){
        if (list.isEmpty()){
            throw new EmptyListException("List is empty");
        }return  list;
    }


    public void addToList(String str){
        if(isExist(str)){
            log.error("String {} is already exist", str);
            return;
        }
        list.add(str);
        log.info("String {} added to list", str);
    }

    public boolean deleteStringFromList(String str){
        if (!isExist(str)){
            log.error("String {} not exist in list", str);
            return  false;
        }else{
            list.remove(str);
            log.info("String {} was deleted from list", str);
        }
        return true;
    }

    public boolean isExistInList(String str){
        return  isExist(str);
    }

    public boolean isExist(String str){
        return  list.contains(str);
    }
}