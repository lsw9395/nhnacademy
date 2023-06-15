package com.nhnacademy.springmvc.postlist.exception;

public class NotFoundPostId extends Exception{
    public NotFoundPostId(Long id){super("post_id:"+id);}
}
