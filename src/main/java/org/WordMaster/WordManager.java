package org.WordMaster;

import java.nio.FloatBuffer;
import java.util.Scanner;


public class WordManager {
    Scanner s=new Scanner(System.in);
    WordCRUD wordCRUD;

    public WordManager() {
        wordCRUD=new WordCRUD(s);
    }


    public int selectMenu(){
        System.out.print("*** 영단어 마스터 *** \n" +
                "******************** \n" +
                "1. 모든 단어 보기\n" +
                "2. 수준별 단어 보기\n" +
                "3. 단어 검색\n" +
                "4. 단어 추가\n" +
                "5. 단어 수정\n" +
                "6. 단어 삭제\n" +
                "7. 파일 저장\n" +
                "0. 나가기\n" +
                "********************\n" +
                "=> 원하는 메뉴는? ");

        int menu=s.nextInt();

        return menu;
    }



    public void start(){
        wordCRUD.loadFile();
        while(true){
            int menu=selectMenu();
            if(wordCRUD.list.size()==0 && !(menu==4 || menu==0 )){
                System.out.println("현재 저장된 단어가 없습니다. 다시 선택하세요!");
                continue;
            }


            if(menu==0){
                System.out.println("\n프로그램 종료! 다음에 만나요~");
                break;
            }
            if(menu==4){
                //create
                wordCRUD.addWord();
            }else if(menu==1){
                //list
                wordCRUD.listAll();
            }else if(menu==2){
                wordCRUD.searchLevel();
            }else if(menu==3){
                wordCRUD.searchWord();
            }else if(menu==5){

//                Scanner tmp=new Scanner(System.in);
//                System.out.print("수정하고 싶은 단어는? ");
//                String udt=tmp.nextLine();
//
//                //update
//                int com=wordCRUD.updateItem(udt);
//
//                if(com==1){
//                    System.out.println("단어 수정이 정상적으로 완료되었습니다!");
//                }else{
//                    System.out.println("잘못된 입력");
//                }
                wordCRUD.updateItem();

            }else if(menu==6){
                //delete
                wordCRUD.deleteItem();
            }else if(menu==7){
                wordCRUD.saveFile();
            }
        }

    }
}
