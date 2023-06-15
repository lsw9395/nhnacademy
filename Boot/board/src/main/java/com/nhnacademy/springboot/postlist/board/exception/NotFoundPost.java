package com.nhnacademy.springboot.postlist.board.exception;

public class NotFoundPost extends Exception{
    public NotFoundPost(int page) {super("post_page:"+page);}
}
