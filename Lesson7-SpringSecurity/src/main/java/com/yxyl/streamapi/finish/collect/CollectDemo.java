package com.yxyl.streamapi.finish.collect;


import com.yxyl.streamapi.Author;
import com.yxyl.streamapi.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.yxyl.streamapi.StreamDemo.getAuthors;

public class CollectDemo {
    public static void main(String[] args) {
        //获取一个存放所有作者名字的List集合。
        List<Author> authors1 = getAuthors();
        List<String> nameList = authors1.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(nameList);

        
        //获取一个所有书名的Set集合。
        List<Author> authors2 = getAuthors();
        Set<Book> books = authors2.stream()
                .flatMap(author -> author.getBooks().stream())
                .collect(Collectors.toSet());
        System.out.println(books);


        //获取一个Map集合，map的key为作者名，value为List<Book>
        List<Author> authors3 = getAuthors();
        Map<String, List<Book>> map = authors3.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
        System.out.println(map);

    }
}
