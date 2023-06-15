package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Handler implements Runnable{
    String accesstime;
    BufferedReader input;
    BufferedWriter output;
    Socket socket;
    String id;
    Thread thread;
    boolean connectCheck = false;
    private static List<Handler> handlers = new LinkedList<>();
    private static int count = 0;
    public Handler(Socket socket) throws IOException {
        this.thread = new Thread(this);
        this.socket = socket;
    }

    public void showTime() throws IOException{
        this.output.write("#@현재시간: "+new Date().toString());
        this.output.newLine();
        this.output.flush();
    }

    public void userList() throws IOException{
        String user="";
        for(Handler h:handlers){
            user = user+h.getId()+" ";
        }
        this.output.write("#@유저리스트: "+user);
        this.output.newLine();
        this.output.flush();
    }

    public void accessTime() throws IOException{
        this.output.write("#@접속시간: "+accesstime);
        this.output.newLine();
        this.output.flush();
    }
    public void connect() throws IOException {
        if(getId().equals("null")){
            this.output.write("이름이 등록되지 않아 프로그램을 사용할 수 없습니다.");
            this.output.newLine();
            this.output.flush();
        } else {
            Handler.handlers.add(this);
            connectCheck = true;
        }
    }
    public void start() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.id = input.readLine();
        System.out.print(id);
        this.accesstime = new Date().toString();
        thread.start();
    }

    public void join(){
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop(){
        thread.interrupt();
    }
    @Override
    public void run() {
            try {
                while (!Thread.interrupted()) {
                    String line = input.readLine();
                    line = line.trim();
                    String[] args = line.split(" ");
                    if(args[0].equals("@@")){
                        line = line.replaceAll(args[0], "").trim();
                        System.out.println("!"+line+"!");
                        switch(line){
                            case "time": showTime(); break;
                            case "userlist": userList(); break;
                            case "connect": connect(); break;
                            case "accesstime" : accessTime(); break;
                            default:
                        }
                    }
                    if(!connectCheck){
                        continue;
                    }
                    if(args[0].contains("@")){
                        line = line.replaceAll(args[0], "").trim();
                        String target = args[0].replaceAll("@","");
                        if(target.equals("all")){
                            broadcast(line);
                        } else{
                            directMessage(target, line);
                        }
                    } else {
                        broadcast(line);
                    }

                }
            } catch (IOException |NullPointerException e) {
                stop();
                System.out.println(getId()+" Client Socket 종료");
                Thread.currentThread().interrupt();
            } finally {
                try {
                    stop();
                    socket.close();
                    handlers.remove(this);
                } catch (IOException ignore) {
                }

            }
    }

    public String getId(){
        return id;
    }

    public void remove(){
        synchronized (handlers){
            handlers.remove(this);
        }
    }
    public void directMessage(String id, String message) throws IOException {
        synchronized (handlers){
            for(Handler handler : handlers){
                if(handler.getId().equals(id)){
                    handler.output.write("귓속말"+this.getId()+" "+message);
                    handler.output.newLine();
                    handler.output.flush();
                }
            }
        }
    }

    public void broadcast(String message) throws IOException {
        synchronized (handlers){
            for(Handler handler: handlers){
                if(!handler.getId().equals(getId())){
                    handler.output.write("#"+id+" "+message);
                    handler.output.newLine();
                    handler.output.flush();
                }
            }
        }

    }
}
