package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kleba on 02.03.2017.
 */
public class JsonUsers {

    private final List<User> list;

    public JsonUsers(List<User> sourceList, int fromIndex) {


        this.list = new ArrayList<>();
        for (int i = fromIndex; i < sourceList.size(); i++) {


            list.add(sourceList.get(i));


        }
    }
    public List<User> getList() {
        return Collections.unmodifiableList(list);
    }
}