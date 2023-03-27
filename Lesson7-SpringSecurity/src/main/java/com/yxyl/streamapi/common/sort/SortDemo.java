package com.yxyl.streamapi.common.sort;


import com.yxyl.streamapi.Author;

import java.util.Comparator;
import java.util.List;

import static com.yxyl.streamapi.StreamDemo.getAuthors;

public class SortDemo {
    public static void main(String[] args) {
        List<Author> authors1 = getAuthors();
//        对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素。
        authors1.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge().compareTo(o1.getAge()))
                .forEach(author -> System.out.println(author.getAge()));


        List<Author> authors2 = getAuthors();
//        对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素。
        authors2.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .forEach(author -> System.out.println(author.getAge()));
        //注意：如果调用空参的sorted()方法，需要流中的元素是实现了Comparable。
    }
}
