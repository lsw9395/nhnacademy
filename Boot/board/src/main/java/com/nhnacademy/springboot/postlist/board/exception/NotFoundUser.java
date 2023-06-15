package com.nhnacademy.springboot.postlist.board.exception;

public class NotFoundUser extends Exception{
    public NotFoundUser(String page){super("user_page:"+page);}
}
