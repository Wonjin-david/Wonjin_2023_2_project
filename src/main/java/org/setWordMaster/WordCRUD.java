package org.setWordMaster;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class WordCRUD implements ICRUD{

    HashSet<Word> set;
    Scanner s;
    final String fname="Dictionary.txt";

    public WordCRUD(Scanner s) {
        set=new HashSet<>();
        this.s=s;
    }



    public void addWord() {
        Word one=(Word)add();

        int pre=set.size();

        set.add(one);

        int aft=set.size();

        if(pre==aft){
            System.out.println("해당 단어는 중복됩니다!!!");
            return ;
        }

        System.out.println("새 단어가 단어장에 추가되었습니다 !!!");
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level=s.nextInt();
        String word=s.nextLine().trim();

        System.out.print("뜻 입력 : ");
        String meaning=s.nextLine();

        return new Word(0,level,word,meaning);
    }

    @Override
    public int updateItem() {

        System.out.print("=> 수정할 단어 검색 : ");
        String keyword=s.next();

        ArrayList<Word> idList=this.listAll(keyword);

        if(idList.size()==0){
            System.out.println("해당 단어는 존재하지 않습니다.");
            return 0;
        }

        System.out.print("=> 수정할 번호 선택 : ");
        int id=s.nextInt();

        s.nextLine();

        if(id<0 || id>=idList.size()){
            System.out.println("선택한 번호가 범위를 벗어났습니다!!!");
            return 0;
        }

        int level=idList.get(id-1).getLevel();
        String word=idList.get(id-1).getWord();

        System.out.print("뜻 입력 : ");
        String meaning=s.nextLine();


        set.remove(idList.get(id-1));
        set.add(new Word(0,level,word,meaning));
        System.out.println("단어가 수정되었습니다. ");

        return 0;
    }

    @Override
    public int deleteItem() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword=s.next();

        ArrayList<Word> idList=this.listAll(keyword);

        if(idList.size()==0){
            System.out.println("해당 단어는 존재하지 않습니다.");
            return 0;
        }


        System.out.print("=> 삭제할 번호 선택 : ");
        int id=s.nextInt();

        s.nextLine();

        if(id<0 || id>=idList.size()){
            System.out.println("선택한 번호가 범위를 벗어났습니다!!!");
            return 0;
        }

        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String ans=s.next();

        if(ans.equalsIgnoreCase("y")){
            set.remove(idList.get(id-1));
            System.out.println("단어가 삭제되었습니다. ");
        }else{
            System.out.println("취소되었습니다. ");
        }

        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void saveFile() {
        try {
            PrintWriter pr=new PrintWriter(new FileWriter(fname));

            Iterator<Word> iter=set.iterator();

            while(iter.hasNext()){
                Word tmpWord=iter.next();

                pr.write(tmpWord.toFileString()+"\n");
            }
            pr.close();
            System.out.println("==> 데이터 저장 완료!!!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchWord() {
        System.out.print("=> 원하는 단어는? ");
        String keyword=s.next();
        listAll(keyword);
    }

    public void searchLevel() {
        System.out.print("=> 원하는 레벨은? (1~3) ");
        int level=s.nextInt();
        listAll(level);
    }



    public void loadFile() {
        try {
            BufferedReader br=new BufferedReader(new FileReader(fname));
            String line;
            int count=0;

            while(true){
                line=br.readLine();
                if(line==null) break;

                String data[]=line.split("\\|");
                int level=Integer.parseInt(data[0]);
                String word=data[1];
                String meaning=data[2];
                set.add(new Word(0,level,word,meaning));
                count++;
            }

            br.close();
            System.out.println("==> "+count+"개 로딩 완료!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listAll(){
        System.out.println("-------------------------------- ");

        int i=1;

        Iterator<Word> iter=set.iterator();

        while(iter.hasNext()){
            Word tmpWord=iter.next();

            System.out.print(i+" ");
            System.out.println(tmpWord.toString()+"    "+tmpWord.hashCode());

            i++;
        }

        System.out.println("-------------------------------- \n");

    }

    private void listAll(int level) {
        int j=0;

        Iterator<Word> iter=set.iterator();

        System.out.println("-------------------------------- ");

        while(iter.hasNext()){
            Word tmpWord=iter.next();

            int ilevel=tmpWord.getLevel();
            if(ilevel!=level) continue;

            System.out.print((j+1)+" ");
            System.out.println(tmpWord.toString());

            j++;

        }

        System.out.println("-------------------------------- \n");
    }

    public ArrayList<Word> listAll(String keyword) {
        ArrayList<Word> idList=new ArrayList<>();
        int j=0;

        System.out.println("-------------------------------- ");

        Iterator<Word> iter=set.iterator();

        while(iter.hasNext()){
            Word tmpWord=iter.next();
            String word=tmpWord.getWord();
            if(word.contains(keyword)){
                System.out.print((j+1)+" ");
                System.out.println(tmpWord.toString());

                idList.add(tmpWord);
                j++;
            }else continue;
        }

        System.out.println("-------------------------------- \n");

        return idList;
    }


}
