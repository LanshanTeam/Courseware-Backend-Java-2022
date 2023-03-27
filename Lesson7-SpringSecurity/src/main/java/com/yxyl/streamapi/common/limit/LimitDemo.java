package com.yxyl.streamapi.common.limit;


import com.yxyl.streamapi.Author;

import java.util.List;

import static com.yxyl.streamapi.StreamDemo.getAuthors;

public class LimitDemo {
    public static void main(String[] args) {
        //可以设置流的最大长度，超出的部分将被抛弃。
        List<Author> authors = getAuthors();
        
        authors.stream()
                .distinct()
//                .sorted()
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));
        
    }
}