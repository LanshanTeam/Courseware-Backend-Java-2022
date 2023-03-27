package com.yxyl.streamapi.finish.count;


import com.yxyl.streamapi.Author;

import java.util.List;

import static com.yxyl.streamapi.StreamDemo.getAuthors;

public class CountDemo {
    public static void main(String[] args) {
        //打印这些作家的所出书籍的数目，注意删除重复元素。
        List<Author> authors = getAuthors();

        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println("count = " + count);
    }
}
