package com.nhnacademy.springmvc.postlist.exception;

public class NotFoundPost extends Exception{
    public NotFoundPost(int page) {super("post_page:"+page);}
}
