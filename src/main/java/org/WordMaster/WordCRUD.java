package org.WordMaster;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;

    WordCRUD(Scanner s){
        list=new ArrayList<>();
        this.s=s;
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level=s.nextInt();
        String word=s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning=s.nextLine();

        return new Word(0,level,word,meaning);
    }

    public void addWord(){
        Word one=(Word)add();
        list.add(one);
        System.out.println("새 단어장에 추가되었습니다. ");
    }

    @Override
    public int update(Object obj) {
        boolean exist=false;

        for(int i=0;i<list.size();i++){
            System.out.println("++"+list.get(i).getWord().trim()+"++");


            String tmp=list.get(i).getWord().trim();

            if(tmp.equals((String)obj)){
                exist=true;
                Scanner udtS=new Scanner(System.in);

                System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
                int level=udtS.nextInt();
                String word=udtS.nextLine();

                System.out.print("뜻 입력 : ");
                String meaning=udtS.nextLine();

                Word udtWord=new Word(0,level,word,meaning);

                list.set(i,udtWord);

                return 1;
            }

        }

        if(!exist){
            System.out.println("해당 단어는 존재하지 않습니다..");
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll(){
        System.out.println("-------------------------------- ");
        for(int i=0;i<list.size();i++){
            System.out.print((i+1)+" ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("-------------------------------- \n");

    }
}
