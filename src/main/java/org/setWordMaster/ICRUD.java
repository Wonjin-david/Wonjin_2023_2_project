package org.setWordMaster;

public interface ICRUD {
    public Object add();
    public int updateItem();
    public int deleteItem();
    public void selectOne(int id);
}
