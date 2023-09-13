package org.DBsetWordMaster;

public interface ICRUD {
    public int add(Word one);
    public int updateItem();
    public int deleteItem();
    public void selectOne(int id);
}
