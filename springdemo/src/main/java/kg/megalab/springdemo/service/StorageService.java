package kg.megalab.springdemo.service;

import java.util.List;

public interface StorageService {
    List<String> getList();

    void addToList(String str);

    boolean deleteStringFromList(String str);

    boolean isExistInList(String str);

    boolean isExist(String str);

    //boolean isExist(String str);
}
