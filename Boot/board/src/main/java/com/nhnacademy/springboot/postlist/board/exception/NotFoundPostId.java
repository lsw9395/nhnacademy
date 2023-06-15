package com.nhnacademy.springboot.postlist.board.exception;

public class NotFoundPostId extends Exception{
    public NotFoundPostId(Long id){super("post_id:"+id);}
}
