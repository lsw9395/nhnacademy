package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Client {
    public static ArrayList<String> blockUser = new ArrayList<>();
    public static void main(String[] args) {
        String ip = "null";
        String id = "null";
        int port =0;
        Socket s = new Socket();
        Scanner sc = new Scanner(System.in);
        Options options = new Options();
        Option optionH = Option.builder("H")
            .hasArgs()
            .argName("host")
            .desc("접속할 서버의 호스트 네임 또는 IP를 지정합니다.")
            .build();
        options.addOption(optionH);
        options.addOption("h","도움말");
        Option optionI = Option.builder("I")
            .hasArg()
            .argName("user id")
            .desc("채팅에서 사용할 사용자 아이디를 지정합니다.")
            .build();
        Option optionp = Option.builder("p")
            .hasArg()
            .argName("port")
            .desc("접속할 서버의 서비스 포트를 지정합니다.")
            .build();
        options.addOption(optionI);
        options.addOption(optionp);
        while(!s.isConnected()){
            try{
                CommandLineParser parser = new DefaultParser();
                String arg = sc.nextLine();
                String[] cmd = arg.split(" ");
                if(!cmd[0].contains("-") && cmd.length >= 1){
                    continue;
                }
                String check = cmd[0];
                if(!options.hasOption((check.split("-"))[1])){
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("user: MCClient", options);
                    continue;
                }
                CommandLine lines = parser.parse(options, cmd);
                for(Option option : lines.getOptions()){
                    if(option.getOpt().equals("H")){
                        ip = option.getValue();
                        System.out.println("ip가"+ip+"로 설정되었습니다."); break;
                    }
                    if(option.getOpt().equals("I")){
                        id = option.getValue();
                        System.out.println("id가"+ id+ "로 설정되었습니다."); break;
                    }
                    if(option.getOpt().equals("p")){
                        port = Integer.parseInt(option.getValue());
                        System.out.println("port가"+port+"로 설정되었습니다."); break;
                    }
                    if(option.getOpt().equals("h")){
                        HelpFormatter formatter = new HelpFormatter();
                        formatter.printHelp("user: MCClient", options);
                    }
                }
                if(port !=0 && ip != "null"){
                    s = new Socket(ip,port);
                }
            }catch(UnknownHostException e){
                System.out.println("존재하지 않는 호스트 ip입니다.");
            }catch (IOException|ParseException|NumberFormatException e) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("user: MCClient", options);
            }
        }
        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedWriter output3 = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedReader input3 = new BufferedReader(new InputStreamReader(s.getInputStream()));
            output3.write(id);
            output3.newLine();
            output3.flush();
            Thread inputThread = new Thread(()->{
                try {
                    while(!Thread.interrupted()){
                        String text = input3.readLine();
                        if(checkUser(text)){
                            terminal.write(text);
                            terminal.newLine();
                            terminal.flush();
                        }
                    }
                } catch (IOException e){
                    throw new RuntimeException(e);
                }
            });
            inputThread.setDaemon(true);
            inputThread.start();

            while(!Thread.interrupted()){
                String line = console.readLine();
                line = line.trim();
                String[] cmd = line.split(" ");
                if(cmd[0].contains("!")){
                    line = line.replaceAll(cmd[0], "").trim();
                    String target = cmd[0].replace("!","");
                    switch(target){
                        case "차단": addBlockUser(line); break;
                        case "끝" : System.out.println("종료합니다."); Thread.currentThread().interrupt(); break;
                        default:
                    }
                } else {
                    output3.write(line);
                    output3.newLine();
                    output3.flush();
                }
            }

        } catch(Exception ignore){
            System.err.println("연결을 실패하였습니다.");
            Thread.currentThread().interrupt();
        }
    }

        public static void addBlockUser(String user){
            blockUser.add(user);
        }
        public static boolean checkUser(String text){
            for(String user:blockUser){
                if(text.contains(user)){
                    return false;
                }
            }
            return true;
        }
}


