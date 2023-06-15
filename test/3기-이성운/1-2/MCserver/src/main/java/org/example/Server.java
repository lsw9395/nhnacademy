package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Server {
    public static List<String> blacklist = new ArrayList<>();
    public static void main(String[] args) {
        if(args.length < 1){
            Scanner sc = new Scanner(System.in);
            String arg = sc.nextLine();
            args = arg.split(" ");
        }
        Options options = new Options();
        Option optionb = Option.builder("b")
            .hasArg()
            .argName("arg")
            .desc("블랙리스트")
            .build();
        Option optionh = new Option("h", "도움말");
        Option optionp = Option.builder("p")
            .hasArg()
            .argName("arg")
            .desc("서비스 포트")
            .build();
        options.addOption(optionb);
        options.addOption(optionh);
        options.addOption(optionp);
        try{
            CommandLineParser parser = new DefaultParser();
            CommandLine lines = parser.parse(options, args);
            if(args.length < 1){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("MCserver",options);
                return;
            }
            for(Option option : lines.getOptions()){
                if(option.getOpt().equals("b")){
                    blacklist.add(option.getValue()); break;
                }
                if(option.getOpt().equals("p")){
                    int port = Integer.parseInt(option.getValue());
                    serverStart(port); break;
                }
                if(option.getOpt().equals("h")){
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("MCServer",options);
                }
            }
        } catch (Exception e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("MCserver",options);
        }

        //
    }
    public static void serverStart(int port){
        Thread sThread = new Thread(()->{
            try (ServerSocket server = new ServerSocket(port)){
                while(!Thread.interrupted()){
                    System.out.println("연결대기중");
                    Handler handler = new Handler(server.accept());
                    handler.start();
                    checkBlackList(handler);
                    System.out.println("연결됨");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        sThread.start();
    }
    public static void checkBlackList(Handler handler){
        for(String id : blacklist){
            if(id.equals(handler.getId())){
                handler.stop();
            }
        }
    }
}
