package com.booking.onsite.oop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ooo
 * @description 图书管理系统
 * @date 2024/9/23 10:56:16
 */
public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }


    /**
     * Go thought the books to add book into a minHeap with 3 size
     * Then, reserve the 3 books
     * @return
     */
    public List<Book> top3Books(){
        return null;
    }
    public void addBook(Book book){
        this.books.add(book);
    }

    /**
     * How to define the borrow action? Just borrow the book by the title? What if there were duplicate name books?
     * A: I prefer to use amount to define the book is available or not.
     * Should we remove the borrowed book from the books?
     * A: With  the val amount, we didn't need to remove the book.
     * @param title
     */
    public void borrowBook(String title){
        Book book = findBook(title);
        if (book != null && book.amount > 0){
            System.out.println("You have borrowed the book:" + book.title + ", author: " + book.author);
            book.amount--; book.count++;
        }else {
            System.out.println("Sorry, there is no available book with the title:" + title);
        }
    }

    public void returnBook(Book book){
        Book find = findBook(book);
        if (find == null){
            System.out.println("Sorry, this book is not belonging to Library");
            return;
        }
        find.amount++;
    }

    public Book findBook(String title){
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)){
                return book;
            }
        }
        return null;
    }

    public Book findBook(Book b){
        for (Book book : books) {
            if (book.equals(b)){
                return book;
            }
        }
        return null;
    }
    static class Book{
        private String title;
        private String author;
        private int amount;
        private int count = 0;

        public Book(String title, String author, int amount) {
            this.title = title;
            this.author = author;
            this.amount = amount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getCount() {
            return count;
        }

        public void increaseCount(int count) {
            this.count++;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
}