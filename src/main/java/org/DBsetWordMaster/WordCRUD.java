package org.DBsetWordMaster;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class WordCRUD implements ICRUD{

    final String WORD_SELECTAll="select * from Dictionary";
    final String WORD_SELECT="select * from Dictionary where word like ? ";
    final String WORD_INSERT="insert into Dictionary (level, word, meaning, regdate) "
            +"values (?,?,?,?) ";
    final String WORD_UPDATE="update Dictionary set meaning=? where id=? ";
    final String WORD_DELETE="delete from Dictionary where id=?";

    HashSet<Word> set;
    Scanner s;
    final String fname="Dictionary.txt";
    Connection conn;

    public WordCRUD(Scanner s) {
        set=new HashSet<>();
        this.s=s;
        conn=DBConnection.getConnection();
    }

    public void loadData(String keyword){
        set.clear();

        try {
            PreparedStatement stmt;
            ResultSet rs;
            if(keyword.equals("")){
                stmt=conn.prepareStatement(WORD_SELECTAll);
                rs=stmt.executeQuery();
            }else{
                stmt=conn.prepareStatement(WORD_SELECT);
                stmt.setString(1,"%"+keyword+"%");
                rs=stmt.executeQuery();
            }

//            System.out.println(keyword+" "+stmt);
            while(true){
                if(!rs.next()) break;
                int id=rs.getInt("id");
                int level=rs.getInt("level");
                String word=rs.getString("word");
                String meaning=rs.getString("meaning");
                set.add(new Word(id,level,word,meaning));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrentDate(){
        LocalDate now=LocalDate.now();
        DateTimeFormatter f=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return f.format(now);
    }

    @Override
    public int add(Word one) {
        int retVal=0;
        PreparedStatement pstmt;
        try {
            pstmt=conn.prepareStatement(WORD_INSERT);
            pstmt.setInt(1,one.getLevel());
            pstmt.setString(2,one.getWord());
            pstmt.setString(3,one.getMeaning());
            pstmt.setString(4,getCurrentDate());
            retVal=pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return retVal;
    }

    public void addWord() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level=s.nextInt();
        String word=s.nextLine().trim();

        System.out.print("뜻 입력 : ");
        String meaning=s.nextLine();

        int pre=set.size();

        Word one=new Word(0,level,word,meaning);

        set.add(one);

        int aft=set.size();

        if(pre==aft){
            System.out.println("해당 단어는 중복됩니다!!!");
            return ;
        }


        int retVal=add(one);
        if(retVal>0) System.out.println("새 단어가 단어장에 추가되었습니다. ");
        else System.out.println("새 단어 추가중 에러가 발생되었습니다. ");




    }

    @Override
    public int update(Word one){
        int retVal=0;
        PreparedStatement pstmt;
        try {
            pstmt=conn.prepareStatement(WORD_UPDATE);
            pstmt.setString(1,one.getMeaning());
            pstmt.setInt(2,one.getId());
            retVal=pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }
    public int updateItem() {

        System.out.print("=> 수정할 단어 검색 : ");
        String keyword=s.next();
        ArrayList<Word> idList=listAll(keyword);

        System.out.print("=> 수정할 번호 선택 : ");
        int id=s.nextInt();

        s.nextLine();

        if(id<0 || (id-1)>=idList.size()){
            System.out.println("id: "+id+" idList.size: "+idList.size());
            System.out.println("선택한 번호가 범위를 벗어났습니다!!!");
            return 0;
        }

        int level=idList.get(id-1).getLevel();
        String word=idList.get(id-1).getWord();

        System.out.print("뜻 입력 : ");
        String meaning=s.nextLine();

        int val=this.update(new Word(idList.get(id-1).getId(),level,word,meaning));

        if(val>0){
            set.remove(idList.get(id-1));
            set.add(new Word(0,level,word,meaning));
            System.out.println("단어가 수정되었습니다. ");
        }else{
            System.out.println("[수정] 에러발생 !!!");
        }



        return 0;
    }
    public int delete(Word one){
        int retVal=0;
        PreparedStatement pstmt;
        try{
            pstmt=conn.prepareStatement(WORD_DELETE);
            pstmt.setInt(1,one.getId());
            retVal=pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }

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

        if(id<0 || (id-1)>=idList.size()){
            System.out.println("선택한 번호가 범위를 벗어났습니다!!!");
            return 0;
        }

        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String ans=s.next();


        if(ans.equalsIgnoreCase("y")){
        int val = this.delete(new Word(idList.get(id - 1).getId(), 0, "", ""));
            set.remove(idList.get(id-1));
            System.out.println("단어가 삭제되었습니다. ");
        }else{
            System.out.println("취소되었습니다. ");
        }

        return 1;
    }

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

    public ArrayList<Word> listAll(String keyword){
        ArrayList<Word> idList=new ArrayList<>();
        loadData(keyword);
        System.out.println("-------------------------------- ");

        int i=1;
        int j=0;

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


//        while(iter.hasNext()){
//            Word tmpWord=iter.next();
//
//            String word=tmpWord.getWord();
//            if(word.contains(keyword)){
//                System.out.print((j+1)+" ");
//                System.out.println(tmpWord.toString());
//
//                idList.add(tmpWord);
//                j++;
//            }else continue;
//
//            System.out.print(i+" ");
//            System.out.println(tmpWord.toString());
//
//            i++;
//        }

        System.out.println("-------------------------------- \n");

        return idList;
    }

    private void listAll(int level) {
        loadData("");
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

//    public ArrayList<Word> listAll(String keyword) {
//        ArrayList<Word> idList=new ArrayList<>();
//        int j=0;
//
//        System.out.println("-------------------------------- ");
//
//        Iterator<Word> iter=set.iterator();
//
//        while(iter.hasNext()){
//            Word tmpWord=iter.next();
//            String word=tmpWord.getWord();
//            if(word.contains(keyword)){
//                System.out.print((j+1)+" ");
//                System.out.println(tmpWord.toString());
//
//                idList.add(tmpWord);
//                j++;
//            }else continue;
//        }
//
//        System.out.println("-------------------------------- \n");
//
//        return idList;
//    }


}
